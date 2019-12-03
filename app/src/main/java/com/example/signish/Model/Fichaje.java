package com.example.signish.Model;

import java.io.Serializable;
import java.util.Date;

public class Fichaje  implements Serializable {
    private String currentTime;

    public Fichaje(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
