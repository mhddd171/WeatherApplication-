package com.example.weatheapplicarion;

public class changeUnits extends WeatherInfo{
    changeUnits(){

    }

    public double temperaturemethod(Double tem) {

        double fa=tem* 9/5 + 3;
        return fa;
//        Cursor cursor = mDb.query(cityName);

//        int temperatureIndex = cursor.getColumnIndex(DatabaseHelper.COL_2);
//        int feelsLikeIndex = cursor.getColumnIndex(DatabaseHelper.COL_3);

//        if (cursor.moveToFirst()) {
//            if (temSP.getSelectedItem().toString().equals("Celsius")) {
//                temperatureTV.setText(cursor.getString(temperatureIndex) + " °C");
//                feelsLikeTV.setText(cursor.getString(feelsLikeIndex) + " °C");
//            } else {
//                double celsius = Double.parseDouble(cursor.getString(temperatureIndex));
//                double fahrenheit = celsius * 9/5 + 32;
//                temperatureTV.setText(String.format("%.1f", fahrenheit) + " °F");
//
//                double feelsLikeCelsius = Double.parseDouble(cursor.getString(feelsLikeIndex));
//                double feelsLikeFahrenheit = feelsLikeCelsius * 9/5 + 32;
//                feelsLikeTV.setText(String.format("%.1f", feelsLikeFahrenheit) + " °F");
//            }
//
//        }
//
//
//        cursor.close();
//    }
//    public void windspeedmethod(Double cityName){
//        Cursor cursor = mDb.query(cityName);
//
//        int windSpeedIndex = cursor.getColumnIndex(DatabaseHelper.COL_5);
//
//        if (cursor.moveToFirst()) {
//            if (spedSP.getSelectedItem().toString().equals("Kilometers per hour")) {
//                windSpeedTV.setText(cursor.getString(windSpeedIndex) + " km/h");
//            } else {
//                double kmh = Double.parseDouble(cursor.getString(windSpeedIndex));
//                double mph = kmh / 1.609;
//                windSpeedTV.setText(String.format("%.1f", mph) + " mph");
//            }
//
//        }
//        cursor.close();
//
//    }
//    public void distancemethod(String cityName){
//        Cursor cursor = mDb.query(cityName);
//
//        int visibilityIndex = cursor.getColumnIndex(DatabaseHelper.COL_6);
//
//        if (cursor.moveToFirst()) {
//            if (distanceSP.getSelectedItem().toString().equals("Kilometer")) {
//                visibilityTV.setText(cursor.getString(visibilityIndex) + " km");
//            } else {
//                double km = Double.parseDouble(cursor.getString(visibilityIndex));
//                double mile = km / 1.609;
//                visibilityTV.setText(String.format("%.1f", mile) + " mph");
//            }
//
//        }
//        cursor.close();
//
    }

}
