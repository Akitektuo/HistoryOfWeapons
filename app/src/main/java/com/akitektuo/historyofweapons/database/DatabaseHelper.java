package com.akitektuo.historyofweapons.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Akitektuo on 14.12.2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_QUESTION_QUERY = "CREATE TABLE " + DatabaseContract.QuestionContractEntry.TABLE_NAME + " (" +
            DatabaseContract.QuestionContractEntry.COLUMN_NAME_ID + " NUMBER," +
            DatabaseContract.QuestionContractEntry.COLUMN_NAME_IMAGE_DATA + " TEXT," +
            DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_EN + " TEXT," +
            DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_RO + " TEXT," +
            DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_EN + " TEXT," +
            DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_RO + " TEXT," +
            DatabaseContract.QuestionContractEntry.COLUMN_NAME_ANSWER + " NUMBER" + ");";

    private static final String DATABASE_WEAPON_QUERY = "CREATE TABLE " + DatabaseContract.WeaponContractEntry.TABLE_NAME + " (" +
            DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID + " NUMBER," +
            DatabaseContract.WeaponContractEntry.COLUMN_NAME_IMAGE_DATA + " TEXT," +
            DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_EN + " TEXT," +
            DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_RO + " TEXT," +
            DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME + " TEXT," +
            DatabaseContract.WeaponContractEntry.COLUMN_NAME_TYPE + " NUMBER" + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_QUESTION_QUERY);
        db.execSQL(DATABASE_WEAPON_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addQuestion(SQLiteDatabase database, String imageData, String questionEn, String questionRo, String variantsEn, String variantsRo, int answer){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_ID, getQuestionId() + 1);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_IMAGE_DATA, imageData);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_EN, questionEn);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_RO, questionRo);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_EN, variantsEn);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_RO, variantsRo);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_ANSWER, answer);
        database.insert(DatabaseContract.QuestionContractEntry.TABLE_NAME, null, contentValues);
    }

    public void addWeapon(SQLiteDatabase database, String imageData, String descriptionEn, String descriptionRo, String name, int type){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID, getWeaponId() + 1);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_IMAGE_DATA, imageData);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_EN, descriptionEn);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_RO, descriptionRo);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME, name);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_TYPE, type);
        database.insert(DatabaseContract.WeaponContractEntry.TABLE_NAME, null, contentValues);
    }

    public Cursor getQuestion(SQLiteDatabase database){
        String[] list = {DatabaseContract.QuestionContractEntry.COLUMN_NAME_ID,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_IMAGE_DATA,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_EN,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_RO,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_EN,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_RO,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_ANSWER};
        return database.query(DatabaseContract.QuestionContractEntry.TABLE_NAME, list, null, null, null, null, null);
    }

    public Cursor getWeapon(SQLiteDatabase database) {
        String[] list = {DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_IMAGE_DATA,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_EN,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_RO,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_TYPE};
        return database.query(DatabaseContract.WeaponContractEntry.TABLE_NAME, list, null, null, null, null, null);
    }

    public int getQuestionId() {
        int id = -1;
        Cursor cursor = getQuestion(getReadableDatabase());
        if (cursor.moveToLast()) {
            id = cursor.getInt(0);
        }
        return id;
    }

    public int getWeaponId() {
        int id = -1;
        Cursor cursor = getWeapon(getReadableDatabase());
        if (cursor.moveToLast()) {
            id = cursor.getInt(0);
        }
        return id;
    }

    public void editQuestion(SQLiteDatabase database, int id, String imageData, String questionEn, String questionRo, String variantsEn, String variantsRo, int answer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_ID, id);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_IMAGE_DATA, imageData);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_EN, questionEn);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_RO, questionRo);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_EN, variantsEn);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_RO, variantsRo);
        contentValues.put(DatabaseContract.QuestionContractEntry.COLUMN_NAME_ANSWER, answer);
        String selection = DatabaseContract.QuestionContractEntry.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        database.update(DatabaseContract.QuestionContractEntry.TABLE_NAME, contentValues, selection, selectionArgs);
    }

    public void editWeapon(SQLiteDatabase database, int id, String imageData, String descriptionEn, String descriptionRo, String name, int type) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID, id);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_IMAGE_DATA, imageData);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_EN, descriptionEn);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_RO, descriptionRo);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME, name);
        contentValues.put(DatabaseContract.WeaponContractEntry.COLUMN_NAME_TYPE, type);
        String selection = DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        database.update(DatabaseContract.WeaponContractEntry.TABLE_NAME, contentValues, selection, selectionArgs);
    }

    public void deleteQuestion(SQLiteDatabase database, int id){
        String selection = DatabaseContract.QuestionContractEntry.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        database.delete(DatabaseContract.QuestionContractEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void deleteWeapon(SQLiteDatabase database, int id) {
        String selection = DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        database.delete(DatabaseContract.WeaponContractEntry.TABLE_NAME, selection, selectionArgs);
    }

    public Cursor getQuestionForId(SQLiteDatabase database, int id) {
        String[] results = {DatabaseContract.QuestionContractEntry.COLUMN_NAME_ID,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_IMAGE_DATA,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_EN,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_QUESTION_RO,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_EN,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_VARIANTS_RO,
                DatabaseContract.QuestionContractEntry.COLUMN_NAME_ANSWER};
        String selection = DatabaseContract.QuestionContractEntry.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        return database.query(DatabaseContract.QuestionContractEntry.TABLE_NAME, results, selection, selectionArgs, null, null, null);
    }

    public Cursor getWeaponForId(SQLiteDatabase database, int id) {
        String[] results = {DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_IMAGE_DATA,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_EN,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_RO,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_TYPE};
        String selection = DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        return database.query(DatabaseContract.WeaponContractEntry.TABLE_NAME, results, selection, selectionArgs, null, null, null);
    }

    public Cursor getWeaponForType(SQLiteDatabase database, int type) {
        String[] results = {DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_IMAGE_DATA,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_EN,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_RO,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_TYPE};
        String selection = DatabaseContract.WeaponContractEntry.COLUMN_NAME_TYPE + " LIKE ?";
        String[] selectionArgs = {String.valueOf(type)};
        return database.query(DatabaseContract.WeaponContractEntry.TABLE_NAME, results, selection, selectionArgs, null, null, DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME + " COLLATE NOCASE ASC");
    }

    public Cursor getWeaponForName(SQLiteDatabase database, String name) {
        String[] results = {DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_IMAGE_DATA,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_EN,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_RO,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_TYPE};
        String selection = DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME + " LIKE ?";
        String[] selectionArgs = {name};
        return database.query(DatabaseContract.WeaponContractEntry.TABLE_NAME, results, selection, selectionArgs, null, null, null);
    }

    public Cursor getWeaponAlphabetically(SQLiteDatabase database) {
        String[] list = {DatabaseContract.WeaponContractEntry.COLUMN_NAME_ID,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_IMAGE_DATA,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_EN,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_DESCRIPTION_RO,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME,
                DatabaseContract.WeaponContractEntry.COLUMN_NAME_TYPE};
        return database.query(DatabaseContract.WeaponContractEntry.TABLE_NAME, list, null, null, null, null, DatabaseContract.WeaponContractEntry.COLUMN_NAME_NAME + " COLLATE NOCASE ASC");
    }

}
