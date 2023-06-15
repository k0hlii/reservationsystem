package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

import java.sql.Connection;

import java.sql.PreparedStatement;
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
//                reservations.add(new Reservation(
//                        rs.getInt("ReservierungsID"),
//                        rs.getInt("AnzahlPlätze"),
//                        rs.getInt("AnzahlEinheiten"),
//                        rs.getFloat("Preis"),
//                        rs.getDate("Datum"),
//                        rs.getTime("Uhrzeit"),
//                        rs.getInt("Platz"),
//                        rs.getInt("KundenID"),
//                        rs.getString("Zustand")
//                        )
//                );
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return reservations;
    }


    public static void add(Reservation r) {
        try (Connection con = DBConnector.connect()) {
            java.util.Date utilDate = r.date; // Assuming r.date is a java.util.Date object

            // Convert java.util.Date to java.sql.Date
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
            preparedStatement.setString(8, "test");

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully.");
        } catch (SQLException ex) {
            System.err.println("Error executing SQL: " + ex.getMessage());
        }
//        Connection con;
//        try {
//            java.util.Date utilDate = r.date; // Assuming r.date is a java.util.Date object
//
//            // Convert java.util.Date to java.sql.Date
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//            System.out.println("asd "+ sqlDate);
//
//            con = DBConnector.connect();
//            String sql = "INSERT INTO Reservierungen (AnzahlPlätze, AnzahlEinheiten, Preis, Datum, Uhrzeit, Platz, KundenID,Zustand)" +
//                    "VALUES" + "("+ r.court_count+ ","+r.sessions +"," +r.price+","+ r.date.getTime()+",'09:00:00'," +r.court +"," +r.cusomerID+", 'test')";
//            con.createStatement().executeUpdate(sql);
//        } catch (SQLException ex) {
//            System.err.println(""+ex.getMessage());
//        }
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
