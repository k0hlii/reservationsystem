package com.example.controllsdemo;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Country;
import model.CountryDAO;
import model.Person;
import model.PersonDAO;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.time.LocalDate;

public class HelloController  implements Initializable{
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

    Button[] weekdaysButtons;

    String[] weekdays = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY","SUNDAY"};
    String[] shortWeekdays = {"Mo", "Di", "Mi", "Do", "Fr", "Sa","So"};

    @FXML
    public void handleDatePicker(ActionEvent actionEvent) {
        datepicker.setValue(datepicker.getValue());
        updateWeekdays();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datepicker.setValue(LocalDate.now());
        weekdaysButtons = new Button[]{btnMo, btnDi, btnMi, btnDo, btnFr, btnSa, btnSo};

        for (int i = 0; i < weekdays.length; i++) {
            if (weekdays[i].equals(datepicker.getValue().getDayOfWeek().toString())) {
                weekdaysButtons[i].getStyleClass().add("weekday-selected");
            }
        }
    }

    void updateWeekdays(){
        btnMo.getStyleClass().remove("weekday-selected");
        btnDi.getStyleClass().remove("weekday-selected");
        btnMi.getStyleClass().remove("weekday-selected");
        btnDo.getStyleClass().remove("weekday-selected");
        btnFr.getStyleClass().remove("weekday-selected");
        btnSa.getStyleClass().remove("weekday-selected");
        btnSo.getStyleClass().remove("weekday-selected");

        for (int i = 0; i < weekdays.length; i++) {
            if (weekdays[i].equals(datepicker.getValue().getDayOfWeek().toString())) {
                weekdaysButtons[i].getStyleClass().add("weekday-selected");
            }
        }
    }

    @FXML
    public void handleDayback(ActionEvent actionEvent) {
        datepicker.setValue(datepicker.getValue().minusDays(1));

        updateWeekdays();
    }

    @FXML
    public void handleDayforward(ActionEvent actionEvent) {
        datepicker.setValue(datepicker.getValue().plusDays(1));

        updateWeekdays();
    }

    @FXML
    public void handleWeekdaySelect(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String btnText = btn.getText();

        btnMo.getStyleClass().remove("weekday-selected");
        btnDi.getStyleClass().remove("weekday-selected");
        btnMi.getStyleClass().remove("weekday-selected");
        btnDo.getStyleClass().remove("weekday-selected");
        btnFr.getStyleClass().remove("weekday-selected");
        btnSa.getStyleClass().remove("weekday-selected");
        btnSo.getStyleClass().remove("weekday-selected");

        btn.getStyleClass().add("weekday-selected");

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