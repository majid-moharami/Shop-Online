package com.example.digikala.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_product")
public class CartProduct {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private Long mId;

    @ColumnInfo(name = "ProductId")
    private int mProductId;

    @ColumnInfo(name = "Color")
    private String mColor;

    @ColumnInfo(name = "Count")
    private int mCount;


    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }
}
