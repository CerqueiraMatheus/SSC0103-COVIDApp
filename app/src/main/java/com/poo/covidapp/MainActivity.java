package com.poo.covidapp;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.poo.covidapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind and set the content
        com.poo.covidapp.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configuration for bar workflow
        AppBarConfiguration conf = new AppBarConfiguration.Builder(
                R.id.navigation_noticias, R.id.navigation_dados, R.id.navigation_estimativa, R.id.navigation_sobre).build();

        // Linking the navigation controller and configurations
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController controller = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, controller, conf);
        NavigationUI.setupWithNavController(binding.bottomNavigation, controller);

        // Fix orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}