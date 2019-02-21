package com.alfredo.myapplication1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ASDF", "onCreate");

        Cursor cursor = getContentResolver().query(
                ExampleProvider.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            Log.d("ASDF", "Empty cursor");
        } else {
            Log.d("ASDF", "Cursor result count: " + cursor.getCount());
            while (cursor.moveToNext()) {
                Log.d("ASDF", "Registro: " + cursor.getInt(0) + " - " + cursor.getString(1));
            }
            cursor.close();
        }
    }
}
