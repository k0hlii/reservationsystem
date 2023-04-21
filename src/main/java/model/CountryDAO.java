package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO {
    public static ObservableList<Country> getCountrys() {
        ObservableList<Country> countrys = FXCollections.observableArrayList(
                new Country(1,"Austria","AT",83858),
                new Country(2,"Germany","DE",357022),
                new Country(3,"Italy","IT",301338)
        );

        Connection con;

        try {
            con = DBConnector.connect();
            String sql = "SELECT id,last_name,first_name,Year(dob) as year,country FROM persons";

            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                countrys.add(new Country(
                        rs.getInt("id"),
                        rs.getString("country"),
                        rs.getString("country_short"),
                        rs.getInt("area")
                ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return countrys;
    }
}
