package com.taskinbox.v1.infra.dtos;

import com.taskinbox.v1.domain.model.enumerations.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class StatusTaskValidator implements ConstraintValidator<StatusValidator, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (Status.valueOf(value).getStatus().isBlank()) {
            return false;
        }

        return true;
    }
}
