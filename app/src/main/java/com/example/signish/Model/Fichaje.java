package com.example.signish.Model;

import java.util.Date;

public class Fichaje {
    private Date currentTime;

    public Fichaje(Date currentTime) {
        this.currentTime = currentTime;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
}
