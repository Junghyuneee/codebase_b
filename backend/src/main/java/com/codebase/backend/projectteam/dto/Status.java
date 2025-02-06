package com.codebase.backend.projectteam.dto;

import java.util.Arrays;

public enum Status {
    PENDING,
    ACCEPTED,
    REJECTED,
    UNKNOWN;
    
    public static Status fromString(String inputStatus) {
        return Arrays.stream(Status.values())
                     .filter(status -> status.name().equalsIgnoreCase(inputStatus))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Invalid Status: " + inputStatus));
    }
}
