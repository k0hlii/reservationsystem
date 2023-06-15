package com.example.controllsdemo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import model.Reservation;
import model.ReservationDAO;
import model.SharedDataModel;

import java.sql.Time;
import java.util.Date;

public class Paymenu
{
    @javafx.fxml.FXML
    private TextField tfCustomer;
    @javafx.fxml.FXML
    private TextField tfPrice;
    @javafx.fxml.FXML
    private Button btnPay;
    @javafx.fxml.FXML
    private Button btnCancel;
    @javafx.fxml.FXML
    private TextField tfCntSessions;

    @javafx.fxml.FXML
    public void initialize() {
        Reservation reservation = SharedDataModel.getReservation();
        int sessions = reservation.sessions * reservation.court_count;
        double price;
        if (sessions ==1) {
            price = 19.9;
        }
        else {
            price =19.9 +(sessions-1) * 9.95;
        }

        tfCustomer.setText(reservation.customer.firstname + " " + reservation.customer.lastname);
        tfPrice.setText(String.valueOf(String.format("%.2f", price) + "€"));
        tfCntSessions.setText(String.valueOf(sessions));
    }

    @javafx.fxml.FXML
    public void handlePay(ActionEvent actionEvent) {
        SharedDataModel.getReservation().state = "paid";
        ReservationDAO.update(SharedDataModel.getReservation());

        Stage currentStage = (Stage) btnCancel.getScene().getWindow();
        currentStage.close();
    }

    @javafx.fxml.FXML
    public void handlebtnCancel(ActionEvent actionEvent) {
        Stage currentStage = (Stage) btnCancel.getScene().getWindow();
        currentStage.close();
    }
}