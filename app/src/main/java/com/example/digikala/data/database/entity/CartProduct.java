package com.example.digikala.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_product")
public class CartProduct {
    @PrimaryKey
    @ColumnInfo(name = "ProductId")
    private int mProductId;

    @ColumnInfo(name = "Color")
    private String mColor;

    @ColumnInfo(name = "Count")
    private int mCount;

    @Ignore
    public CartProduct(int productId, String color, int count) {
        mProductId = productId;
        mColor = color;
        mCount = count;
    }

    public CartProduct() {
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
