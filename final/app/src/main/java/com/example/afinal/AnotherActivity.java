package com.example.afinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AnotherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity);

        // Send us back!
        Toolbar toolbar = findViewById(R.id.topBar);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}
