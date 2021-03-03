package com.geektrust.family.enums;

import static com.geektrust.family.constants.StringConstants.INVALID_RELATIONSHIP;

/**
 * enum for relations like son, daughter etc.
 */
public enum Relation {
    SON("Son"),
    DAUGHTER("Daughter"),
    SIBLINGS("Siblings"),
    BROTHER_IN_LAW("Brother-In-Law"),
    SISTER_IN_LAW("Sister-In-Law"),
    MATERNAL_AUNT("Maternal-Aunt"),
    PATERNAL_AUNT("Paternal-Aunt"),
    MATERNAL_UNCLE("Maternal-Uncle"),
    PATERNAL_UNCLE("Paternal-Uncle");

    public String getValue() {
        return value;
    }

    private String value;

    Relation(final String value) {
        this.value = value;
    }

    /**
     * static method to search for the relation from above enum values.
     *
     * @param value - name of relation to find
     * @return - relation name
     */
    public static Relation fromValue(final String value) {
        if (value == null) {
            System.out.println(INVALID_RELATIONSHIP);
            return null;
        }
        for (Relation relation : Relation.values()) {
            if (relation.getValue().equals(value)) {
                return relation;
            }
        }
        System.out.println(INVALID_RELATIONSHIP);
        return null;
    }
}
