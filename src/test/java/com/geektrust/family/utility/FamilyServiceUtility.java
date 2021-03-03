package com.geektrust.family.utility;

import com.geektrust.family.enums.Gender;
import com.geektrust.family.model.Person;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility class to verify the result of test cases
 * for ex: we can use the findMember method to verify
 * if the child/spouse addition succeeded or not
 */
public class FamilyServiceUtility {

    /**
     * static method to search the person added as part of test cases
     *
     * @param name
     * @return person
     */
    public static Person findMember(String name, Person head) {
        if (name == null || head == null) {
            return null;
        }

        Person result = null;
        if (name.equals(head.getName())) {
            return head;
        } else if (head.getSpouse() != null && name.equals(head.getSpouse().getName())) {
            return head.getSpouse();
        }

        Set<Person> childList = new HashSet<>();
        if (head.getGender() == Gender.FEMALE) {
            childList = head.getChildren();
        } else if (head.getSpouse() != null) {
            childList = head.getSpouse().getChildren();
        }

        for (Person person : childList) {
            result = findMember(name, person);
            if (result != null) {
                break;
            }
        }
        return result;
    }
}
