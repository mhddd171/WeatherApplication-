package com.example.weatheapplicarion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="weather.db";
    public static final String TABLE_NAME="weatherInfo";
    public static final String COL_1="CityName";
    public static final String COL_2="Temprature";
    public static final String COL_3="FeelsLike";
    public static final String COL_4="Humidity";
    public static final String COL_5="WindSpeed";
    public static final String COL_6="Visibility";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " +TABLE_NAME+ "(CityName TEXT PRIMARY KEY, Temprature INGEGER,FeelsLike INTEGER,Humidity INTEGER, WindSpeed INTEGER, Visibility INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String CN, String Tem, String feels,String Hum, String Wind, String Vis)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,CN);
        contentValues.put(COL_2,Tem);
        contentValues.put(COL_3,feels);
        contentValues.put(COL_4,Hum);
        contentValues.put(COL_5,Wind);
        contentValues.put(COL_6,Vis);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor query(String cityName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = { cityName };
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + "=?";
        return db.rawQuery(query, args);
    }

    public boolean updateData(String CN, String Tem, String feels, String Hum, String Wind, String Vis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Tem);
        contentValues.put(COL_3, feels);
        contentValues.put(COL_4, Hum);
        contentValues.put(COL_5, Wind);
        contentValues.put(COL_6, Vis);
        int result = db.update(TABLE_NAME, contentValues, COL_1 + "=?", new String[] { CN });
        return result > 0;
    }

}

