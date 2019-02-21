package com.alfredo.myapplication2;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String AUTHORITY = "com.alfredo.myapps";
    private static final String SHARED_BASE_PATH = "shared";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + SHARED_BASE_PATH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ASDF", "onCreate");

        Cursor cursor = getContentResolver().query(
                CONTENT_URI,
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


        ContentValues contentValues = new ContentValues();
        contentValues.put("value", "desde app2");
        getContentResolver().insert(CONTENT_URI, contentValues);
    }
}
