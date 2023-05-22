package com.example.weatheapplicarion;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
DatabaseHelper mDb;
EditText edt;
Button btn, insertBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt= (EditText) findViewById(R.id.edt);
        btn=(Button) findViewById(R.id.goBT);
        insertBT=(Button) findViewById(R.id.enterDataBT);

        mDb = new DatabaseHelper(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CityName=edt.getText().toString();
                SQLiteDatabase db = mDb.getReadableDatabase();
                Cursor cursor = db.query("weatherInfo", null, "CityName = ?", new String[] { CityName }, null, null, null);
                if (cursor.moveToFirst()) {
                    int cityNameIndex = cursor.getColumnIndex("CityName");
                    if (cityNameIndex != -1 && !cursor.isNull(cityNameIndex)) {
                        openActivity2(CityName);
                    } else {
                        Toast.makeText(getApplicationContext(),"Can Not Find The City Name",Toast.LENGTH_LONG).show();
                    }
                }
                cursor.close();
                db.close();

            }
        });

        insertBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertpage();
            }
        });

    }

    public void openActivity2(String CityName) {
        Intent intent=new Intent(this,WeatherInfo.class);
        intent.putExtra("CityName", CityName);
        startActivity(intent);
    }
    private void insertpage() {
        Intent intent=new Intent(this,insertdata.class);
        startActivity(intent);
    }
}