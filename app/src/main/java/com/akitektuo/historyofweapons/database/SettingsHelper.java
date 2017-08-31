package com.akitektuo.historyofweapons.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AoD Akitektuo on 26-Jul-16.
 */
public class SettingsHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database_settings_one.db";

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_QUERY = "CREATE TABLE " + SettingsContract.SettingsContractEntry.TABLE_NAME + " (" +
            SettingsContract.SettingsContractEntry.COLUMN_NAME_START + " TEXT," +
            SettingsContract.SettingsContractEntry.COLUMN_NAME_LANGUAGE + " TEXT" + ");";

    public SettingsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addInformation(String start, String language, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SettingsContract.SettingsContractEntry.COLUMN_NAME_START, start);
        contentValues.put(SettingsContract.SettingsContractEntry.COLUMN_NAME_LANGUAGE, language);
        db.insert(SettingsContract.SettingsContractEntry.TABLE_NAME, null, contentValues);
    }

    public Cursor getInformation(SQLiteDatabase db) {
        String[] list = {SettingsContract.SettingsContractEntry.COLUMN_NAME_START,
                SettingsContract.SettingsContractEntry.COLUMN_NAME_LANGUAGE};
        return db.query(SettingsContract.SettingsContractEntry.TABLE_NAME, list, null, null, null, null, null);
    }

    public boolean updateInformation(String oldLanguage, String language, SQLiteDatabase db) {
        if (!oldLanguage.equals(language)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SettingsContract.SettingsContractEntry.COLUMN_NAME_LANGUAGE, language);
            String selection = SettingsContract.SettingsContractEntry.COLUMN_NAME_LANGUAGE + " LIKE ?";
            String[] selectionArgs = {oldLanguage};
            db.update(SettingsContract.SettingsContractEntry.TABLE_NAME, contentValues, selection, selectionArgs);
            return true;
        }
        return false;
    }

    public boolean isFirstStartUp() {
        Cursor cursor = getInformation(getReadableDatabase());
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals("true")) {
                    return false;
                }
            } while (cursor.moveToNext());
        }
        return true;
    }

    public String getLanguage(SQLiteDatabase db) {
        Cursor cursor = getInformation(db);
        if (cursor.moveToFirst()) {
                return cursor.getString(1);
        }
        return "";
    }
}
