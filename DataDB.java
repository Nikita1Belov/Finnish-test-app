package com.example.finnishtest;

import java.io.Serializable;
import java.util.ArrayList;

public class DataDB implements Serializable {
    private ArrayList<String[]> statisticList;

    public DataDB(ArrayList<String[]> statisticList){
        this.statisticList = statisticList;
    }

    public ArrayList<String[]> getDB() {
        return statisticList;
    }

    public void setDB(ArrayList<String[]> statisticList) {
        this.statisticList = statisticList;
    }
}

