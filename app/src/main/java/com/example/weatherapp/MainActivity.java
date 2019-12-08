package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxAir;
    private CheckBox checkBoxCloud;
    Spinner citiesSpinner;
    Button showWeatherBtn;

    final static String KEY_TO_DATA = "KEY_TO_DATA";
    private final static String CITY_STATE = "cityState";
    private final static String HUMIDITY_STATE = "humidityState";
    private final static String OVERCAST_STATE = "overcastState";

    private final String[] cities = {"Москва", "Санкт-Петербург", "Зеленоград", "Рославль"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        checkBoxAir = findViewById(R.id.checkBoxAir);
        checkBoxCloud = findViewById(R.id.checkBoxCloud);
        citiesSpinner = findViewById(R.id.citiesSpinner);
        showWeatherBtn = findViewById(R.id.showWeather);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.preview_text,
                cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citiesSpinner.setAdapter(adapter);

        showWeatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                ActivityInfo activityInfo = intent.resolveActivityInfo(getPackageManager(),
                        intent.getFlags());
                if (activityInfo != null) {
                    Parcel parcel = new Parcel();
                    parcel.city = citiesSpinner.getSelectedItem().toString();
                    parcel.humidity = checkBoxAir.isChecked();
                    parcel.overcast = checkBoxCloud.isChecked();
                    intent.putExtra(KEY_TO_DATA, parcel);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        citiesSpinner.getSelectedItemPosition();
        outState.putInt(CITY_STATE, citiesSpinner.getSelectedItemPosition());
        outState.putBoolean(HUMIDITY_STATE, checkBoxAir.isChecked());
        outState.putBoolean(OVERCAST_STATE, checkBoxCloud.isChecked());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        citiesSpinner.setSelection(savedInstanceState.getInt(CITY_STATE));
        checkBoxAir.setChecked(savedInstanceState.getBoolean(HUMIDITY_STATE));
        checkBoxCloud.setChecked(savedInstanceState.getBoolean(OVERCAST_STATE));
    }
}
