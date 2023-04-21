package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnector;
public class PersonDAO {
    public static ObservableList<Person> getPersons() {
        ObservableList<Person> persons = FXCollections.observableArrayList(
                new Person(1,"Stefan","Müller",1995,"AUT"),
                new Person(2,"Jürgen","Huber",1990,"DE"),
                new Person(3,"Lukas","Mayer",1989 ,"IT")
        );

        Connection con;

        try {
            con = DBConnector.connect();
            String sql = "SELECT id,last_name,first_name,Year(dob) as year,country FROM persons";

            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                persons.add(new Person(
                        rs.getInt("id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getInt("year"),
                        rs.getString("country"))
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return persons;
    }

    public static void update(Person p) {
        Connection con;

        try {
            con = DBConnector.connect();
            String sql = "UPDATE persons SET last_name = '" + p.getLastname() + "', first_name = '" + p.getFirstname() + "', dob = '" + p.getBirthyear() + "', country = '" + p.getCountry() + "' WHERE id = " + p.getId();
            con.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
