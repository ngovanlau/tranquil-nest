package com.laau.tranquilnest.controller;

import com.laau.tranquilnest.dto.ApiResponse;
import com.laau.tranquilnest.dto.request.AuthenticationRequest;
import com.laau.tranquilnest.dto.request.IntrospectionRequest;
import com.laau.tranquilnest.dto.response.AuthenticationResponse;
import com.laau.tranquilnest.dto.response.IntrospectionResponse;
import com.laau.tranquilnest.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationCantroller {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectionResponse> introspect(
            @RequestBody IntrospectionRequest request
    ) {
        return ApiResponse.<IntrospectionResponse>builder()
                .result(authenticationService.introspect(request))
                .build();
    }
}
