package com.example.weatherapp.fragments;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.weatherapp.CoatOfArmsContainer;
import com.example.weatherapp.R;

import java.util.Objects;

public class WeatherInfoFragment extends Fragment {
    private TextView humidityTextView;
    private TextView overcastTextView;

    static WeatherInfoFragment create(CoatOfArmsContainer container) {
        WeatherInfoFragment fragment = new WeatherInfoFragment();    // создание

        // Передача параметра
        Bundle args = new Bundle();
        args.putSerializable("index", container);
        fragment.setArguments(args);
        return fragment;
    }

    // Получить индекс из списка (фактически из параметра)
    int getIndex() {
        CoatOfArmsContainer coatContainer = (CoatOfArmsContainer) (Objects.requireNonNull(getArguments())
                .getSerializable("index"));

        try {
            return Objects.requireNonNull(coatContainer).position;
        } catch (Exception e) {
            return 0;
        }
    }

    private String getCityName() {
        CoatOfArmsContainer coatContainer = (CoatOfArmsContainer) (Objects.requireNonNull(getArguments())
                .getSerializable("index"));

        try {
            return Objects.requireNonNull(coatContainer).cityName;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    @SuppressLint("Recycle")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_info, null);
        init(view);

        TextView item = view.findViewById(R.id.cityTextView);
        item.setText(getCityName());

        ImageView imageView = view.findViewById(R.id.coatOfArmsImageView);
        TypedArray images = getResources().obtainTypedArray(R.array.coatofarms_imgs);
        imageView.setImageResource(images.getResourceId(getIndex(), -1));

        return view;
    }

    private void init(View view) {
        CheckBox humidityCheckBox = view.findViewById(R.id.checkBoxAir);
        CheckBox overcastCheckBox = view.findViewById(R.id.checkBoxCloud);
        humidityTextView = view.findViewById(R.id.valueHumidityTextView);
        overcastTextView = view.findViewById(R.id.valueOvercastTextView);

        humidityCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibility = visibleView(((CheckBox) view).isChecked());
                humidityTextView.setVisibility(visibility);
            }
        });

        overcastCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibility = visibleView(((CheckBox) view).isChecked());
                overcastTextView.setVisibility(visibility);
            }
        });
    }

    private int visibleView(Boolean visible) {
        return visible ? View.VISIBLE : View.INVISIBLE;
    }
}