package com.geektrust.family.model;

import com.geektrust.family.enums.Gender;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * .
 * Person will be base model for this project
 * he has name, gender, spouse, children, parent(female)
 * defined as part of model
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1333473940190977421L;

    /**
     * .
     * name of the person
     */
    private String name;

    /**
     * .
     * gender of the person
     */
    private Gender gender;
    /**
     * .
     * set of children of the person
     */
    private Set<Person> children;
    /**
     * .
     * spouse of the person
     */
    private Person spouse;
    /**
     * .
     * female parent of the person
     */
    private Person parent;

    /**
     * .
     *
     * @param name   - name of the person
     * @param gender - gender of the person
     */
    public Person(final String name, final String gender) {
        this.name = name;
        this.gender = Gender.fromValue(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    /**
     * <p>
     * get female parent and then return children
     *
     * @return - children
     */
    public Set<Person> getChildren() {
        if (Gender.MALE.equals(this.getGender()) && this.getSpouse() != null) {
            return this.getSpouse().getChildren();
        }
        if (this.children == null) {
            children = new HashSet<>();
        }
        return this.children;
    }

    public void setChildren(final Set<Person> children) {
        this.children = children;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(final Person spouse) {
        this.spouse = spouse;
    }

    public Person getParent() {
        return parent;
    }

    /**
     * add children to female parent only
     *
     * @param parent - person
     */
    public void setParent(final Person parent) {
        if (parent != null && Gender.FEMALE.equals(parent.getGender())) {
            this.parent = parent;
        }
    }


    /**
     * .
     * overriding the toString method to print its contents instead of address
     *
     * @return - contents of person
     */
    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", gender=" + gender
                + ", children=" + children
                + ", spouse=" + spouse
                + ", parent=" + parent
                + '}';
    }

    /**
     * .
     * Overriding the equals method to make sure that duplicates are not
     * inserted into the system
     *
     * @param o - person object to check equals based on name
     * @return - true if equals
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return name.equals(person.name);
    }

    /**
     * .
     * Overriding the hashcode to maintain equals and hashcode contract
     *
     * @return - hashcode of person name
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
