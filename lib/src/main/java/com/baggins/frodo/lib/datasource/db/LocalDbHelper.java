package com.baggins.frodo.lib.datasource.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZachS on 1/10/2016.
 */
public class LocalDbHelper extends SQLiteOpenHelper{

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todo.db";

    public static final String TODO_TABLE = "Todo.tbl";
    public static final String TODO_ID = "todo_id";
    public static final String TODO_CAT_ID = "todo_cat_id";
    public static final String TODO_COMPLETE = "todo_complete";
    public static final String TODO_MSG = "todo_msg";
    public static final String TODO_CREATED_AT = "todo_created_at";

    public static final String CATEGORY_TABLE = "Category.tbl";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";

    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE
            + "(" + TODO_ID + " INTEGER PRIMARY KEY, " + TODO_CAT_ID + " INTEGER, "
            + TODO_COMPLETE + " INTEGER, " + TODO_MSG + " TEXT, " + TODO_CREATED_AT
            + " TEXT)";

    private static final String CREATE_CATEGORY_TABLE = "CREATE TABLE " + CATEGORY_TABLE
            + "(" + CATEGORY_ID + " INTEGER PRIMARY KEY, " + CATEGORY_NAME + ")";


    public LocalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);

        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_NAME, "General");
        long categoryId = db.insert(CATEGORY_TABLE, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
