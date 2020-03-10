package com.example.weatherapp.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;

import java.util.ArrayList;


    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private ArrayList<DataClass> data = new ArrayList<>();

        public RecyclerViewAdapter(ArrayList<DataClass> data) {
            if(data != null) {
                this.data = data;
            }
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();

            View view = LayoutInflater.from(context)
                    .inflate(R.layout.temperatures, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.date.setText(data.get(position).date);
            holder.temperature.setText(data.get(position).temperature);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView date;
            TextView temperature;

            ViewHolder(View view) {
                super(view);

                date = itemView.findViewById(R.id.dateTextView);
                temperature = itemView.findViewById(R.id.tempTextView);
            }
        }
    }

