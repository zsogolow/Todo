package com.baggins.frodo.lib.datasource.dao;

import java.util.List;

/**
 * Created by ZachS on 1/10/2016.
 */
public interface IBaseDAO<T> {
    List<T> get();
    T get(int id);
    T insert(T entityToInsert);
    T update(T entityToUpdate);
    void delete(String whereClause, String[] whereArgs);
}
