package com.laau.tranquilnest.service;

import com.laau.tranquilnest.dto.request.AuthenticationRequest;
import com.laau.tranquilnest.dto.request.IntrospectionRequest;
import com.laau.tranquilnest.dto.response.AuthenticationResponse;
import com.laau.tranquilnest.dto.response.IntrospectionResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    IntrospectionResponse introspect(IntrospectionRequest request);
}
