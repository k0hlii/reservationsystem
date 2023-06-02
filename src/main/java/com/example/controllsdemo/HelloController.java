package com.example.controllsdemo;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import reservationmenu.FXMLReservationmenu_Controller;


import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        datepicker.setValue(LocalDate.now());
        weekdaysButtons = new Button[]{btnMo, btnDi, btnMi, btnDo, btnFr, btnSa, btnSo};
        updateWeekdays();

        reservationPanes[0] = createPane(new Reservation(0,1,1,new Customer("John", "Doe"),new Date()));
        reservationPanes[16] = createPane(new Reservation(16,1,1,new Customer("John", "Doe"),new Date()));
        reservationPanes[32] = createPane(new Reservation(32,1,1,new Customer("John", "Doe"),new Date()));

        DayReservations date = new DayReservations(datepicker.getValue(), reservationPanes);


        Pane[] reservationPanes2 = new Pane[200];

        reservationPanes2[12] = createPane(new Reservation(12,1,1,new Customer("John", "Schnee"),new Date()));
        reservationPanes2[12*2] = createPane(new Reservation(12*2,1,1,new Customer("John", "Stark"),new Date()));
        reservationPanes2[12*3] = createPane(new Reservation(12*3,1,1,new Customer("Jürgen", "Schmidt"),new Date()));

        DayReservations date2 = new DayReservations(datepicker.getValue().plusDays(1), reservationPanes2);

        dates[0] = date;
        dates[1] = date2;
        loadReservationsDay();

        addClass(reservationPanes[0], "abo");

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

    private void loadReservationsDay() {
        grid.getChildren().removeAll(reservationPanes);

        for (int i = 0; i < dates.length; i++) {
            if (dates[i] != null) {
                System.out.println("date: "+dates[i].date + " datepicker: "+datepicker.getValue() + " i: "+i);
            }
            if (dates[i] != null && dates[i].date.equals(datepicker.getValue()) ) {
                System.out.println("date: "+dates[i].date + " datepicker: "+datepicker.getValue() + " i: "+i);
                reservationPanes = dates[i].reservations;
                loadReservations(reservationPanes);
                return;
            }
        }
        grid.getChildren().removeAll(reservationPanes);
    }

    void handleReservationButton(ActionEvent actionEvent)
    {
        Button button = (Button) actionEvent.getSource();

        int court = Integer.parseInt(button.getText());
        Reservation reservation = new Reservation(court,1,1,new Customer("John", "Doe"),new Date());
        createPane(reservation);
        reservationPanes[court] = createPane(reservation);

        loadReservations(reservationPanes);
        int count = 0;
        for (int i = 0; i < 200; i++) {
            if (reservationPanes[i] != null) {
                count++;
            }
        }

        System.out.println("count: "+count);

//    try {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("src/main/java/reservationmenu/FXML_reservationmenu.fxml"));
//        Parent root = loader.load();                            //Wurzelcontrol
//        FXMLReservationmenu_Controller ctrl = loader.getController();  //ref. Controlerobj
////        ctrl.setPerson(actPerson);
//        Stage stage = new Stage();
//        stage.initModality(Modality.WINDOW_MODAL);              //im Vordergrund
//        stage.setScene(new Scene(root));
//        stage.showAndWait();                                    //Anzeige
//        System.out.println("after Dialog: ");
////        myPersonList.set(inx, actPerson);
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }
    }

    @FXML
    public void handleDatePicker(ActionEvent actionEvent)
    {
        datepicker.setValue(datepicker.getValue());
        updateWeekdays();
        loadReservationsDay();
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

        grid.setRowSpan(pane,reservation.sessions);
        grid.setColumnSpan(pane,reservation.court_count);

        if (reservation.court%courts >= 5 ) {
            grid.add(pane, reservation.court%courts +3, reservation.court/courts +1);
        }
        else {
            grid.add(pane, reservation.court%courts +1, reservation.court/courts +1);
        }
    }

    public void addClass(Pane pane, String className)
    {
        pane.getStyleClass().add(className);
    }

    public void loadReservations(Pane[] reservationPanes)
    {
        for (int i = 0; i < 200; i++) {
            if (reservationPanes[i] != null) {
                grid.getChildren().remove(reservationPanes[i]);
            }
        }

        for (int i = 0; i < reservationPanes.length; i++) {
            if (reservationPanes[i] != null) {
                if (i%courts >= 5 ) {
                    grid.add(reservationPanes[i], i%courts +3, i/courts +1);
                }
                else {
                    grid.add(reservationPanes[i], i%courts +1, i/courts +1);
                }
            }
        }
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
        loadReservationsDay();
    }

    @FXML
    public void handleDayforward(ActionEvent actionEvent)
    {
        datepicker.setValue(datepicker.getValue().plusDays(1));

        updateWeekdays();
        loadReservationsDay();
    }

    @FXML
    public void handleWeekdaySelect(ActionEvent actionEvent)
    {
        Button btn = (Button) actionEvent.getSource();
        String btnText = btn.getText();

        updateWeekdays();
        loadReservationsDay();

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