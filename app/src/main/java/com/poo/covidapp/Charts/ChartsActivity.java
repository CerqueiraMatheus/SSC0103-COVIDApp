package com.poo.covidapp.Charts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.poo.covidapp.R;
import com.poo.covidapp.databinding.ActivityChartsBinding;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ChartsActivity extends AppCompatActivity {
    private ActivityChartsBinding binding;
    private TreeMap<String, Float> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set view and binding
        setContentView(R.layout.activity_charts);
        binding = ActivityChartsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setChart();

        // Change action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set custom title
        getSupportActionBar().setTitle(getIntent().getExtras().getString("title"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Fix on back pressed
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void setChart() {
        map = new TreeMap<>((Map<String, Float>) getIntent().getExtras().get("entries"));

        // Build chart
        BarChart chart = findViewById(R.id.bar_chart);
        chart.setData(getData(getEntries()));
        chart.getDescription().setEnabled(false);

        // Set Y axis
        chart.animateY(600);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setAxisMinimum(0f);

        // Set X Axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(map.keySet().toArray(new String[0])));
        xAxis.setLabelCount((int) chart.getVisibleXRange());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    // Get entries
    private ArrayList<BarEntry> getEntries() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        Float[] values = map.values().toArray(new Float[0]);

        // Add entries
        for (int i = 0; i < values.length; i++) {
            entries.add(new BarEntry(i, values[i]));
        }

        return entries;
    }

    // Get data
    static private BarData getData(ArrayList<BarEntry> entries) {
        BarDataSet dataSet = new BarDataSet(entries, "Estados");

        // Style
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);

        return new BarData(dataSet);
    }
}
