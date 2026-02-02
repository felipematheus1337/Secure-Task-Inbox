package com.taskinbox.v1.domain.model.event;

import com.taskinbox.v1.domain.model.enumerations.TaskEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;

@Document(collection = "task_activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskActivity implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    private String eventId;

    private TaskEventType type;

    private String ownerId;

    private Instant createdAt;

    private String status;
}
