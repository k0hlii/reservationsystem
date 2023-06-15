package model;

import java.util.Date;

public class SharedDataModel {
    private static int court;
    private static Date date;

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        SharedDataModel.date = date;
    }

    public  int getCourt() {
        return court;
    }

    public  void setCourt(int court) {
        SharedDataModel.court = court;
    }

}

