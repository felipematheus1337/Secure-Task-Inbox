package com.taskinbox.v1.domain.service;

import com.taskinbox.v1.domain.model.Task;
import com.taskinbox.v1.domain.model.enumerations.Status;
import com.taskinbox.v1.domain.repo.TaskRepository;
import com.taskinbox.v1.infra.dtos.RequestTask;
import com.taskinbox.v1.infra.dtos.TaskResponse;
import com.taskinbox.v1.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;


    @Transactional
    public TaskResponse create(RequestTask request) {

        Task task = this.mapper.toEntity(request);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());

        return mapper.entityToResponse(taskRepository.save(task));

    }


    public List<TaskResponse> list() {
        return taskRepository.findAll()
                .stream()
                .map(mapper::entityToResponse)
                .toList();
    }


    @Transactional
    public void update(String id, String status) {

        Task task = taskRepository.findById(id)
                .orElseThrow();

        task.setStatus(Status.valueOf(status));

    }


}
