package com.taskinbox.v1.infra.dtos;

import com.taskinbox.v1.domain.model.enumerations.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StatusTaskValidator implements ConstraintValidator<StatusValidator, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isBlank()) {
            return false;
        }

       return Arrays.stream(Status.values())
               .anyMatch(status -> status.name().equals(value));
    }
}
