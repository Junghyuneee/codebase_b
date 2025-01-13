package com.codebase.backend.projectteam.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TechStack {
    FRONTEND,
    BACKEND,
    FULLSTACK,
    DESIGN,
    PM,
    ETC;

    @JsonCreator
    public static TechStack fromString(String value) {
        if (value == null|| value.isEmpty()) {
            return null;
        }
        try {
            return TechStack.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value for TechStack: " + value);
        }
    }
}
