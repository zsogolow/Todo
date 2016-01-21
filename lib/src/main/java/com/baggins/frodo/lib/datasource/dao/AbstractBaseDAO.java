package com.baggins.frodo.lib.datasource.dao;

import android.content.Context;

import com.baggins.frodo.lib.datasource.db.LocalDbHelper;

/**
 * Created by ZachS on 1/10/2016.
 */
public abstract class AbstractBaseDAO<T> implements IBaseDAO<T> {
    protected Context mContext;
    protected LocalDbHelper mDb;
    protected String mTableName;

    public AbstractBaseDAO(Context context, String tblName) {
        mContext = context;
        mDb = new LocalDbHelper(context);
        mTableName = tblName;
    }
}
