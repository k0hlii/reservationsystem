package com.example.controllsdemo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerDAO;

public class Customermenu
{
    @javafx.fxml.FXML
    private TextField inpVorname;
    @javafx.fxml.FXML
    private TextField inpNachname;
    @javafx.fxml.FXML
    private TextField inpEmail;
    @javafx.fxml.FXML
    private Button btnSubmit;
    @javafx.fxml.FXML
    private Button btnCancel;
    @javafx.fxml.FXML
    private TextField inpTelNr;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handlSubmit(ActionEvent actionEvent) {
        if (inpVorname.getText().isEmpty() || inpNachname.getText().isEmpty() || inpEmail.getText().isEmpty() || inpTelNr.getText().isEmpty()) {
            return;
        }
        Customer customer = new Customer(inpVorname.getText(), inpNachname.getText(), inpEmail.getText(), inpTelNr.getText(), 1);
        CustomerDAO.add(customer);

        Stage currentStage = (Stage) btnCancel.getScene().getWindow();
        currentStage.close();
    }

    @javafx.fxml.FXML
    public void handlebtnCancel(ActionEvent actionEvent) {
        Stage currentStage = (Stage) btnCancel.getScene().getWindow();
        currentStage.close();
    }
}