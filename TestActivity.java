package com.example.finnishtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class TestActivity extends AppCompatActivity {
    private TextView textView1, textView2, textView3, textView4, textView5;
    private TextView textView6, textView7, textView8, textView9, textView10;
    private Button button_next;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    private CheckBox checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;
    private String userKey = "wordList";
    private DatabaseReference cloudDB;
    double points = 0;
    int counter = 1;
    ArrayList<String[]> wordListFR = new ArrayList<>();
    ArrayList<String> wordListF = new ArrayList<>();
    ArrayList<String> unCheckedWordsF = new ArrayList<>();

    public interface dataCallback {
        void onCallback(ArrayList<String[]> wordListShow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ArrayList<String> values = getNumbers();

        getData(new dataCallback() {
            @Override
            public void onCallback(ArrayList<String[]> wordListFR) {
                sortData( 0, 9);

                setContentView(R.layout.activity_test);

                textView1 = (TextView) findViewById(R.id.textView1);
                textView1.setText(wordListF.get(0));

                textView2 = (TextView) findViewById(R.id.textView2);
                textView2.setText(wordListF.get(1));

                textView3 = (TextView) findViewById(R.id.textView3);
                textView3.setText(wordListF.get(2));

                textView4 = (TextView) findViewById(R.id.textView4);
                textView4.setText(wordListF.get(3));

                textView5 = (TextView) findViewById(R.id.textView5);
                textView5.setText(wordListF.get(4));

                textView6 = (TextView) findViewById(R.id.textView6);
                textView6.setText(wordListF.get(5));

                textView7 = (TextView) findViewById(R.id.textView7);
                textView7.setText(wordListF.get(6));

                textView8 = (TextView) findViewById(R.id.textView8);
                textView8.setText(wordListF.get(7));

                textView9 = (TextView) findViewById(R.id.textView9);
                textView9.setText(wordListF.get(8));

                textView10 = (TextView) findViewById(R.id.textView10);
                textView10.setText(wordListF.get(9));

                checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
                checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
                checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
                checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
                checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
                checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
                checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
                checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
                checkBox9 = (CheckBox) findViewById(R.id.checkBox9);
                checkBox10 = (CheckBox) findViewById(R.id.checkBox10);

                button_next = (Button) findViewById(R.id.button_next);
            }
        }, values);
    }

    private void sortData(int start, int finish){
        for(int y = start; y <= finish; y++){
            wordListF.add(wordListFR.get(y)[0]);
        }
    }

    private void getData(dataCallback Callback, ArrayList<String> values) {
        cloudDB = FirebaseDatabase.getInstance().getReference(userKey);
        ValueEventListener vL = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String id = ds.child("id").getValue(String.class);
                    for(String value: values){
                        if(Objects.equals(id, value)){
                            String[] wordFR = {ds.child("word").getValue(String.class), ds.child("translate").getValue(String.class)};
                            wordListFR.add(wordFR);
                        }
                    }
                }
                Callback.onCallback(wordListFR);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TAG", databaseError.getMessage());
            }
        };
        cloudDB.addValueEventListener(vL);
    }

    public ArrayList<String> getNumbers() {
        ArrayList<String> values = new ArrayList<>();
        Integer[] range = {0, 650, 1400, 2750, 6000, 9500, 10000};

        for(int i = 0; i < range.length - 1; i++){
            int min = range[i];
            int max = range[i + 1];
            for(int j = 0; j < 10; j++){
                int index = min + (int) (Math.random() * (max - min + 1));
                values.add(String.valueOf(index));
            }
        }
        return values;
    }

    public int boxChecker(int index){
        int local_point = 0;

        if (checkBox1.isChecked()) {
            local_point++;
            checkBox1.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index)[0] + " - " + wordListFR.get(index)[1]);
        }

        if (checkBox2.isChecked()) {
            local_point++;
            checkBox2.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index+1)[0] + " - " + wordListFR.get(index+1)[1]);
        }

        if (checkBox3.isChecked()) {
            local_point++;
            checkBox3.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index+2)[0] + " - " + wordListFR.get(index+2)[1]);
        }

        if (checkBox4.isChecked()) {
            local_point++;
            checkBox4.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index+3)[0] + " - " + wordListFR.get(index+3)[1]);
        }

        if (checkBox5.isChecked()) {
            local_point++;
            checkBox5.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index+4)[0] + " - " + wordListFR.get(index+4)[1]);
        }

        if (checkBox6.isChecked()) {
            local_point++;
            checkBox6.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index+5)[0] + " - " + wordListFR.get(index+5)[1]);
        }

        if (checkBox7.isChecked()) {
            local_point++;
            checkBox7.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index+6)[0] + " - " + wordListFR.get(index+6)[1]);
        }

        if (checkBox8.isChecked()) {
            local_point++;
            checkBox8.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index+7)[0] + " - " + wordListFR.get(index+7)[1]);
        }

        if (checkBox9.isChecked()) {
            local_point++;
            checkBox9.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index+8)[0] + " - " + wordListFR.get(index+8)[1]);
        }

        if (checkBox10.isChecked()) {
            local_point++;
            checkBox10.setChecked(false);
        }else{
            unCheckedWordsF.add(wordListFR.get(index+9)[0] + " - " + wordListFR.get(index+9)[1]);
        }

        wordListF.clear();
        counter++;
        return local_point;
    }

    public void onClick(View view) {
        overridePendingTransition(R.anim.animation_right_in, R.anim.animation_right_in);
        if(counter == 1){
            points = boxChecker(0);

            sortData(10, 19);

            textView1 = (TextView) findViewById(R.id.textView1);
            textView1.setText(wordListF.get(0));
            textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText(wordListF.get(1));
            textView3 = (TextView) findViewById(R.id.textView3);
            textView3.setText(wordListF.get(2));
            textView4 = (TextView) findViewById(R.id.textView4);
            textView4.setText(wordListF.get(3));
            textView5 = (TextView) findViewById(R.id.textView5);
            textView5.setText(wordListF.get(4));
            textView6 = (TextView) findViewById(R.id.textView6);
            textView6.setText(wordListF.get(5));
            textView7 = (TextView) findViewById(R.id.textView7);
            textView7.setText(wordListF.get(6));
            textView8 = (TextView) findViewById(R.id.textView8);
            textView8.setText(wordListF.get(7));
            textView9 = (TextView) findViewById(R.id.textView9);
            textView9.setText(wordListF.get(8));
            textView10 = (TextView) findViewById(R.id.textView10);
            textView10.setText(wordListF.get(9));

        }else if(counter == 2){
            points = points + boxChecker(10);

            sortData( 20, 29);

            textView1 = (TextView) findViewById(R.id.textView1);
            textView1.setText(wordListF.get(0));
            textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText(wordListF.get(1));
            textView3 = (TextView) findViewById(R.id.textView3);
            textView3.setText(wordListF.get(2));
            textView4 = (TextView) findViewById(R.id.textView4);
            textView4.setText(wordListF.get(3));
            textView5 = (TextView) findViewById(R.id.textView5);
            textView5.setText(wordListF.get(4));
            textView6 = (TextView) findViewById(R.id.textView6);
            textView6.setText(wordListF.get(5));
            textView7 = (TextView) findViewById(R.id.textView7);
            textView7.setText(wordListF.get(6));
            textView8 = (TextView) findViewById(R.id.textView8);
            textView8.setText(wordListF.get(7));
            textView9 = (TextView) findViewById(R.id.textView9);
            textView9.setText(wordListF.get(8));
            textView10 = (TextView) findViewById(R.id.textView10);
            textView10.setText(wordListF.get(9));

        }else if(counter == 3){
            points = points + boxChecker(20);

            sortData( 30, 39);

            textView1 = (TextView) findViewById(R.id.textView1);
            textView1.setText(wordListF.get(0));
            textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText(wordListF.get(1));
            textView3 = (TextView) findViewById(R.id.textView3);
            textView3.setText(wordListF.get(2));
            textView4 = (TextView) findViewById(R.id.textView4);
            textView4.setText(wordListF.get(3));
            textView5 = (TextView) findViewById(R.id.textView5);
            textView5.setText(wordListF.get(4));
            textView6 = (TextView) findViewById(R.id.textView6);
            textView6.setText(wordListF.get(5));
            textView7 = (TextView) findViewById(R.id.textView7);
            textView7.setText(wordListF.get(6));
            textView8 = (TextView) findViewById(R.id.textView8);
            textView8.setText(wordListF.get(7));
            textView9 = (TextView) findViewById(R.id.textView9);
            textView9.setText(wordListF.get(8));
            textView10 = (TextView) findViewById(R.id.textView10);
            textView10.setText(wordListF.get(9));

        }else if(counter == 4){
            points = points + boxChecker(30);

            sortData(40, 49);

            textView1 = (TextView) findViewById(R.id.textView1);
            textView1.setText(wordListF.get(0));
            textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText(wordListF.get(1));
            textView3 = (TextView) findViewById(R.id.textView3);
            textView3.setText(wordListF.get(2));
            textView4 = (TextView) findViewById(R.id.textView4);
            textView4.setText(wordListF.get(3));
            textView5 = (TextView) findViewById(R.id.textView5);
            textView5.setText(wordListF.get(4));
            textView6 = (TextView) findViewById(R.id.textView6);
            textView6.setText(wordListF.get(5));
            textView7 = (TextView) findViewById(R.id.textView7);
            textView7.setText(wordListF.get(6));
            textView8 = (TextView) findViewById(R.id.textView8);
            textView8.setText(wordListF.get(7));
            textView9 = (TextView) findViewById(R.id.textView9);
            textView9.setText(wordListF.get(8));
            textView10 = (TextView) findViewById(R.id.textView10);
            textView10.setText(wordListF.get(9));

        }else if(counter == 5){
            points = points + boxChecker(40);

            button_next.setText("Завершить");

            sortData( 50, 59);

            textView1 = (TextView) findViewById(R.id.textView1);
            textView1.setText(wordListF.get(0));
            textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText(wordListF.get(1));
            textView3 = (TextView) findViewById(R.id.textView3);
            textView3.setText(wordListF.get(2));
            textView4 = (TextView) findViewById(R.id.textView4);
            textView4.setText(wordListF.get(3));
            textView5 = (TextView) findViewById(R.id.textView5);
            textView5.setText(wordListF.get(4));
            textView6 = (TextView) findViewById(R.id.textView6);
            textView6.setText(wordListF.get(5));
            textView7 = (TextView) findViewById(R.id.textView7);
            textView7.setText(wordListF.get(6));
            textView8 = (TextView) findViewById(R.id.textView8);
            textView8.setText(wordListF.get(7));
            textView9 = (TextView) findViewById(R.id.textView9);
            textView9.setText(wordListF.get(8));
            textView10 = (TextView) findViewById(R.id.textView10);
            textView10.setText(wordListF.get(9));

        }else if(counter == 6){
            points = points + boxChecker(50);
            String[] buffer = levelDetecter();
            String pointsStr = buffer[0];
            String level = buffer[1];
            addToDB(pointsStr);

            Intent intent = new Intent(this, ResultActivity.class);

            DataPoints dataPoints = new DataPoints(pointsStr);
            intent.putExtra(DataPoints.class.getSimpleName(), dataPoints);

            DataList dataWordList = new DataList(unCheckedWordsF);
            intent.putExtra(DataList.class.getSimpleName(), dataWordList);

            DataLevel dataLevel = new DataLevel(level);
            intent.putExtra(DataLevel.class.getSimpleName(), dataLevel);

            overridePendingTransition(R.anim.animation_right_in, R.anim.animation_left_out);
            startActivity(intent);
        }
    }

    private String[] levelDetecter(){
        if (points / 60 <= 0.17) {
            points = points / 60 * 10000 * 0.78;
        } else if (0.17 < points /60 && points / 60 <= 0.33) {
            points = points / 60 * 10000 * 0.84;
        } else if (0.33 < points /60 && points / 60 <= 0.5) {
            points = points / 60 * 10000 * 1.1;
        } else if (0.5 < points /60 && points / 60 <= 0.67) {
            points = points / 60 * 10000 * 1.8;
        } else if (0.67 < points /60 && points / 60 <= 0.83) {
            points = points / 60 * 10000 * 2.28;
        } else if (0.87 < points /60 && points / 60 <= 1) {
            points = points / 60 * 10000 * 2;
        }

        String level = "";
        if (points <= 800) {
            level = "A0";
        } else if (800 < points && points <= 1300) {
            level = "A1";
        } else if (1300 < points && points <= 2800) {
            level = "A2";
        } else if (2800 < points && points <= 5500) {
            level = "B1";
        } else if (5500 < points && points <= 12000) {
            level = "B2";
        } else if (12000 < points && points <= 19000) {
            level = "C1";
        } else if (19000 < points) {
            level = "C2";
        }

        String pointsStr = String.valueOf((int)Math.ceil(points));
        return new String[]{pointsStr, level};
    }

    private void addToDB(String pointsStr){
        DataEntity result = new DataEntity();

        result.setDate(String.valueOf(LocalDate.now()));
        result.setResult(pointsStr);
        AppDatabase.getAppDataBase(getApplicationContext()).getDao().insertAll(result);

        finish();
    }
}