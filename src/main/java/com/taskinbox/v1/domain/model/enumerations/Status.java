package com.taskinbox.v1.domain.model.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    OPEN("OPEN"),
    DOING("DOING"),
    DONE("DONE");


    private final String status;
}
