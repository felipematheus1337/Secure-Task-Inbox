package com.taskinbox.v1.kafka.producer;

import com.taskinbox.v1.domain.model.TaskEvent;
import com.taskinbox.v1.domain.model.event.TaskCreateEvent;
import com.taskinbox.v1.domain.model.event.TaskUpdateEvent;
import com.taskinbox.v1.support.errors.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskEventProducer {

    private final StreamBridge streamBridge;

    private static final String CREATE_OUT = "taskCreated-out-0";
    private static final String UPDATE_OUT = "taskStatusUpdated-out-0";

    public void send(TaskEvent event) {
        if (event == null || event.getPayload() == null) {
            throw new BusinessException("Payload invalid.");
        }

        Object payload = event.getPayload();

        if (payload instanceof TaskCreateEvent createEvent) {
            streamBridge.send(CREATE_OUT, MessageBuilder.withPayload(createEvent).build());
            return;
        }

        if (payload instanceof TaskUpdateEvent updateEvent) {
            streamBridge.send(UPDATE_OUT, MessageBuilder.withPayload(updateEvent).build());
            return;
        }

        throw new BusinessException("Unsupported task event payload: " + payload.getClass().getSimpleName());
    }
}
