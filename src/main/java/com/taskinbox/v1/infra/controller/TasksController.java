package com.taskinbox.v1.infra.controller;

import com.taskinbox.v1.infra.dtos.RequestTask;
import com.taskinbox.v1.infra.dtos.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TasksController {


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskResponse> createTask(@RequestBody RequestTask request) {

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<?>> list() {

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id,
                                       @RequestParam("status") String status) {

    }



}
