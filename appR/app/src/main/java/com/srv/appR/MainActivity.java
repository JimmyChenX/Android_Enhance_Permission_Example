package com.srv.appR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
    }

    private void initDatabase() {
        DatabaseHelper mOpenHelper = new DatabaseHelper(getApplicationContext());
        ContentValues contentValues = new ContentValues();
        contentValues.put(Provider.Person.NAME, "jimmy");
        contentValues.put(Provider.Person.AGE, 18);
        contentValues.put(Provider.Person.GENDER, "T1");
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long ignore = db.insert(Provider.Person.TABLE_NAME, Provider.Person.NAME, contentValues);
    }
}