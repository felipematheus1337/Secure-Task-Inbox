package com.taskinbox.v1.infra.controller;

import com.taskinbox.v1.domain.service.TaskService;
import com.taskinbox.v1.infra.dtos.TaskRequest;
import com.taskinbox.v1.infra.dtos.TaskResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService service;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<TaskResponse> createTask(@RequestHeader(name = "X-Correlation-Id") String correlationId,
            @Valid @RequestBody TaskRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<TaskResponse>> list(@RequestHeader(name = "X-Correlation-Id") String correlationId) {

        return ResponseEntity.ok(service.list());

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> update(@RequestHeader(name = "X-Correlation-Id") String correlationId, @PathVariable String id,
                                       @RequestParam("status") String status) {

        service.update(id, status);

        return ResponseEntity.noContent().build();

    }



}
