package com.example.weatheapplicarion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insertdata extends AppCompatActivity {
    Button btn, insertDataBT;
    DatabaseHelper mDb;
    EditText City, Tem, Feels, Hum, Wind, Vis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertdata);

        btn = (Button) findViewById(R.id.inser_backBT);
        insertDataBT = (Button) findViewById(R.id.insertBT);

        City = (EditText) findViewById(R.id.CitynameET);
        Tem = (EditText) findViewById(R.id.temperatureET);
        Feels = (EditText) findViewById(R.id.feelslikeET);
        Hum = (EditText) findViewById(R.id.humidityET);
        Wind = (EditText) findViewById(R.id.windspeedET);
        Vis = (EditText) findViewById(R.id.VisibilityET);

        mDb = new DatabaseHelper(this);

        addData();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main();
            }
        });
    }

    public void addData() {
        insertDataBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = City.getText().toString();
                if (TextUtils.isEmpty(cityName) || TextUtils.isEmpty(Tem.getText().toString()) || TextUtils.isEmpty(Feels.getText().toString()) || TextUtils.isEmpty(Hum.getText().toString()) || TextUtils.isEmpty(Wind.getText().toString()) || TextUtils.isEmpty(Vis.getText().toString())) {
                    Toast.makeText(insertdata.this, "Please Enter Valid Data", Toast.LENGTH_LONG).show();
                    return;
                }

                Cursor cursor = mDb.query(cityName);
                if (cursor != null && ((Cursor) cursor).moveToFirst()) {
                    // Update data
                    boolean update = mDb.updateData(cityName, Tem.getText().toString(), Feels.getText().toString(), Hum.getText().toString(), Wind.getText().toString(), Vis.getText().toString());
                    cursor.close();

                    if (update) {
                        Toast.makeText(insertdata.this, "Data Updated", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(insertdata.this, "Data not updated", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Insert new data
                    if (cursor != null) {
                        cursor.close();
                    }
                    boolean insert = mDb.insertData(cityName, Tem.getText().toString(), Feels.getText().toString(), Hum.getText().toString(), Wind.getText().toString(), Vis.getText().toString());

                    if (insert) {
                        Toast.makeText(insertdata.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(insertdata.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    public void Main() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
