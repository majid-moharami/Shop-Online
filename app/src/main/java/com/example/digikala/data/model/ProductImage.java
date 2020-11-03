package com.example.digikala.data.model;

public class ProductImage {
    private String mImageURL;
    private String mName;
    private String mId;

    public ProductImage(String imageURL, String name, String id) {
        mImageURL = imageURL;
        mName = name;
        mId = id;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String imageURL) {
        mImageURL = imageURL;
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
