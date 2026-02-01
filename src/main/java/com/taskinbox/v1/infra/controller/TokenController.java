package com.taskinbox.v1.infra.controller;

import com.taskinbox.v1.domain.service.TokenService;
import com.taskinbox.v1.infra.dtos.LoginRequest;
import com.taskinbox.v1.infra.dtos.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse response = tokenService.login(loginRequest);

        if (response != null) return ResponseEntity.ok(response);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

}
