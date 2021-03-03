package com.geektrust.family.service;

import com.geektrust.family.model.Person;

/** .
 * interface to define service layer methods
 */
public interface FamilyService {

    /**.
     * add child to given person
     *
     * @param parent - parent name for whom child has to be added
     * @param child  - child to be added
     * @param gender - gender of child
     */
    void addChild(String parent, String child, String gender);

    /** add spouse to given person.
     *
     * @param partner - partner name for whom spouse to be added
     * @param spouse  - spouse to be added
     * @param gender  - gender of child
     */
    void addSpouse(String partner, String spouse, String gender);

    /**.
     * finds names of persons holding given relationship
     *
     * @param name         - name of the person
     * @param relationship - like Son, Daughter etc
     * @return names of all the persons with given relationship
     */
    String getRelationShip(String name, String relationship);

    /**.
     * search for the person name starting from the Queen
     *
     * @param name - name of person to search for
     * @param head - head of person
     * @return Person
     */
    Person findMember(String name, Person head);

}
