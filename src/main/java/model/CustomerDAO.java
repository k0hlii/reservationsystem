package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public static ObservableList<Customer> getCustomer() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        Connection con;
        try {
            con = DBConnector.connect();
            String sql = "SELECT * FROM Kunde";

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

    public static Customer getCustomerbyID(int id) {
        ObservableList<Customer> customers = getCustomer();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).id == id) {
                return customers.get(i);
            }
        }
        System.out.println("No Customer found");
        return new Customer("nothing","found");
    }

    public static void add(Customer c) {
        try (Connection con = DBConnector.connect()) {

            String sql = "INSERT INTO Kunde (Vorname, Nachname, Telefonnummer, Email, AdressenID) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, c.firstname);
            preparedStatement.setString(2, c.lastname);
            preparedStatement.setString(3, c.phone);
            preparedStatement.setString(4, c.email);
            preparedStatement.setInt(5, c.addressID);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error executing SQL: " + ex.getMessage());
        }
    }
}
