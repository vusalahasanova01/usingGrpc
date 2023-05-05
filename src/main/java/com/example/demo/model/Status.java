package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {
    INACTIVE("INACTIVE", 0),
    ACTIVE("ACTIVE", 1),
    DELETED("DELETED", 3);

    @Getter
    private final String name;

    @Getter
    private final int id;


}
