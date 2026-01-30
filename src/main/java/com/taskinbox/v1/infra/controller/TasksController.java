package com.taskinbox.v1.infra.controller;

import com.taskinbox.v1.domain.service.TaskService;
import com.taskinbox.v1.infra.dtos.RequestTask;
import com.taskinbox.v1.infra.dtos.TaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService service;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody RequestTask request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TaskResponse>> list() {

        return ResponseEntity.ok(service.list());

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable String id,
                                       @RequestParam("status") String status) {

        service.update(id, status);

        return ResponseEntity.noContent().build();

    }



}
