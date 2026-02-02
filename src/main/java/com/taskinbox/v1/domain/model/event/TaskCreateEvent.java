package com.taskinbox.v1.domain.model.event;

import com.taskinbox.v1.domain.model.TaskEvent;
import com.taskinbox.v1.domain.model.enumerations.TaskEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreateEvent implements TaskEvent {

    private String eventId;

    private TaskEventType type;

    private String ownerId;

    private Instant createdAt;

    private String status;


    @Override
    public TaskEventType getEventType() {
        return TaskEventType.TASK_CREATED;
    }

    @Override
    public TaskCreateEvent getPayload() {
        return this;
    }
}
