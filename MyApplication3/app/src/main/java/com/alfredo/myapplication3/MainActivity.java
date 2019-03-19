package com.alfredo.myapplication3;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewContent = findViewById(R.id.textViewContent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        saveContent("content://com.alfredo.myapps1/shared", "prueba desde app3");
    }

    private void loadData() {
        textViewContent.setText("");
        getContent("content://com.alfredo.myapps1/shared");
        getContent("content://com.alfredo.myapps2/shared");
        getContent("content://com.alfredo.myapps3/shared");
    }

    private void getContent(String contentProvider) {
        log("********************************************");
        log(contentProvider);
        Cursor cursor = getContentResolver().query(Uri.parse(contentProvider), null, null, null, null);
        if (cursor == null) {
            log("Cursor vacio");
        } else {
            log("Cantidad de registros: " + cursor.getCount());
            while (cursor.moveToNext()) {
                log("Registro: " + cursor.getInt(0) + " - " + cursor.getString(1));
            }
            cursor.close();
        }
    }

    private void saveContent(String contentProvider, String value) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", value);
        getContentResolver().insert(Uri.parse(contentProvider), contentValues);
        getContent(contentProvider);
    }

    private void log(String message) {
        textViewContent.append(message + "\n");
    }
}
