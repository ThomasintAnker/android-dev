package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void weather_navigation(View _view) {
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        startActivity(intent);
    }

    public void another_navigation(View _view) {
        Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
        startActivity(intent);
    }
}
