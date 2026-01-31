package com.taskinbox.v1.domain.model.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    MODERATOR("MODERATOR"),
    ADMIN("ADMIN");

    private final String role;
}

