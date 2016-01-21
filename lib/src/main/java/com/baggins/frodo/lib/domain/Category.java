package com.baggins.frodo.lib.domain;

/**
 * Created by ZachS on 1/10/2016.
 */
public class Category {
    private static final String DefaultCategoryName = "Default";

    private int mId;
    private String mCategoryName;

    public Category() {
       this(DefaultCategoryName);
    }

    public Category(String categoryName) {
        this(-1, categoryName);
    }

    public Category(int id, String categoryName) {
        mId = id;
        mCategoryName = categoryName;
    }
}
