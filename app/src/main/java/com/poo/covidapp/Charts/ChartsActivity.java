package com.poo.covidapp.Charts;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.poo.covidapp.R;
import com.poo.covidapp.databinding.ActivityChartsBinding;

import java.util.ArrayList;

public class ChartsActivity extends AppCompatActivity {
    private ActivityChartsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        binding = ActivityChartsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();


        // chart = findViewById(R.id.bar_chart) == binding.barChart

        ArrayList<String> initials = (ArrayList<String>) getIntent().getSerializableExtra("initials");
        for (String initials_ : initials) {
            System.out.println(initials_);
        }

        ArrayList<Float> values = (ArrayList<Float>) getIntent().getSerializableExtra("values");
        for (float value : values) {
            System.out.println(value);
        }

        // Show back button and set custom title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Batata");
    }

    @Override
    // Perform "back"
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
