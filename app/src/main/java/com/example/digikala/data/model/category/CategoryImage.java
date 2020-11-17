package com.example.digikala.data.model.category;

public class CategoryImage {
    private int mId;
    private String mSrc;

    public CategoryImage() {
    }

    public CategoryImage(int id, String src) {
        mId = id;
        mSrc = src;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getSrc() {
        return mSrc;
    }

    public void setSrc(String src) {
        mSrc = src;
    }
}
