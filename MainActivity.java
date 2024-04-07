package com.example.finnishtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private TextView textViewStart, textViewStat, textViewResultText, textViewResult;
    private ArrayList<String[]> statisticList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String result = getDataDB();
        textViewStart = (TextView) findViewById(R.id.textViewStart);
        textViewStart.setText("Начать тест");
        textViewStat = (TextView) findViewById(R.id.textViewStat);
        textViewStat.setText("Посмотреть статистику");
        textViewResultText = (TextView) findViewById(R.id.textViewResultText);
        textViewResultText.setText("Предыдущий результат:");
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        if(result.isEmpty()){
            textViewResult.setText("Вы ещё не проходили тест");
        }else {
            textViewResult.setText(result + " слов");
        }
    }

    private String getDataDB(){
        ArrayList<DataEntity> elementList = (ArrayList<DataEntity>) AppDatabase.getAppDataBase(getApplicationContext()).getDao().getAll();
        statisticList = new ArrayList<>();
        if(!elementList.isEmpty()){
        for(DataEntity element: elementList){
            String[] resultStr = {String.valueOf(element.date), String.valueOf(element.result)};
            statisticList.add(resultStr);
        }
        String result = statisticList.get(statisticList.size()-1)[1];
        return result;
        }
        return "";
    }

    public void onClickStart(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
    }

    public void onClickStat(View view){
        Intent intent = new Intent(this, StatisticsActivity.class);

        DataDB dataDB = new DataDB(statisticList);
        intent.putExtra(DataLevel.class.getSimpleName(), dataDB);

        overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
        startActivity(intent);

    }
}