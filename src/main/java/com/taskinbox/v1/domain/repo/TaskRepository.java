package com.taskinbox.v1.domain.repo;

import com.taskinbox.v1.domain.model.Task;
import com.taskinbox.v1.domain.model.enumerations.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

}
