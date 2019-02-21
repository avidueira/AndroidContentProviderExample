package com.alfredo.myapplication1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExampleDatabase extends SQLiteOpenHelper {

    private static final String TAG = "ASDF";
    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "example_data";

    public static final String TABLE_SHARED = "shared";
    public static final String ID = "_id";
    public static final String COL_VALUE = "value";

    private static final String CREATE_TABLE_SHARED = "create table " + TABLE_SHARED + " (" + ID + " integer primary key autoincrement, " + COL_VALUE + " text not null);";

    private static final String DB_SCHEMA = CREATE_TABLE_SHARED;

    public ExampleDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating database");
        db.execSQL(DB_SCHEMA);
        //insert sample data
        db.execSQL("INSERT INTO shared (value) VALUES ('test1'), ('test2'), ('test3');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Upgrading database");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHARED);
        onCreate(db);
    }
}
