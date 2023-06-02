package model;

public class Customer {
    int id;
    public String firstname;
    public String lastname;
    String phone;
    String email;
    int addressID;

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Customer(int id, String firstname, String lastname, String phone, String email, int addressID) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.addressID = addressID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
