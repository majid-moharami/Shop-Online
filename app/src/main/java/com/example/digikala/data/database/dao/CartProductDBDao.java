package com.example.digikala.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.digikala.data.database.entity.CartProduct;

import java.util.List;

@Dao
public interface CartProductDBDao {

    @Query("select * from cart_product")
    List<CartProduct> getAll();

    @Query("select * from cart_product where ProductId == :productId")
    CartProduct getCartProductById(Integer productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartProduct cartProduct);

    @Update
    void update(CartProduct cartProduct);

    @Delete
    void delete(CartProduct cartProduct);

}
