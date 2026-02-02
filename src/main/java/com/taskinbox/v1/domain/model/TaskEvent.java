package com.taskinbox.v1.domain.model;

import com.taskinbox.v1.domain.model.enumerations.TaskEventType;

public interface TaskEvent {

    TaskEventType getEventType();

    Object getPayload();
}
