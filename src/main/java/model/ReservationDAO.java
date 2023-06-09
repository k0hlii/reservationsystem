package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReservationDAO {
    public static ObservableList<Reservation> getReservations() {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();

        Connection con;
        try {
            con = DBConnector.connect();
            String sql = "SELECT * FROM Reservierungen";

            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("ReservierungsID"),
                        rs.getInt("AnzahlPlätze"),
                        rs.getInt("AnzahlEinheiten"),
                        rs.getFloat("Preis"),
                        rs.getDate("Datum"),
                        rs.getTime("Uhrzeit"),
                        rs.getInt("Platz"),
                        rs.getInt("KundenID"),
                        rs.getString("Zustand")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return reservations;
    }

    public static ObservableList<Reservation> getReservations(Date date) {
        java.util.Date utilDate = date; // Assuming r.date is a java.util.Date object

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        ObservableList<Reservation> reservations = FXCollections.observableArrayList();

        Connection con;
        try {
            con = DBConnector.connect();
            String sql = "SELECT * FROM Reservierungen WHERE Datum = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDate(1, sqlDate);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(
                    rs.getInt("ReservierungsID"),
                    rs.getInt("AnzahlPlätze"),
                    rs.getInt("AnzahlEinheiten"),
                    rs.getFloat("Preis"),
                    rs.getDate("Datum"),
                    rs.getTime("Uhrzeit"),
                    rs.getInt("Platz"),
                    rs.getInt("KundenID"),
                    rs.getString("Zustand")
                    )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return reservations;
    }


    public static void add(Reservation r) {
        try (Connection con = DBConnector.connect()) {
            java.util.Date utilDate = r.date;

            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            String sql = "INSERT INTO Reservierungen (AnzahlPlätze, AnzahlEinheiten, Preis, Datum, Uhrzeit, Platz, KundenID, Zustand) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, r.court_count);
            preparedStatement.setInt(2, r.sessions);
            preparedStatement.setDouble(3, r.price);
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5, "09:00:00");
            preparedStatement.setInt(6, r.court);
            preparedStatement.setInt(7, r.cusomerID);
            preparedStatement.setString(8, "reserved");

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error executing SQL: " + ex.getMessage());
        }

    }
    public static void update(Reservation r) {
        Connection con;

        try {
            con = DBConnector.connect();
            String sql = "UPDATE Reservierungen SET Zustand = '" + r.state + "' WHERE ReservierungsID = " + r.id;
            con.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
