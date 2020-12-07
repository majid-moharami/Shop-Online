package com.example.digikala.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.digikala.data.database.carttabledatabase.CartProductDatabase;
import com.example.digikala.data.database.entity.CartProduct;

import java.util.List;

public class CartProductDBRepository {
    private static CartProductDBRepository sCartProductDBRepository;
    private CartProductDatabase mDatabase;

    public static CartProductDBRepository getInstance(Context context){
        if (sCartProductDBRepository == null)
            sCartProductDBRepository = new CartProductDBRepository(context.getApplicationContext());
        return sCartProductDBRepository;
    }

    public CartProductDBRepository(Context context) {
        mDatabase = Room.databaseBuilder(context.getApplicationContext(),
                CartProductDatabase.class,
                "CartProductDatabase").allowMainThreadQueries().build();
    }

    public void insert(CartProduct cartProduct){
        mDatabase.mCartProductDao().insert(cartProduct);
    }

    public void update(CartProduct cartProduct){
        mDatabase.mCartProductDao().update(cartProduct);
    }

    public void delete(CartProduct cartProduct){
        mDatabase.mCartProductDao().delete(cartProduct);
    }

    public List<CartProduct> getAllCartProduct(){
        return mDatabase.mCartProductDao().getAll();
    }

    public CartProduct getCartProduct(int id){
        return mDatabase.mCartProductDao().getCartProductById(id);
    }
}
