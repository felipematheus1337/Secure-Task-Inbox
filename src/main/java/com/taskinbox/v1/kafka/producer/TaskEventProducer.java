package com.taskinbox.v1.kafka.producer;

import com.taskinbox.v1.domain.model.TaskEvent;
import com.taskinbox.v1.domain.model.enumerations.TaskEventType;
import com.taskinbox.v1.domain.model.event.TaskCreateEvent;
import com.taskinbox.v1.domain.model.event.TaskUpdateEvent;
import com.taskinbox.v1.support.errors.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class TaskEventProducer<T extends TaskEvent> {

    private final StreamBridge streamBridge;
    private static final String CREATE_EVENT = "taskCreated-in-0";
    private static final String UPDATE_EVENT = "taskStatusUpdated-in-0";

    public void send(TaskEvent T) {

         if (T.getPayload().equals(null)) throw new BusinessException("Payload invalid.");

         boolean isToCreate = verifyIfIsToCreateEvent(T);
         boolean isToUpdate = verifyIfIsToUpdateEvent(T);

         if (isToCreate) {

             TaskCreateEvent createEvent = (TaskCreateEvent) T.getPayload();
             streamBridge.send(CREATE_EVENT, createEvent);


         } else if (isToUpdate){
             TaskUpdateEvent updateEvent = (TaskUpdateEvent) T.getPayload();
             streamBridge.send(UPDATE_EVENT, updateEvent);
         } else {
             throw new BusinessException("Failed to parse specific payload.");
         }

    }

    private boolean verifyIfIsToUpdateEvent(TaskEvent t) {
        return t.getPayload()
                .getClass()
                .getName()
                .toLowerCase()
                .compareTo(TaskUpdateEvent.class.getCanonicalName()) == 0 ? true : false;
    }

    private boolean verifyIfIsToCreateEvent(TaskEvent t) {

        return t.getPayload()
                .getClass()
                .getName()
                .toLowerCase()
                .compareTo(TaskCreateEvent.class.getCanonicalName()) == 0 ? true : false;
    }

    private boolean isStatusValid(String status) {
        if (status == null || status.isBlank()) return false;

        return Arrays.stream(TaskEventType.values())
                .anyMatch(statusTask -> statusTask
                        .getValue()
                        .equals(status));
    }



}
