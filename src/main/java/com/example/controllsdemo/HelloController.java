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
import java.util.ResourceBundle;

public class HelloController  {

//    private SimpleListProperty listProperty = new SimpleListProperty();
//
//    private ObservableList<Person> persons;
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        persons = PersonDAO.getPersons();
//
//        cbPersons.itemsProperty().bind(listProperty);
//        listProperty.setValue(persons);
//
//        spYob.setValueFactory(
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(1958,2005)
//       );
//
//        cbCountrys.setItems(CountryDAO.getCountrys());
//    }
//
//    @Deprecated
//    public void handleCbPersonsAction(ActionEvent actionEvent) {
//        Person p = cbPersons.getSelectionModel().getSelectedItem();
//        System.out.println(p.getId() +" "+ p);
//    }
//
//    @Deprecated
//    public void handleMiEditAction(ActionEvent actionEvent) {
//        Person p = cbPersons.getSelectionModel().getSelectedItem();
//        if (p == null)
//            return;
//
//        tfFirstName.setText(p.getFirstname());
//        tfLastName.setText(p.getLastname());
//        spYob.getValueFactory().setValue(p.getBirthyear());
//
////        for (Toggle tg: tgCountrys.getToggles()) {
////            if (((RadioButton)tg).getText().equals(p.getCountry()))
////                tgCountrys.selectToggle(tg);
////        }
//    }
//
//    @Deprecated
//    public void handleBtnSaveAction(ActionEvent actionEvent) {
//        Person p = cbPersons.getSelectionModel().getSelectedItem();
//
//        if (p ==null)
//            return;
//
//        p.setFirstname(tfFirstName.getText());
//        p.setLastname(tfLastName.getText());
//        p.setBirthyear((Integer) spYob.getValue());
//        //p.setCountry(((RadioButton)tgCountrys.getSelectedToggle()).getText());
//
//        int x = cbPersons.getSelectionModel().getSelectedIndex();
//        persons.set(x,p);
//        PersonDAO.update(p);
//    }
}