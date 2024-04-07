package com.example.finnishtest;

import java.io.Serializable;
import java.util.ArrayList;

public class DataList implements Serializable {
    private ArrayList<String> unCheckedWordsF;

    public DataList(ArrayList<String> unCheckedWordsF){
        this.unCheckedWordsF = unCheckedWordsF;
    }

    public ArrayList<String> getWordList() {
        return unCheckedWordsF;
    }

    public void setWordList(ArrayList<String> unCheckedWordsF) {
        this.unCheckedWordsF = unCheckedWordsF;
    }
}
