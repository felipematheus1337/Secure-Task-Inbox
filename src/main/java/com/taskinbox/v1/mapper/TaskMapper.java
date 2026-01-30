package com.taskinbox.v1.mapper;

import com.taskinbox.v1.domain.model.Task;
import com.taskinbox.v1.infra.dtos.RequestTask;
import com.taskinbox.v1.infra.dtos.TaskResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(RequestTask requestTask);

    TaskResponse entityToResponse(Task task);
}
