package model;

/**
 * This is the superclass person
 */
public abstract class Person {
    private String firstName;
    private String surname;

    /**
     * constructs a person from the given params.
     * @param firstName
     * @param surname
     */
    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     *
     * @return person first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This is a setter method to set the first name of the person
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return person surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * This is a setter method to set the surname of the person
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
