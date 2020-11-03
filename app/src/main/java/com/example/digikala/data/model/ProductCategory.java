package com.example.digikala.data.model;

public class ProductCategory {
    private String mName;
    private String mId;

    public ProductCategory(String name, String id) {
        mName = name;
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
