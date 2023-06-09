package com.example.controllsdemo;
import javafx.beans.Observable;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import model.Customer;

import java.net.URL;

import java.util.ResourceBundle;

public class Reservationmenu_Controller implements Initializable {

    @javafx.fxml.FXML
    private ComboBox cbCustomer;
    @javafx.fxml.FXML
    private Spinner<Integer> spCourts;
    @javafx.fxml.FXML
    private Spinner<Integer> spSessions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        SimpleListProperty listProperty = new SimpleListProperty();
        ObservableList<Customer> customers = FXCollections.observableArrayList(
                new Customer("Test","1"),
                new Customer("Test2","2")
        );

        listProperty.setValue(customers);
        cbCustomer.itemsProperty().bind(listProperty);

        spCourts.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10)
        );
        spSessions.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20)
        );
    }

    @javafx.fxml.FXML
    public void handlecbCustomer() {

    }
}
