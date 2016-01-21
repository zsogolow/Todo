package com.baggins.frodo.lib.datasource.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baggins.frodo.lib.datasource.db.LocalDbHelper;
import com.baggins.frodo.lib.datasource.dto.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZachS on 1/10/2016.
 */
public class CategoryDAO extends AbstractBaseDAO<CategoryDTO> {
    public CategoryDAO(Context context) {
        super(context, LocalDbHelper.CATEGORY_TABLE);
    }

    @Override
    public List<CategoryDTO> get() {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        String selectStatement = "SELECT * FROM " + mTableName;

        SQLiteDatabase database = mDb.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectStatement, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(LocalDbHelper.CATEGORY_ID));
                String catName = cursor.getString(cursor.getColumnIndex(LocalDbHelper.CATEGORY_NAME));
                categoryDTOs.add(new CategoryDTO(id, catName));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return categoryDTOs;
    }

    @Override
    public CategoryDTO get(int id) {
        return null;
    }

    @Override
    public CategoryDTO insert(CategoryDTO entityToInsert) {
        SQLiteDatabase database = mDb.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(LocalDbHelper.CATEGORY_NAME, entityToInsert.getCategoryName());

        long id = database.insert(mTableName, null, cv);
        entityToInsert.setId(id);

        database.close();
        return entityToInsert;
    }

    @Override
    public CategoryDTO update(CategoryDTO entityToUpdate) {
        SQLiteDatabase database = mDb.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(LocalDbHelper.CATEGORY_NAME, entityToUpdate.getCategoryName());

        database.update(mTableName, cv, LocalDbHelper.CATEGORY_TABLE + " = ?",
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
