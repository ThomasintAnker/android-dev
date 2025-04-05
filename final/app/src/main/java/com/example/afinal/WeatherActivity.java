package com.example.afinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class WeatherActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        prefs = getSharedPreferences(getString(R.string.app_id), Context.MODE_PRIVATE);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch sw = findViewById(R.id.temp_switch);
        sw.setChecked(prefs.getBoolean(getString(R.string.pref_db_sw), false));
        handle_switch(sw);

        // Send us back!
        Toolbar toolbar = findViewById(R.id.topBar);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    public void fetch_data(View view) {
        ListView list = findViewById(R.id.list);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch sw = findViewById(R.id.temp_switch);

        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/'s-hertogenbosch?unitGroup=";
        url += sw.isChecked() ? "metric" : "us";
        url += "&elements=datetime%2Cname%2Caddress%2CresolvedAddress%2Clatitude%2Clongitude%2Ctempmax%2Ctempmin%2Ctemp%2Csunrise%2Csunset&key=B92M3RGGTVPJEF7D4D72SXHG2&contentType=json";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET, url, null, response ->
            {
                populate_list(response, list);
            }, error -> {
                error_message();
            }
        );
        queue.add(req);
    }

    public void handle_switch(View view) {
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch sw = findViewById(R.id.temp_switch);

        SharedPreferences.Editor db = prefs.edit();
        db.putBoolean(getString(R.string.pref_db_sw), sw.isChecked());
        db.apply();
    }

    private void populate_list(JSONObject _resp, ListView _list) {
        ArrayList<String> data = new ArrayList<>();
        try {
            JSONArray forecast = _resp.getJSONArray("days");
            for (int i = 0; i < forecast.length(); i++) {
                JSONObject obj = forecast.getJSONObject(i);
                data.add(String.format("%s\nMin:%s\t\t\tMax:%s", obj.getString("datetime"), obj.getString("tempmin"), obj.getString("tempmax")));
            }
        } catch (Exception e) {
            error_message();
            return;
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                WeatherActivity.this, android.R.layout.simple_list_item_1, data
        );
        _list.setAdapter(adapter);
    }

    private void error_message() {
        Toast.makeText(WeatherActivity.this, "Something went wrong?", Toast.LENGTH_SHORT).show();
    }
}
