package com.taskinbox.v1.domain.model.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskEventType {

    TASK_CREATED("TASK_CREATED"),
    TASK_STATUS_UPDATED("TASK_STATUS_UPDATED");
    private final String value;
}
