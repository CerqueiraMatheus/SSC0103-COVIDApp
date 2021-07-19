package com.poo.covidapp.About;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.poo.covidapp.R;

public class AboutStaticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_static);

        System.out.println(getIntent().getExtras().getInt("type"));
    }
}