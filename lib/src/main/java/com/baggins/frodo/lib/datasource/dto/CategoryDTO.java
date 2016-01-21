package com.baggins.frodo.lib.datasource.dto;

/**
 * Created by ZachS on 1/10/2016.
 */
public class CategoryDTO extends BaseDTO {
    private static final String DefaultCategoryName = "Default";

    private int mId;
    private String mCategoryName;

    public CategoryDTO() {
        this(DefaultCategoryName);
    }

    public CategoryDTO(String categoryName) {
        this(-1, categoryName);
    }

    public CategoryDTO(int id, String categoryName) {
        mId = id;
        mCategoryName = categoryName;
    }

    @Override
    public int getId() {
        return mId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setId(long id) {
        this.mId = (int)id;
    }

    public void setCategoryName(String name) { this.mCategoryName = name; }
}
