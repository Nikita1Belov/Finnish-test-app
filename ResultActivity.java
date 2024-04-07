package com.example.finnishtest;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;


public class ResultActivity extends AppCompatActivity {
    private TextView textViewVocabulary, textViewFinal, textViewText;
    String points;
    private ArrayList<String> unCheckedWordsF;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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

        textViewVocabulary = (TextView) findViewById(R.id.textViewVocabulary);
        textViewVocabulary.setText("Ваш словарный запас:");
        textViewFinal = (TextView) findViewById(R.id.textViewFinal);
        textViewFinal.setText(points + " слов");
        textViewText = (TextView) findViewById(R.id.textViewText);
        textViewText.setText("Это соотвествует уровню " + level);
    }

    public void onClickWord(View view) {
        Intent intent = new Intent(this, WordActivity.class);

        DataPoints dataPoints = new DataPoints(points);
        intent.putExtra(DataPoints.class.getSimpleName(), dataPoints);

        DataList dataWordList = new DataList(unCheckedWordsF);
        intent.putExtra(DataList.class.getSimpleName(), dataWordList);

        DataLevel dataLevel = new DataLevel(level);
        intent.putExtra(DataLevel.class.getSimpleName(), dataLevel);

        overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
        startActivity(intent);
    }

    public void onClickStat(View view){
        Intent intent = new Intent(this, Statistics2Activity.class);

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