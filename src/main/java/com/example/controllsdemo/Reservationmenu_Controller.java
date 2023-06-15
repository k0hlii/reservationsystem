package com.example.controllsdemo;
import javafx.beans.Observable;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;

import java.util.Date;
import java.sql.Time;
import java.util.ResourceBundle;

public class Reservationmenu_Controller implements Initializable {

    @javafx.fxml.FXML
    private ComboBox cbCustomer;
    @javafx.fxml.FXML
    private Spinner<Integer> spCourts;
    @javafx.fxml.FXML
    private Spinner<Integer> spSessions;
    @javafx.fxml.FXML
    private Button btnSubmit;
    @javafx.fxml.FXML
    private Button btnCancel;
    @FXML
    private Button btnAddCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        SimpleListProperty listProperty = new SimpleListProperty();
        ObservableList<Customer> customers = CustomerDAO.getCustomer();

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

    @FXML
    public void handlSubmit(ActionEvent actionEvent) {
        SharedDataModel data = new SharedDataModel();

        float price = 10.5F;

        Date date = data.getDate();
        System.out.println(date);
        Customer customer = (Customer) cbCustomer.getSelectionModel().getSelectedItem();

        String state ="reserved";
        Reservation r = new Reservation(spCourts.getValue(),spSessions.getValue(),price,date,new Time(654),data.getCourt(),customer.id,state);

        ReservationDAO.add(r);

        Stage currentStage = (Stage) btnCancel.getScene().getWindow();
        currentStage.close();


    }

    @javafx.fxml.FXML
    public void handlebtnCancel(ActionEvent actionEvent) {
        Stage currentStage = (Stage) btnCancel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    public void handleAddCustomer(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customermenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Reservation System");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            SimpleListProperty listProperty = new SimpleListProperty();
            ObservableList<Customer> customers = CustomerDAO.getCustomer();

            listProperty.setValue(customers);
            cbCustomer.itemsProperty().bind(listProperty);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
