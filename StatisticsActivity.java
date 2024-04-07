package com.example.finnishtest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StatisticsActivity extends AppCompatActivity {
    private BarChart barChart;
    private TextView textViewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ArrayList<String[]> buffer = getData();

        barChart = findViewById(R.id.mp_barChart);
        barChart.getAxisRight().setDrawLabels(false);
        if(buffer.isEmpty()){
            textViewText = (TextView) findViewById(R.id.textViewText);
            textViewText.setText("Вы ещё не проходили тест");
        }else {
            ArrayList<BarEntry> bars = new ArrayList<>();
            List<String> xValues = new ArrayList<>();
            List<Integer> newBuffer = new ArrayList<>();
            for (int i = 0; i < buffer.size(); i++) {
                bars.add(new BarEntry(i+1, Integer.parseInt(buffer.get(i)[1])));
                newBuffer.add(Integer.parseInt(buffer.get(i)[1]));
                xValues.add(String.valueOf(i));
            }
            float maximum = Collections.max(newBuffer);

            YAxis yAxis = barChart.getAxisLeft();
            yAxis.setAxisMinimum(0f);
            yAxis.setAxisMaximum(maximum);
            yAxis.setZeroLineWidth(2f);
            yAxis.setZeroLineColor(Color.BLACK);
            yAxis.setLabelCount(10);

            BarDataSet dataSet = new BarDataSet(bars, "Your vocabulary");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

            BarData barData = new BarData(dataSet);
            barChart.setData(barData);

            barChart.getDescription().setEnabled(false);
            barChart.invalidate();

            barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter((xValues)));
            barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            barChart.getXAxis().setGranularity(1f);
            barChart.getXAxis().setGranularityEnabled(true);
        }
    }

    @NonNull
    private ArrayList<String[]> getData(){
        ArrayList<DataEntity> elementList = (ArrayList<DataEntity>) AppDatabase.getAppDataBase(getApplicationContext()).getDao().getAll();
        ArrayList<String[]> buffer = new ArrayList<>();
        for(DataEntity element: elementList){
            String[] resultStr = {String.valueOf(element.date), String.valueOf(element.result)};
            buffer.add(resultStr);
        }
        return buffer;
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
        startActivity(intent);
    }
}