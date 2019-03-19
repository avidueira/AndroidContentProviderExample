package com.alfredo.myapplication2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class ExampleProvider extends ContentProvider {

    private static final String AUTHORITY = "com.alfredo.myapps2";
    private static final String SHARED_BASE_PATH = "shared";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + SHARED_BASE_PATH);

    private static final int SHARED = 1;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, SHARED_BASE_PATH, SHARED);
    }


    private ExampleDatabase exampleDatabase;

    @Override
    public boolean onCreate() {
        exampleDatabase = new ExampleDatabase(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase database = exampleDatabase.getWritableDatabase();

        Cursor cursor = database.query(ExampleDatabase.TABLE_SHARED, projection, null, selectionArgs, null, null, sortOrder);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sURIMatcher.match(uri);

        switch (match) {
            case SHARED:
                return "vnd.android.cursor.dir/vnd.alfredo.shared";
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = exampleDatabase.getWritableDatabase();
        long rowID = db.insert(ExampleDatabase.TABLE_SHARED, "", values);
        Log.d("ASDF", "inserted: " + rowID);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
