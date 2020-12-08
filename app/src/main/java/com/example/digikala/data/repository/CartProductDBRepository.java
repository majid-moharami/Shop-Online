package com.example.digikala.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.digikala.data.database.carttabledatabase.CartProductDatabase;
import com.example.digikala.data.database.dao.CartProductDBDao;
import com.example.digikala.data.database.entity.CartProduct;

import java.util.List;

public class CartProductDBRepository {
    private static CartProductDBRepository sCartProductDBRepository;
    private CartProductDBDao mCartProductDBDao;


    public synchronized static CartProductDBRepository getInstance(Context context){
        if (sCartProductDBRepository == null)
            sCartProductDBRepository = new CartProductDBRepository(context.getApplicationContext());
        return sCartProductDBRepository;
    }

    private CartProductDBRepository(Context context) {
        CartProductDatabase cartProductDatabase = CartProductDatabase.getDatabase(context);
        mCartProductDBDao = cartProductDatabase.getDao();
    }

    public void insert(CartProduct cartProduct){
        CartProductDatabase.databaseWriteExecutor.execute(()-> mCartProductDBDao.insert(cartProduct));
        //mDatabase.getDao().insert(cartProduct);
    }

    public void update(CartProduct cartProduct){
        CartProductDatabase.databaseWriteExecutor.execute(()-> mCartProductDBDao.update(cartProduct));
        //mDatabase.getDao().update(cartProduct);
    }

    public void delete(CartProduct cartProduct){
        CartProductDatabase.databaseWriteExecutor.execute(()-> mCartProductDBDao.delete(cartProduct));
       // mDatabase.getDao().delete(cartProduct);
    }

    public LiveData<List<CartProduct>> getAllCartProduct(){
        return mCartProductDBDao.getAll();
    }

    public LiveData<CartProduct> getCartProduct(int id){
        return mCartProductDBDao.getCartProductById(id);
    }
}
