package com.example.cloudy;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocationDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "location.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "locations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";

    private static final int MAX_LOCATIONS = 10;

    public LocationDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LATITUDE + " REAL, " +
                COLUMN_LONGITUDE + " REAL);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the table has reached the limit of 10 locations
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        if (count >= MAX_LOCATIONS) {
            // Delete the oldest location
            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = (SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " LIMIT 1)");
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LATITUDE, location.getLatitude());
        contentValues.put(COLUMN_LONGITUDE, location.getLongitude());
        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public Cursor getAllLocations() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_LATITUDE + ", " + COLUMN_LONGITUDE + " FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }
    public void deleteAllLocations() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = '" + TABLE_NAME + "'");
        db.close();
    }

    public void printAllLocationsSortedById() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"id", "latitude", "longitude"}, null, null, null, null, "id DESC");

        List<Location> locationList = new ArrayList<>();
        if (cursor.moveToFirst()) {

            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
                @SuppressLint("Range") double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
                Location location = new Location(latitude, longitude);
                location.setId(id);
                locationList.add(location);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Collections.sort(locationList, new Comparator<Location>() {
            @Override
            public int compare(Location location1, Location location2) {
                return Integer.compare(location2.getId(), location1.getId()); // reverse order
            }
        });

        for (Location location : locationList) {
            Log.d("Location", "ID: " + location.getId() + ", Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
        }
    }

    public List<Location> getAllLocationsSortedById() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"id", "latitude", "longitude"}, null, null, null, null, "id DESC");

        List<Location> locationList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
                @SuppressLint("Range") double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
                Location location = new Location(latitude, longitude);
                location.setId(id);
                locationList.add(location);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Collections.sort(locationList, new Comparator<Location>() {
            @Override
            public int compare(Location location1, Location location2) {
                return Integer.compare(location2.getId(), location1.getId()); // reverse order
            }
        });

        return locationList;
    }








}
