package com.baggins.frodo.lib.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ZachS on 1/10/2016.
 */
public class Todo {
    private int mId;
    private Category mCategory;
    private boolean mComplete;
    private String mTodo;
    private Date mCreatedAt;

    public Todo() {
        this("");
    }

    public Todo(String todo) {
        this(todo, new Category());
    }

    public Todo(String todo, Category category) {
        this( -1, todo, category);
    }

    public Todo(int id, String todo, Category category) {
        this(id, todo, category, Calendar.getInstance().getTime());
    }

    public Todo(int id, String todo, Category category, Date date) {
        mId = id;
        mCategory = category;
        mComplete = false;
        mTodo = todo;
        mCreatedAt = date;
    }
}
