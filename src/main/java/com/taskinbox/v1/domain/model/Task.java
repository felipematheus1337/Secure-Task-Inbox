package com.taskinbox.v1.domain.model;

import com.taskinbox.v1.domain.model.enumerations.Status;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "tasks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    private String id;

    private String ownerId;

    private String title;

    private String description;

    private Status status;

    @Nullable
    private List<String> tags;

    private Instant createdAt;

    private Instant updatedAt;
}
