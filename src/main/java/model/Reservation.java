package model;

import java.sql.Time;
import java.util.Date;

public class Reservation {

    public int court;
    public int court_count;
    public int sessions;
    public Customer customer;
    public java.util.Date date;
    int id;
    float price;
    int cusomerID;
    String state;

    public Reservation(int court, int court_count, int sessions, Customer customer, Date date) {
        this.court = court;
        this.court_count = court_count;
        this.sessions = sessions;
        this.customer = customer;
        this.date = date;
    }

    public Reservation( int anzahlPlätze, int anzahlEinheiten, float preis, Date datum, Time uhrzeit, int platz, int kundenID, String zustand) {
        this.court_count = anzahlPlätze;
        this.sessions = anzahlEinheiten;
        this.price = preis;
        this.date = datum;
        this.cusomerID = kundenID;
        this.state = zustand;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "court=" + court +
                ", court_count=" + court_count +
                ", sessions=" + sessions +
                ", customer=" + customer +
                ", date=" + date +
                '}';
    }
}
