package com.aby.roomforall.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Idate implements Serializable {

    private String date;
    private String time;

    public Idate() {}

    public Idate(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public Idate(Date date, Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
        this.date = sdf.format(date);
        this.time = stf.format(time);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
