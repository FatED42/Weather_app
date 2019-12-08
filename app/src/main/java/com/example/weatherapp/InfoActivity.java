package com.example.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InfoActivity extends AppCompatActivity {
    TextView cityNameTextView;
    TextView valueHumidityTextView;
    TextView valueOvercastTextView;
    ImageView coatOfArmsImageView;
    Button moreDetailsBtn;

    private static Map<String, Integer> resources = new HashMap<>();
    private static final String URL = "https://ru.wikipedia.org/wiki/";

    static {
        resources.put("Москва", R.drawable.gerb_moscow);
        resources.put("Санкт-Петербург", R.drawable.gerb_sankt_peterburga);
        resources.put("Зеленоград", R.drawable.gerb_zelenograda);
        resources.put("Рославль", R.drawable.gerb_roslavl);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        init();
        setValue();
    }

    public void init() {
        cityNameTextView = findViewById(R.id.cityName);
        valueHumidityTextView = findViewById(R.id.valueHumidity);
        valueOvercastTextView = findViewById(R.id.valueOvercast);
        coatOfArmsImageView = findViewById(R.id.coatOfArms);
        moreDetailsBtn = findViewById(R.id.moreDetailsBtn);

        moreDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(URL + cityNameTextView.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    public void setValue() {
        Serializable serializable = getIntent().getSerializableExtra(MainActivity.KEY_TO_DATA);
        if (serializable != null) {
            Parcel parcel = (Parcel) serializable;
            String city = Objects.requireNonNull(parcel).city;
            cityNameTextView.setText(city);
            valueHumidityTextView.setVisibility(visibleView(Objects.requireNonNull(parcel).humidity));
            valueOvercastTextView.setVisibility(visibleView(Objects.requireNonNull(parcel).overcast));
            coatOfArmsImageView.setImageResource(Objects.requireNonNull(resources.get(city)));
        }
    }

    private int visibleView(Boolean visible) {
        return visible ? View.VISIBLE : View.INVISIBLE;
    }
}
