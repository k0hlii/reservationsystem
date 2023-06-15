package com.example.controllsdemo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;


import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.time.LocalDate;

public class HelloController  implements Initializable {
    @FXML
    private DatePicker datepicker;
    @FXML
    private Button btnDayback;
    @FXML
    private Button btnDayforward;
    @FXML
    private Button btnMo;
    @FXML
    private Button btnDi;
    @FXML
    private Button btnMi;
    @FXML
    private Button btnDo;
    @FXML
    private Button btnFr;
    @FXML
    private Button btnSa;
    @FXML
    private Button btnSo;

    @FXML
    private GridPane grid;

    Button[] weekdaysButtons;
    String[] weekdays = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY","SUNDAY"};
    String[] shortWeekdays = {"Mo", "Di", "Mi", "Do", "Fr", "Sa","So"};
    DayReservations[] dates = new DayReservations[7];

    int courts = 10;

    Pane[] reservationPanes = new Pane[200];

    ObservableList<Reservation> reservations ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        datepicker.setValue(LocalDate.now());
        weekdaysButtons = new Button[]{btnMo, btnDi, btnMi, btnDo, btnFr, btnSa, btnSo};
        updateWeekdays();

        updateReservations();
    }

    private void drawButtons() {
        Button[] reseravtionButtons = new Button[200];
        for (int i = 0; i < 200; i++) {
            reseravtionButtons[i] = new Button(""+i);
            reseravtionButtons[i].setOnAction(this::handleReservationButton);
            reseravtionButtons[i].setOpacity(0);
            reseravtionButtons[i].setPrefHeight(100);
            reseravtionButtons[i].setPrefWidth(100);
            if (i%courts >= 5 ) {
                grid.add(reseravtionButtons[i], i%courts +3, i/courts +1);
            }
            else {
                grid.add(reseravtionButtons[i], i%courts +1, i/courts +1);
            }
        }
    }

    private void updateReservations() {

        grid.getChildren().removeAll(reservationPanes);

        LocalDate localDate = datepicker.getValue();
        System.out.println(localDate);

        // Convert LocalDate to java.util.Date
        Date utilDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        reservations = ReservationDAO.getReservations(utilDate);
        System.out.println(reservations.size());

        reservationPanes = new Pane[200];
        for (int i = 0; i < reservations.size(); i++) {
            displayReservation(reservations.get(i));
        }
        drawButtons();

    }

    void handleReservationButton(ActionEvent actionEvent)
    {
        Button btn = (Button) actionEvent.getSource();

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).court == Integer.parseInt(btn.getText())) {
                System.out.println(reservations.get(i).customer.firstname);
                SharedDataModel data = new SharedDataModel();
                data.setReservation(reservations.get(i));
                try {
                    Stage stage = new Stage();

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paymenu.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setTitle("New Reservation");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();

                    updateReservations();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                finally {
                    return;
                }

            }
        }


        try {
            SharedDataModel data = new SharedDataModel();
            data.setCourt(Integer.parseInt(btn.getText()));

            LocalDate localDate = datepicker.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            SharedDataModel.setDate(date);
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("reservationmenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("New Reservation");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            updateReservations();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void handleDatePicker(ActionEvent actionEvent)
    {
        datepicker.setValue(datepicker.getValue());
        updateWeekdays();
        updateReservations();
    }

    public Pane createPane(Reservation reservation)
    {
        Pane pane = new Pane(new Label(""+reservation.customer.firstname +"\n"+reservation.customer.lastname));
        pane.getStyleClass().add("pane");
        return pane;
    }

    public void displayReservation(Reservation reservation)
    {
        Pane pane = createPane(reservation);
        reservationPanes[reservation.court] = pane;

        if (reservation.court%courts >= 5 ) {
            grid.add(pane, reservation.court%courts +3, reservation.court/courts +1);
        }
        else {
            grid.add(pane, reservation.court%courts +1, reservation.court/courts +1);
        }

        grid.setRowSpan(pane,reservation.sessions);
        grid.setColumnSpan(pane,reservation.court_count);

        try {
            addClass(pane, reservation.state);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addClass(Pane pane, String className)
    {
        pane.getStyleClass().add(className);
    }


    void updateWeekdays()
    {
        for (Button btn :weekdaysButtons) {
            btn.getStyleClass().remove("weekday-selected");
        }

        for (int i = 0; i < weekdays.length; i++) {
            if (weekdays[i].equals(datepicker.getValue().getDayOfWeek().toString())) {
                weekdaysButtons[i].getStyleClass().add("weekday-selected");
            }
        }
    }

    @FXML
    public void handleDayback(ActionEvent actionEvent)
    {
        datepicker.setValue(datepicker.getValue().minusDays(1));

        updateWeekdays();
        updateReservations();
    }

    @FXML
    public void handleDayforward(ActionEvent actionEvent)
    {
        datepicker.setValue(datepicker.getValue().plusDays(1));
        updateWeekdays();
        updateReservations();
    }

    @FXML
    public void handleWeekdaySelect(ActionEvent actionEvent)
    {
        Button btn = (Button) actionEvent.getSource();
        String btnText = btn.getText();

        updateWeekdays();
        updateReservations();


        String dayOfWeek = datepicker.getValue().getDayOfWeek().toString();

        int day1 = 0;
        int day2 = 0;

        for (int i = 0; i < weekdays.length; i++) {
            if (dayOfWeek.equals(weekdays[i])) {
                 day1 = i;
            }
        }

        for (int i = 0; i < shortWeekdays.length; i++) {
            if (btnText.equals(shortWeekdays[i])) {
                day2 = i;
            }
        }

        if (day1 < day2) {
            datepicker.setValue(datepicker.getValue().plusDays(day2 - day1));
        } else if (day1 > day2) {
            datepicker.setValue(datepicker.getValue().minusDays(day1 - day2));
        } else {
            datepicker.setValue(datepicker.getValue());
        }
    }
}