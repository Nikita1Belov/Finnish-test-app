package com.example.finnishtest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.view.View;


public class WordActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private String points;
    private ArrayList<String> unCheckedWordsF;
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Bundle arguments = getIntent().getExtras();
        assert arguments != null;

        DataPoints dataPoints = (DataPoints) arguments.getSerializable(DataPoints.class.getSimpleName());
        assert dataPoints != null;
        points = dataPoints.getPoints();

        DataList dataWordList = (DataList) arguments.getSerializable(DataList.class.getSimpleName());
        assert dataWordList != null;
        unCheckedWordsF = dataWordList.getWordList();

        DataLevel dataLevel = (DataLevel) arguments.getSerializable(DataLevel.class.getSimpleName());
        assert dataLevel != null;
        level = dataLevel.getLevel();

        ListView list = (ListView) findViewById(R.id.wordListItem);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, unCheckedWordsF);
        list.setAdapter(adapter);
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, ResultActivity.class);

        DataPoints dataPoints = new DataPoints(points);
        intent.putExtra(DataPoints.class.getSimpleName(), dataPoints);

        DataList dataWordList = new DataList(unCheckedWordsF);
        intent.putExtra(DataList.class.getSimpleName(), dataWordList);

        DataLevel dataLevel = new DataLevel(level);
        intent.putExtra(DataLevel.class.getSimpleName(), dataLevel);

        overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
        startActivity(intent);
    }
}