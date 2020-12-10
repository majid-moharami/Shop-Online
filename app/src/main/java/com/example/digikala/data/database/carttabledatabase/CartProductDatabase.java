package com.example.digikala.data.database.carttabledatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.digikala.data.database.dao.CartProductDBDao;
import com.example.digikala.data.database.entity.CartProduct;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CartProduct.class}, version = 1 , exportSchema = false)
public abstract class CartProductDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "cart_product.db";

    public abstract CartProductDBDao getDao();

}
