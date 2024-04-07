package com.example.finnishtest;
import java.io.Serializable;

public class DataPoints implements Serializable {
    private String points;

    public DataPoints(String points){
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}

