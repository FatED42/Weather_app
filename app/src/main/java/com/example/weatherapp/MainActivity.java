package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxAir;
    private CheckBox checkBoxCloud;
    private TextView airHumidity;
    private TextView overcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxAir = (CheckBox)findViewById(R.id.checkBoxAir);
        checkBoxCloud = (CheckBox)findViewById(R.id.checkBoxCloud);
        airHumidity = (TextView)findViewById(R.id.textView);
        overcast = (TextView)findViewById(R.id.textView2);

        checkBoxAir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxAir.isChecked()) {
                    airHumidity.setVisibility(View.VISIBLE);
                }
                else airHumidity.setVisibility(View.INVISIBLE);
            }
        });
        checkBoxCloud.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxCloud.isChecked()) {
                    overcast.setVisibility(View.VISIBLE);
                }
                else overcast.setVisibility(View.INVISIBLE);
            }
        });
    }
}
