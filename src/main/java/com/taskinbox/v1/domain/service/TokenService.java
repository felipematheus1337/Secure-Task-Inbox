package com.taskinbox.v1.domain.service;

import com.taskinbox.v1.domain.repo.UserRepository;
import com.taskinbox.v1.infra.dtos.LoginRequest;
import com.taskinbox.v1.infra.dtos.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public LoginResponse login(LoginRequest loginRequest) {

        var user = userRepository.findByEmail(loginRequest.mail());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("user or password is invalid.");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scope = user.get()
                .getRole();

        var claims = JwtClaimsSet.builder()
                .issuer("taskinbox")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scope)
                .build();

        var jwtValue = jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);

    }
}
