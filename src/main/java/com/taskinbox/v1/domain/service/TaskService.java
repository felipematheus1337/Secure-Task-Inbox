package com.taskinbox.v1.domain.service;

import com.taskinbox.v1.domain.repo.TaskRepository;
import com.taskinbox.v1.infra.dtos.RequestTask;
import com.taskinbox.v1.infra.dtos.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public @Nullable TaskResponse create(RequestTask request) {
    }


    public @Nullable List<TaskResponse> list() {
    }


    public void update(String id, String status) {
    }


}
