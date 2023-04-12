package com.srv.appR;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "person.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE " + Provider.Person.TABLE_NAME + " ("
                + Provider.Person._ID + " INTEGER PRIMARY KEY,"
                + Provider.Person.NAME + " TEXT,"
                + Provider.Person.GENDER + " TEXT,"
                + Provider.Person.AGE + " INTEGER"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + Provider.Person.TABLE_NAME);
        onCreate(db);
    }

}
