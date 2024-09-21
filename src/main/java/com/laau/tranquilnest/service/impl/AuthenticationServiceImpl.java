package com.laau.tranquilnest.service.impl;

import com.laau.tranquilnest.dto.request.AuthenticationRequest;
import com.laau.tranquilnest.dto.request.IntrospectionRequest;
import com.laau.tranquilnest.dto.response.AuthenticationResponse;
import com.laau.tranquilnest.dto.response.IntrospectionResponse;
import com.laau.tranquilnest.entity.User;
import com.laau.tranquilnest.enums.ErrorCode;
import com.laau.tranquilnest.exception.AppException;
import com.laau.tranquilnest.repository.UserRepository;
import com.laau.tranquilnest.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signer-key}")
    protected String SINGER_KEY;
    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private String generateToken(User user) {
        JWSHeader header =new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("com.laau.tranquilnest")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .claim("scope", "ROLE_" + user.getRole().name())
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException exception) {
            log.error("Cannot create token", exception);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public IntrospectionResponse introspect(IntrospectionRequest request) {
        var token = request.getToken();
        boolean isValid = true;

        try {
            JWSVerifier verifier = new MACVerifier(SINGER_KEY.getBytes());

            SignedJWT signedJWT = SignedJWT.parse(token);

            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            var verified = signedJWT.verify(verifier);

            if (!(verified && expirationTime.after(new Date())))
                throw new AppException(ErrorCode.UNAUTHENTICATED);
        } catch (AppException | JOSEException | ParseException e) {
            isValid = false;
        }

        return IntrospectionResponse.builder()
                .valid(isValid)
                .build();
    }
}
