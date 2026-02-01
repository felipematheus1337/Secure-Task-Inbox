package com.taskinbox.v1.infra.dtos;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusTaskValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusValidator {

    String message() default "Invalid Status.";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
