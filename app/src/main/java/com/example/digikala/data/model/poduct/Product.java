package com.example.digikala.data.model.poduct;

import android.util.Log;

import com.example.digikala.data.model.ProductCategory;
import com.example.digikala.data.model.ProductImage;

import java.text.DecimalFormat;
import java.util.List;

public class Product {
    private String mName;
    private String mId;
    private String mDescription;
    private String mPrice;
    private String mRegularPrice;
    private String mAverageRating;
    private String mRatingCount;
    private List<ProductImage> mImages;
    private List<ProductCategory> mProductCategories;

    public Product(String name, String id, String description, String price, String regularPrice, String averageRating, String ratingCount, List<ProductImage> images, List<ProductCategory> productCategories) {
        mName = name;
        mId = id;
        mDescription = description;
        mPrice = price;
        mRegularPrice = regularPrice;
        mAverageRating = averageRating;
        mRatingCount = ratingCount;
        mImages = images;
        mProductCategories = productCategories;
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPrice() {
        String number = mPrice;
        if (!number.equals("")){
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
        }else return "0";
    }

    public String getPriceToman(){
        return getPrice() + " تومان ";
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getRegularPrice() {
        return mRegularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        mRegularPrice = regularPrice;
    }

    public String getAverageRating() {
        return mAverageRating;
    }

    public void setAverageRating(String averageRating) {
        mAverageRating = averageRating;
    }

    public String getRatingCount() {
        return mRatingCount;
    }

    public void setRatingCount(String ratingCount) {
        mRatingCount = ratingCount;
    }

    public List<ProductImage> getImages() {
        return mImages;
    }

    public void setImages(List<ProductImage> images) {
        mImages = images;
    }

    public List<ProductCategory> getProductCategories() {
        return mProductCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        mProductCategories = productCategories;
    }
}
