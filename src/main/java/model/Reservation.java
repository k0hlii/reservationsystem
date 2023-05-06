package model;

import java.util.Date;

public class Reservation {

    public int court;
    public int court_count;
    public int sessions;
    public Customer customer;
    public Date date;

    public Reservation(int court, int court_count, int sessions, Customer customer, Date date) {
        this.court = court;
        this.court_count = court_count;
        this.sessions = sessions;
        this.customer = customer;
        this.date = date;
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
