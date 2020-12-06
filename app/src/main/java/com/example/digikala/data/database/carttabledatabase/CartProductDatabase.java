package com.example.digikala.data.database.carttabledatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.digikala.data.database.dao.CartProductDBDao;
import com.example.digikala.data.database.entity.CartProduct;

@Database(entities = {CartProduct.class}, version = 1 , exportSchema = false)
public abstract class CartProductDatabase extends RoomDatabase {
    public abstract CartProductDBDao mCartProductDao();
}
