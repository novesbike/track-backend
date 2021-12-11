package com.hexagonal.api.enums;


import com.hexagonal.api.exceptions.InvalidAttributeException;

public enum GroupLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED;

    public static GroupLevel cast(String x) {
        switch (x) {
            case "beginner":
                return BEGINNER;
            case "intermediate":
                return INTERMEDIATE;
            case "advanced":
                return ADVANCED;
        }

        throw new InvalidAttributeException("Invalid level");
    }
}