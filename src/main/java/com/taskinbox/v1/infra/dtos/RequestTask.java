package com.taskinbox.v1.infra.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RequestTask(@NotBlank String ownerId, @NotBlank String title, @NotBlank String status, List<String> tags) {
}
