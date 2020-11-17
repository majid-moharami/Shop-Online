package com.example.digikala.data.model.category;

public class Category {
    private int mId;
    private String mName;
    private int mParent;
    private CategoryImage mCategoryImage;

    public Category() {
    }

    public Category(int id, String name, int parent, CategoryImage categoryImage) {
        mId = id;
        mName = name;
        mParent = parent;
        mCategoryImage = categoryImage;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getParent() {
        return mParent;
    }

    public void setParent(int parent) {
        mParent = parent;
    }

    public CategoryImage getCategoryImage() {
        return mCategoryImage;
    }

    public void setCategoryImage(CategoryImage categoryImage) {
        mCategoryImage = categoryImage;
    }
}
