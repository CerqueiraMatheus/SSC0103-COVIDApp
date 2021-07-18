package com.poo.covidapp.Charts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.poo.covidapp.R;

import java.util.ArrayList;

public class ChartsActivity extends AppCompatActivity {
    private BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        chart = findViewById(R.id.bar_chart);

        ArrayList<String> initials = (ArrayList<String>)getIntent().getSerializableExtra("initials");
        for (String initials_ : initials) {
            System.out.println(initials_);
        }

        ArrayList<Float> values = (ArrayList<Float>)getIntent().getSerializableExtra("values");
        for (float value : values) {
            System.out.println(value);
        }
    }
}
