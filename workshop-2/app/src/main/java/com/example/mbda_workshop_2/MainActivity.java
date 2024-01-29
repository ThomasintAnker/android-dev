package com.example.mbda_workshop_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] myStringArray = new String[] { "Bulbasaur", "Dragonite", "Pikachu" } ;
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray);
        ListView listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener mMessageClickedHandler =
            new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView parent, View v, int position, long id)
                {
                    String message = adapter.getItem(position);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            };
        listView.setOnItemClickListener(mMessageClickedHandler);
    }
}