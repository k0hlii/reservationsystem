package com.example.controllsdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Reservation System");
        stage.setScene(scene);
        stage.show(); //leider show
    }

    public static void main(String[] args) {
        launch();
    }
}