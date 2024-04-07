package com.example.finnishtest;

import java.io.Serializable;

public class DataLevel implements Serializable {
    private String level;

    public DataLevel(String level){
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
