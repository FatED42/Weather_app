package com.example.weatherapp.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.weatherapp.CoatOfArmsContainer;
import com.example.weatherapp.InfoActivity;
import com.example.weatherapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class CitiesFragment extends Fragment {
    private ListView listView;
    private TextView emptyTextView;
    private Button buttonOK;

    private boolean isExistCoatOfArms;
    private int currentPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isExistCoatOfArms = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("CurrentCity", 0);
        }

        if (isExistCoatOfArms) {
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showCoatOfArms();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", currentPosition);
        super.onSaveInstanceState(outState);
    }

    private void initViews(View view) {
        listView = view.findViewById(R.id.cities_list_view);
        emptyTextView = view.findViewById(R.id.cities_list_empty_view);
        buttonOK = view.findViewById(R.id.button1);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Вы уверены?", Snackbar.LENGTH_LONG)
                        .setAction("Да", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                snackbar.setActionTextColor(Color.CYAN);
                TextView textView = snackbar.getView()
                        .findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.LTGRAY);

                snackbar.show();
            }
        });
    }

    private void initList() {
        ArrayAdapter adapter =
                ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.cities,
                        android.R.layout.simple_list_item_activated_1);
        listView.setAdapter(adapter);

        listView.setEmptyView(emptyTextView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                showCoatOfArms();
            }
        });
    }

    private void showCoatOfArms() {
        if (isExistCoatOfArms) {
            listView.setItemChecked(currentPosition, true);

            WeatherInfoFragment detail = (WeatherInfoFragment)
                    Objects.requireNonNull(getFragmentManager()).findFragmentById(R.id.coat_of_arms);

            if (detail == null || detail.getIndex() != currentPosition) {

                detail = WeatherInfoFragment.create(getCoatOfArmsContainer());

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.coat_of_arms, detail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                //ft.addToBackStack(null);
                ft.addToBackStack("Some_Key");
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(Objects.requireNonNull(getActivity()), InfoActivity.class);
            intent.putExtra("index", getCoatOfArmsContainer());
            startActivity(intent);
        }
    }

    private CoatOfArmsContainer getCoatOfArmsContainer() {
        String[] cities = getResources().getStringArray(R.array.cities);
        CoatOfArmsContainer container = new CoatOfArmsContainer();
        container.position = currentPosition;
        container.cityName = cities[currentPosition];
        return container;
    }
}
