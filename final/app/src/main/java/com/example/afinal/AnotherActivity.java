package com.example.afinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AnotherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity);

        InterestingFragment f = new InterestingFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, f)
                .commit();
    }
}
