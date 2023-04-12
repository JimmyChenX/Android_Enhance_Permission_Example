package com.srv.appC;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "com.srv.sourceprovider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/persons");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.hello_tv);
        try {
            String result = queryProvider();
            tv.setText(result);
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText("Query provider failed in appC!");
        }
    }

    private String queryProvider() throws Exception {
        String result = null;
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if(cursor.moveToFirst()){
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                @SuppressLint("Range")
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                result = "name:" + name + "; age: " + age + "; gender: " + gender;
            } else {
                result = "Can not get info from provider.";
            }
            cursor.close();
        }
        return result;
    }

}