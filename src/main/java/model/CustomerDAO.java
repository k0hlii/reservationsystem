package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public static ObservableList<Customer> getCustomer() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        Connection con;
        try {
            con = DBConnector.connect();
            String sql = "SELECT * FROM Reservierungen";

            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                customers.add(new Customer(
                    rs.getInt("KundenID"),
                    rs.getString("Vorname"),
                    rs.getString("Nachname"),
                    rs.getString("Telefonnummer"),
                    rs.getString("Email"),
                    rs.getInt("AdressenID")
                ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return customers;
    }
}
