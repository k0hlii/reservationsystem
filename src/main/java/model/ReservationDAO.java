package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

//    public static void update(Person p) {
//        Connection con;
//
//        try {
//            con = DBConnector.connect();
//            String sql = "UPDATE persons SET last_name = '" + p.getLastname() + "', first_name = '" + p.getFirstname() + "', dob = '" + p.getBirthyear() + "', country = '" + p.getCountry() + "' WHERE id = " + p.getId();
//            con.createStatement().executeUpdate(sql);
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
}
