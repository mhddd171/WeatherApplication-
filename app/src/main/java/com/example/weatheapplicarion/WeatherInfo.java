package com.example.weatheapplicarion;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


    public class WeatherInfo extends AppCompatActivity {
        Button backbt, changeunitbt;
        TextView CityNameTV, temperatureTV, feelsLikeTV, humidityTV, windSpeedTV, visibilityTV;
        Spinner temSP, spedSP, distanceSP;

        DatabaseHelper mDb;


        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_weather_info);

            backbt = findViewById(R.id.backBT);
            changeunitbt = findViewById(R.id.changeunitBT);

            temSP = (Spinner) findViewById(R.id.temp_unit_spinner2);
            spedSP = (Spinner) findViewById(R.id.speed_unit_spinner2);
            distanceSP = (Spinner) findViewById(R.id.distance_unit_spinner2);

            CityNameTV = findViewById(R.id.CitynameTV);
            temperatureTV = findViewById(R.id.temperatureTV);
            feelsLikeTV = findViewById(R.id.feelslikeTV);
            humidityTV = findViewById(R.id.humidityTV);
            windSpeedTV = findViewById(R.id.windspeedTV);
            visibilityTV = findViewById(R.id.VisibilityTV);

            mDb = new DatabaseHelper(this);

            Intent intent = getIntent();
            String cityName = intent.getStringExtra("CityName");

            Cursor cursor = mDb.query(cityName);

            int cityNameIndex = cursor.getColumnIndex(DatabaseHelper.COL_1);
            int temperatureIndex = cursor.getColumnIndex(DatabaseHelper.COL_2);
            int feelsLikeIndex = cursor.getColumnIndex(DatabaseHelper.COL_3);
            int humidityIndex = cursor.getColumnIndex(DatabaseHelper.COL_4);
            int windSpeedIndex = cursor.getColumnIndex(DatabaseHelper.COL_5);
            int visibilityIndex = cursor.getColumnIndex(DatabaseHelper.COL_6);

            if (cursor.moveToFirst()) {
                CityNameTV.setText(cursor.getString(cityNameIndex));
                temperatureTV.setText(cursor.getString(temperatureIndex) + " °C");
                feelsLikeTV.setText(cursor.getString(feelsLikeIndex) + " °C");
                humidityTV.setText(cursor.getString(humidityIndex) + " %");
                windSpeedTV.setText(cursor.getString(windSpeedIndex) + " km/h");
                visibilityTV.setText(cursor.getString(visibilityIndex) + " km");
            } else {
                Toast.makeText(this, "No weather data found for " + cityName, Toast.LENGTH_SHORT).show();
            }

            cursor.close();

            backbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            changeunitbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    temperaturemethod(cityName);
                    windspeedmethod(cityName);
                    distancemethod(cityName);



                }
            });
        }
        public void temperaturemethod(String cityName) {
            Cursor cursor = mDb.query(cityName);

            int temperatureIndex = cursor.getColumnIndex(DatabaseHelper.COL_2);
            int feelsLikeIndex = cursor.getColumnIndex(DatabaseHelper.COL_3);

            if (cursor.moveToFirst()) {
                if (temSP.getSelectedItem().toString().equals("Celsius")) {
                    temperatureTV.setText(cursor.getString(temperatureIndex) + " °C");
                    feelsLikeTV.setText(cursor.getString(feelsLikeIndex) + " °C");
                } else {
                    double celsius = Double.parseDouble(cursor.getString(temperatureIndex));
                    double fahrenheit = celsius * 9/5 + 32;
                    temperatureTV.setText(String.format("%.1f", fahrenheit) + " °F");

                    double feelsLikeCelsius = Double.parseDouble(cursor.getString(feelsLikeIndex));
                    double feelsLikeFahrenheit = feelsLikeCelsius * 9/5 + 32;
                    feelsLikeTV.setText(String.format("%.1f", feelsLikeFahrenheit) + " °F");
                }

            }

            cursor.close();
        }
        public void windspeedmethod(String cityName){
            Cursor cursor = mDb.query(cityName);

            int windSpeedIndex = cursor.getColumnIndex(DatabaseHelper.COL_5);

            if (cursor.moveToFirst()) {
                if (spedSP.getSelectedItem().toString().equals("Kilometers per hour")) {
                    windSpeedTV.setText(cursor.getString(windSpeedIndex) + " km/h");
                } else {
                    double kmh = Double.parseDouble(cursor.getString(windSpeedIndex));
                    double mph = kmh / 1.609;
                    windSpeedTV.setText(String.format("%.1f", mph) + " mph");
                }

            }
            cursor.close();

        }
        public void distancemethod(String cityName){
            Cursor cursor = mDb.query(cityName);

            int visibilityIndex = cursor.getColumnIndex(DatabaseHelper.COL_6);

            if (cursor.moveToFirst()) {
                if (distanceSP.getSelectedItem().toString().equals("Kilometer")) {
                    visibilityTV.setText(cursor.getString(visibilityIndex) + " km");
                } else {
                    double km = Double.parseDouble(cursor.getString(visibilityIndex));
                    double mile = km / 1.609;
                    visibilityTV.setText(String.format("%.1f", mile) + " mph");
                }

            }
            cursor.close();

        }

    }


