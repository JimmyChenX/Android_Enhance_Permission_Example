package com.srv.appR;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

public class sourceprovider extends ContentProvider {

    private static final int PERSONS = 1;
    private static final int PERSONS_ID = 2;
    private static UriMatcher sUriMatcher;
    private DatabaseHelper mOpenHelper;
    private static HashMap<String, String> sPersonsProjectionMap;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Provider.AUTHORITY, "persons", PERSONS);
        sUriMatcher.addURI(Provider.AUTHORITY, "persons/#", PERSONS_ID);

        sPersonsProjectionMap = new HashMap<String, String>();
        sPersonsProjectionMap.put(Provider.Person._ID, Provider.Person._ID);
        sPersonsProjectionMap.put(Provider.Person.NAME, Provider.Person.NAME);
        sPersonsProjectionMap.put(Provider.Person.GENDER, Provider.Person.GENDER);
        sPersonsProjectionMap.put(Provider.Person.AGE, Provider.Person.AGE);
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        switch (sUriMatcher.match(uri)) {
            case PERSONS:
                return Provider.CONTENT_TYPE;
            case PERSONS_ID:
                return Provider.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        // TODO Auto-generated method stub
        // Validate the requested uri
        if (sUriMatcher.match(uri) != PERSONS) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }

        // Make sure that the fields are all set
        if (values.containsKey(Provider.Person.NAME) == false) {
            values.put(Provider.Person.NAME, "");
        }

        if (values.containsKey(Provider.Person.GENDER) == false) {
            values.put(Provider.Person.GENDER, "");
        }

        if (values.containsKey(Provider.Person.AGE) == false) {
            values.put(Provider.Person.AGE, 0);
        }

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long rowId = db.insert(Provider.Person.TABLE_NAME, Provider.Person.NAME, values);
        if (rowId > 0) {
            Uri noteUri = ContentUris.withAppendedId(Provider.Person.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // TODO Auto-generated method stub
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(Provider.Person.TABLE_NAME);

        switch (sUriMatcher.match(uri)) {
            case PERSONS:
                qb.setProjectionMap(sPersonsProjectionMap);
                break;
            case PERSONS_ID:
                qb.setProjectionMap(sPersonsProjectionMap);
                qb.appendWhere(Provider.Person._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // If no sort order is specified use the default
        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = Provider.Person.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sortOrder;
        }

        // Get the database and run the query
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);

        // Tell the cursor what uri to watch, so it knows when its source data changes
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        // TODO Auto-generated method stub
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
            case PERSONS:
                count = db.update(Provider.Person.TABLE_NAME, values, where, whereArgs);
                break;
            case PERSONS_ID:
                String noteId = uri.getPathSegments().get(1);
                count = db.update(Provider.Person.TABLE_NAME, values, Provider.Person._ID + "=" + noteId
                        + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        // TODO Auto-generated method stub
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
            case PERSONS:
                count = db.delete(Provider.Person.TABLE_NAME, where, whereArgs);
                break;
            case PERSONS_ID:
                String noteId = uri.getPathSegments().get(1);
                count = db.delete(Provider.Person.TABLE_NAME, Provider.Person._ID + "=" + noteId
                        + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

}