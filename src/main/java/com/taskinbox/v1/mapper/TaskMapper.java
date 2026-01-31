package com.taskinbox.v1.mapper;

import com.taskinbox.v1.domain.model.Task;
import com.taskinbox.v1.domain.model.enumerations.Status;
import com.taskinbox.v1.infra.dtos.TaskRequest;
import com.taskinbox.v1.infra.dtos.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse entityToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .ownerId(task.getOwnerId())
                .title(task.getTitle())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .tags(task.getTags())
                .description(task.getDescription())
                .build();
    }

    public Task toEntity(TaskRequest request) {
        return Task.builder()
                .ownerId(request.ownerId())
                .title(request.title())
                .status(Status.valueOf(request.status()))
                .tags(request.tags())
                .build();
    }
}
