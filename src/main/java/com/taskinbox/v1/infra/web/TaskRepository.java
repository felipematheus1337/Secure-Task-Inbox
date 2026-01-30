package com.taskinbox.v1.infra.web;

import com.taskinbox.v1.domain.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<String, Task> {
}
