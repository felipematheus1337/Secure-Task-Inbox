package com.taskinbox.v1.domain.service;

import com.taskinbox.v1.domain.model.Task;
import com.taskinbox.v1.domain.model.enumerations.Status;
import com.taskinbox.v1.domain.model.event.TaskCreateEvent;
import com.taskinbox.v1.domain.repo.TaskRepository;
import com.taskinbox.v1.infra.dtos.TaskRequest;
import com.taskinbox.v1.infra.dtos.TaskResponse;
import com.taskinbox.v1.kafka.producer.TaskEventProducer;
import com.taskinbox.v1.mapper.TaskMapper;
import com.taskinbox.v1.support.errors.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Async
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;
    private final TaskEventProducer taskEventProducer;


    @Transactional
    public TaskResponse create(TaskRequest request) {

        Task task = this.mapper.toEntity(request);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());

        TaskCreateEvent event = TaskCreateEvent
                .builder()
                .createdAt(Instant.now())
                .eventId(UUID.randomUUID().toString())
                .ownerId(task.getOwnerId())
                .status(request.status())
                .build();

        taskEventProducer.send(event);

        return mapper.entityToResponse(taskRepository.save(task));

    }


    @Cacheable("task-list")
    public List<TaskResponse> list() {
        return taskRepository.findAll()
                .stream()
                .map(mapper::entityToResponse)
                .toList();
    }


    @Transactional
    public void update(String id, String status) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Task not found."));

        task.setStatus(Status.valueOf(status));

    }


}
