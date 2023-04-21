package model;

public class Person {
    private int id;
    private String firstname;
    private String lastname;

    int birthyear;

    private String country;

    public Person(int id, String firstname, String lastname, int birthyear) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthyear = birthyear;
    }

    public Person(int id, String firstname, String lastname, int birthyear, String country) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthyear = birthyear;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getBirthyear() {
        return birthyear;
    }

    @Override
    public String toString() {
        return firstname +" "+ lastname;
    }
}
