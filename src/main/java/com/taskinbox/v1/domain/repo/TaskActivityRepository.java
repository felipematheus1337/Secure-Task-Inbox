package com.taskinbox.v1.domain.repo;

import com.taskinbox.v1.domain.model.event.TaskActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskActivityRepository extends MongoRepository<TaskActivity, String> {

    boolean existsByEventId(String eventId);
}
