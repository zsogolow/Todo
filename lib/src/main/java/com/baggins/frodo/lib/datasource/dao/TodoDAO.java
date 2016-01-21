package com.baggins.frodo.lib.datasource.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baggins.frodo.lib.datasource.db.LocalDbHelper;
import com.baggins.frodo.lib.datasource.dto.TodoDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ZachS on 1/10/2016.
 */
public class TodoDAO extends AbstractBaseDAO<TodoDTO> {
    public static final DateFormat df = new SimpleDateFormat("dd/MM/YYYY - hh:mm:ss", Locale.US);

    public TodoDAO(Context context) {
        super(context, LocalDbHelper.TODO_TABLE);
    }

    @Override
    public List<TodoDTO> get() {
        List<TodoDTO> todos = new ArrayList<>();
        String selectStatement = "SELECT * FROM " + mTableName;

        SQLiteDatabase database = mDb.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectStatement, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(LocalDbHelper.TODO_ID));
                int catId = cursor.getInt(cursor.getColumnIndex(LocalDbHelper.TODO_CAT_ID));
                boolean isComplete = 1 == cursor.getInt(cursor.getColumnIndex(LocalDbHelper.TODO_COMPLETE));
                String todoMessage = cursor.getString(cursor.getColumnIndex(LocalDbHelper.TODO_MSG));
                Date created = null;
                try {
                    created = df.parse(cursor.getString(cursor.getColumnIndex(LocalDbHelper.TODO_CREATED_AT)));
                } catch (ParseException pe) {
                    created = Calendar.getInstance().getTime();
                }
                todos.add(new TodoDTO(id, todoMessage, catId, created));
            } while (cursor.moveToNext());
        }
        return todos;
    }

    @Override
    public TodoDTO get(int id) {
        return null;
    }

    @Override
    public TodoDTO insert(TodoDTO entityToInsert) {
        SQLiteDatabase database = mDb.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(LocalDbHelper.TODO_MSG, entityToInsert.getTodo());
        cv.put(LocalDbHelper.TODO_CAT_ID, entityToInsert.getCategoryId());
        cv.put(LocalDbHelper.TODO_COMPLETE, entityToInsert.getComplete());
        cv.put(LocalDbHelper.TODO_CREATED_AT, df.format(entityToInsert.getCreatedAt()));

        long id = database.insert(mTableName, null, cv);
        entityToInsert.setId(id);

        database.close();
        return entityToInsert;
    }

    @Override
    public TodoDTO update(TodoDTO entityToUpdate) {
        SQLiteDatabase database = mDb.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(LocalDbHelper.TODO_MSG, entityToUpdate.getTodo());
        cv.put(LocalDbHelper.TODO_CAT_ID, entityToUpdate.getCategoryId());
        cv.put(LocalDbHelper.TODO_COMPLETE, entityToUpdate.getComplete());
        cv.put(LocalDbHelper.TODO_CREATED_AT, df.format(entityToUpdate.getCreatedAt()));

        database.update(mTableName, cv, LocalDbHelper.TODO_ID + " = ?",
                new String[]{String.valueOf(entityToUpdate.getId())});

        database.close();
        return entityToUpdate;
    }

    @Override
    public void delete(String whereClause, String[] whereArgs) {
        SQLiteDatabase database = mDb.getWritableDatabase();
        database.delete(mTableName, whereClause, whereArgs);
        database.close();
    }
}
