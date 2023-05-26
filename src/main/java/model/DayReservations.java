package model;

import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.util.Date;

public class DayReservations {
    public LocalDate date;
    public Pane[] reservations = new Pane[200];

    public DayReservations(LocalDate date, Pane[] reservationPanes) {
        this.date = date;
        this.reservations = reservationPanes;
    }
}
