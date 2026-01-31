package com.taskinbox.v1.infra.dtos;

public record LoginResponse(String accessToken, Long expiresIn) {
}
