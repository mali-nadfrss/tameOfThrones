package com.geektrust.family.enums;


/**
 * enum for gender, male and female are added now.
 */
public enum Gender {
    MALE("Male"), FEMALE("Female");

    public String getValue() {
        return value;
    }

    private String value;

    Gender(final String value) {
        this.value = value;
    }

    public static Gender fromValue(final String value) {
        for (Gender gender : Gender.values()) {
            if (gender.getValue().equals(value)) {
                return gender;
            }
        }
        return null;
    }
}
