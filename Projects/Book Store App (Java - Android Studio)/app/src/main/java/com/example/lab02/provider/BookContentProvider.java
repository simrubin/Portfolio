package com.example.lab02.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class BookContentProvider extends ContentProvider {

    public static final String CONTENT_AUTHORITY = "fit2081.app.simeon/books";

    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    LibDatabase db;

//    public BookContentProvider() {
//        db = LibDatabase.getDatabase(getContext());
//    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletionCount;

        deletionCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .delete(Book.TABLE_NAME, selection, selectionArgs);

        return deletionCount;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(Book.TABLE_NAME, 0, values);
        return ContentUris.withAppendedId(CONTENT_URI, rowId);
    }

    @Override
    public boolean onCreate() {
        db = LibDatabase.getDatabase(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables("books");
        String query=builder.buildQuery(projection,selection,null,null,sortOrder,null);

        Log.d("BOOKLIB_CONTENTPROVIDER",query);

        Cursor cursor = db.getOpenHelper()
                .getReadableDatabase()
                .query(query);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int updateCount;
        updateCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(Book.TABLE_NAME, 0, values, selection, selectionArgs);

        return updateCount;
    }


}