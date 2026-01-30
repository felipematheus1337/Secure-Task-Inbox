package com.taskinbox.v1.infra.dtos;

import com.taskinbox.v1.domain.model.enumerations.Status;

import java.time.Instant;
import java.util.List;

public record TaskResponse(
        String id, String ownerId, String title, String description,
        Status status, List<String> tags,
        Instant createdAt,
        Instant updatedAt
) {
}
