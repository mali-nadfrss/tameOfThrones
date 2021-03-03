package com.geektrust.family.serviceImpl;

import com.geektrust.family.Geektrust;
import com.geektrust.family.enums.Gender;
import com.geektrust.family.enums.Relation;
import com.geektrust.family.model.Person;
import com.geektrust.family.service.FamilyService;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.geektrust.family.constants.StringConstants.*;

public class FamilyServiceImpl implements FamilyService {

    /**
     * add child to given person.
     *
     * @param parent - parent to whom child has to be added
     * @param child  -  child to be added
     * @param gender - gender of child above
     */
    @Override
    public void addChild(@NotNull final String parent, @NotNull final String child, @NotNull final String gender) {
        Person person = findMember(parent, Geektrust.FAMILY_HEAD);
        if (person == null || Gender.fromValue(gender) == null) {
            System.out.println(CHILD_ADDITION_FAILED);
            return;
        }
        Person childObj = new Person(child, gender);
        if (Gender.FEMALE.equals(person.getGender())) {
            childObj.setParent(person);
            person.getChildren().add(childObj);
            System.out.println(CHILD_ADDITION_SUCCEEDED);
            return;
        } else if (person.getSpouse() != null) {
            childObj.setParent(person.getSpouse());
            person.getSpouse().getChildren().add(childObj);
            System.out.println(CHILD_ADDITION_SUCCEEDED);
            return;
        }
        System.out.println(CHILD_ADDITION_FAILED);
    }

    /**
     * add spouse to given person.
     *
     * @param partner - partner for whom spouse to add
     * @param spouse  -  spouse to be added
     * @param gender  -  gender of spouse
     */
    @Override
    public void addSpouse(@NotNull final String partner, @NotNull final String spouse, @NotNull final String gender) {
        Person person = findMember(partner, Geektrust.FAMILY_HEAD);
        if (Gender.fromValue(gender) == null || person == null || person.getGender().equals(Gender.fromValue(gender))) {
            System.out.println(SPOUSE_ADDITION_FAILED);
            return;
        }
        Person spouseObj = new Person(spouse, gender);
        person.setSpouse(spouseObj);
        spouseObj.setSpouse(person);
        System.out.println(SPOUSE_ADDITION_SUCCEEDED);
    }

    /**
     * finds names of persons holding given relationship.
     *
     * @param name         - name of the person
     * @param relationship - like Son, Daughter etc
     * @return names of all the persons with given relationship
     */
    @Override
    public String getRelationShip(@NotNull final String name, @NotNull final String relationship) {
        Person person = findMember(name, Geektrust.FAMILY_HEAD);
        Relation relation = Relation.fromValue(relationship);
        if (person == null || relation == null) {
            return null;
        }
        Set<Person> result;
        switch (relation) {
            case DAUGHTER:
                result = getChildrenWithGender(person, Gender.FEMALE);
                break;
            case SON:
                result = getChildrenWithGender(person, Gender.MALE);
                break;
            case SIBLINGS:
                result = getSiblings(person);
                break;
            case PATERNAL_UNCLE:
                result = getPaternalUncle(person);
                break;
            case MATERNAL_AUNT:
                result = getMaternalAunt(person);
                break;
            case PATERNAL_AUNT:
                result = getPaternalAunt(person);
                break;
            case SISTER_IN_LAW:
                result = getSisterInLaws(person);
                break;
            case BROTHER_IN_LAW:
                result = getBrotherInLaws(person);
                break;
            case MATERNAL_UNCLE:
                result = getMaternalUncle(person);
                break;
            default:
                result = new HashSet<>();
                break;
        }
        StringBuilder sb = new StringBuilder();
        if (result.isEmpty()) {
            System.out.println(NONE);
        } else {
            result.removeAll(Collections.singleton(null));
            result.forEach(person1 -> sb.append(person1.getName()).append(EMPTY_SPACE));
            System.out.println(sb.toString());
        }
        return sb.toString().trim();
    }

    /**
     * Spouse’s brothers, Husbands of siblings.
     *
     * @param person - person
     * @return - person
     */
    public Set<Person> getBrotherInLaws(@NotNull final Person person) {
        Set<Person> inLaws = new HashSet<>();
        if (person.getSpouse() != null) {
            inLaws.addAll(getSiblingsWithGender(Gender.MALE, person.getSpouse()));
            inLaws.addAll(getSiblingsWithGender(Gender.FEMALE, person.getSpouse()).stream().map(Person::getSpouse).collect(Collectors.toSet()));
        }
        inLaws.addAll(getSiblingsWithGender(Gender.FEMALE, person).stream().map(Person::getSpouse).collect(Collectors.toSet()));
        return inLaws;
    }

    /**
     * Spouse’s sisters and Wives of siblings.
     *
     * @param person - person
     * @return - person set
     */
    public Set<Person> getSisterInLaws(@NotNull final Person person) {
        Set<Person> inLaws = new HashSet<>();
        if (person.getSpouse() != null) {
            inLaws.addAll(getSiblingsWithGender(Gender.FEMALE, person.getSpouse()));
            inLaws.addAll(getSiblingsWithGender(Gender.MALE, person.getSpouse()).stream().map(Person::getSpouse).collect(Collectors.toSet()));
        }
        inLaws.addAll(getSiblingsWithGender(Gender.MALE, person).stream().map(Person::getSpouse).collect(Collectors.toSet()));
        return inLaws;
    }

    /**
     * siblings of given gender.
     *
     * @param gender - gender
     * @param person - person
     * @return - person set
     */
    public Set<Person> getSiblingsWithGender(@NotNull final Gender gender, @NotNull final Person person) {
        return getSiblings(person).stream().filter(person1 -> gender.equals(person1.getGender())).collect(Collectors.toSet());
    }

    /**
     * Mother’s sisters.
     *
     * @param person - person
     * @return - person set
     */
    public Set<Person> getMaternalAunt(@NotNull final Person person) {
        if (person.getParent() != null) {
            return getSiblingsWithGender(Gender.FEMALE, person.getParent());
        }
        return new HashSet<>();
    }

    /**
     * Father’s sisters.
     *
     * @param person - person
     * @return - person set
     */
    public Set<Person> getPaternalAunt(@NotNull final Person person) {
        if (person.getParent() != null && person.getParent().getSpouse() != null) {
            return getSiblingsWithGender(Gender.FEMALE, person.getParent().getSpouse());
        }
        return new HashSet<>();
    }

    /**
     * Father’s brothers.
     *
     * @param person - person
     * @return - person set
     */
    public Set<Person> getPaternalUncle(@NotNull final Person person) {
        if (person.getParent() != null && person.getParent().getSpouse() != null) {
            return getSiblingsWithGender(Gender.MALE, person.getParent().getSpouse());
        }
        return new HashSet<>();
    }


    /**
     * Mother’s brothers.
     *
     * @param person - person
     * @return - person set
     */
    public Set<Person> getMaternalUncle(@NotNull final Person person) {
        if (person.getParent() != null) {
            return getSiblingsWithGender(Gender.MALE, person.getParent());
        }
        return new HashSet<>();
    }

    /**
     * method to get children of given gender.
     *
     * @param person - person
     * @param gender - gender
     * @return - person set
     */
    public Set<Person> getChildrenWithGender(@NotNull final Person person, @NotNull final Gender gender) {
        return person.getChildren().parallelStream().filter(person1 -> gender.equals(person1.getGender())).collect(Collectors.toSet());
    }

    /**
     * method to get siblings of person.
     *
     * @param person - person
     * @return - person set
     */
    public Set<Person> getSiblings(@NotNull final Person person) {
        if (person.getParent() != null) {
            return person.getParent().getChildren().stream().filter(person1 -> !person1.getName().equals(person.getName())).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }


    /**
     * search for the person name starting from the Queen.
     *
     * @param name - name of person to search for
     * @return Person
     */
    @Override
    public Person findMember(@NotNull final String name, @NotNull final Person head) {
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
