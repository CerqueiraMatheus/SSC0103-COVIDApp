package com.poo.covidapp.About;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.poo.covidapp.R;
import com.poo.covidapp.databinding.ActivityAboutStaticBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.noties.markwon.Markwon;

public class AboutStaticActivity extends AppCompatActivity {

    private int type;
    private ActivityAboutStaticBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutStaticBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        type = getIntent().getExtras().getInt("type");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // obtain an instance of Markwon
        final Markwon markwon = Markwon.create(getApplicationContext());

        switch (type) {
            case 0:
                getSupportActionBar().setTitle("Sobre o projeto");
                markwon.setMarkdown(binding.aboutStaticText, getTextFromFile(R.raw.about_project));
                break;
            case 1:
                getSupportActionBar().setTitle("Sobre os autores");
                markwon.setMarkdown(binding.aboutStaticText, getTextFromFile(R.raw.about_authors));
                break;
        }
    }

    private String getTextFromFile(int id) {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(getResources().openRawResource(id)));
        StringBuilder txt = new StringBuilder();

        String line;
        try {
            while ((line = bufferedReader.readLine()) != null)
                txt.append(line).append("\n");
        } catch (IOException ex) {
            // error
        }

        return txt.toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}