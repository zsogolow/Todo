package com.baggins.frodo.lib.datasource.dto;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ZachS on 1/10/2016.
 */
public class TodoDTO extends BaseDTO {
    private int mId;
    private int mCategoryId;
    private boolean mComplete;
    private String mTodo;
    private Date mCreatedAt;

    public TodoDTO() {
        this("");
    }

    public TodoDTO(String todo) {
        this(todo, -1);
    }

    public TodoDTO(String todo, int category) {
        this(-1, todo, category);
    }

    public TodoDTO(int id, String todo, int category) {
        this(id, todo, category, Calendar.getInstance().getTime());
    }

    public TodoDTO(int id, String todo, int category, Date date) {
        mId = id;
        mCategoryId = category;
        mComplete = false;
        mTodo = todo;
        mCreatedAt = date;
    }

    @Override
    public int getId() {
        return mId;
    }

    public String getTodo() {
        return mTodo;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public int getComplete() {
        return mComplete ? 1 : 0;
    }

    public void setId(long id) {
        this.mId = (int) id;
    }

    public void setCategoryId(int categoryId) {
        this.mCategoryId = categoryId;
    }

    public void setComplete(boolean complete) { this.mComplete = complete; }

    public void setTodo(String todo) { this.mTodo = todo; }
}
