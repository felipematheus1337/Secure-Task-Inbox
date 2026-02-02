package com.taskinbox.v1.kafka.consumer;

import com.taskinbox.v1.domain.model.TaskEvent;
import com.taskinbox.v1.domain.model.event.TaskActivity;
import com.taskinbox.v1.domain.model.event.TaskCreateEvent;
import com.taskinbox.v1.domain.model.event.TaskUpdateEvent;
import com.taskinbox.v1.domain.repo.TaskActivityRepository;
import com.taskinbox.v1.support.errors.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
@RequiredArgsConstructor
@Slf4j
public class TaskEventConsumer {

    private final TaskActivityRepository repository;

    @KafkaListener(
            topics = {"task.created.v1", "task.status-updated.v1"},
            groupId = "taskinbox-group",
            errorHandler = "topicoErrorHandler",
            containerFactory = "filterKafkaListenerContainerFactory"
    )
    @Transactional
    public void consume(TaskEvent event) {

        Object payload = event.getPayload();

        if (payload instanceof TaskCreateEvent e) {
            saveIdempotent(map(e), e.getEventId());
            return;
        }

        if (payload instanceof TaskUpdateEvent e) {
            saveIdempotent(map(e), e.getEventId());
            return;
        }

        log.warn("Evento ignorado: payload={}", payload);
    }

    private void saveIdempotent(TaskActivity activity, String eventId) {
        try {
            repository.save(activity);
        } catch (org.springframework.dao.DuplicateKeyException ex) {
            log.info("Duplicado ignorado eventId={}", eventId);
        }
    }



    private TaskActivity map(TaskCreateEvent e) {
        return TaskActivity.builder()
                .eventId(e.getEventId())
                .type(e.getType())
                .ownerId(e.getOwnerId())
                .createdAt(e.getCreatedAt())
                .status(e.getStatus())
                .build();
    }

    private TaskActivity map(TaskUpdateEvent e) {
        return TaskActivity.builder()
                .eventId(e.getEventId())
                .type(e.getType())
                .ownerId(e.getOwnerId())
                .createdAt(e.getCreatedAt())
                .status(e.getStatus())
                .build();
    }
}

