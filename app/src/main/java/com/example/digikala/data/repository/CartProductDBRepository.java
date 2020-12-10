package com.example.digikala.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.digikala.data.database.carttabledatabase.CartProductDatabase;
import com.example.digikala.data.database.dao.CartProductDBDao;
import com.example.digikala.data.database.entity.CartProduct;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.network.parameter.RequestParams;
import com.example.digikala.data.network.retrofit.RetrofitInstance;
import com.example.digikala.data.network.retrofit.WooCommerceService;
import com.example.digikala.utillity.State;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartProductDBRepository {
    private static CartProductDBRepository sCartProductDBRepository;
    private CartProductDatabase mDatabase;
    private Retrofit mRetrofitSingleProduct = RetrofitInstance.getInstanceSingleProduct();
    private WooCommerceService mCommerceService;
    private List<CartProduct> mCartProductList = new ArrayList<>();
    private MutableLiveData<List<Product>> mProductLiveData = new MutableLiveData<>();
    private MutableLiveData<State> mStateMutableLiveData = new MutableLiveData<>();

    public synchronized static CartProductDBRepository getInstance(Context context){
        if (sCartProductDBRepository == null)
            sCartProductDBRepository = new CartProductDBRepository(context.getApplicationContext());
        return sCartProductDBRepository;
    }

    private CartProductDBRepository(Context context) {
        mDatabase = Room.databaseBuilder(context,
                CartProductDatabase.class,
                CartProductDatabase.DATABASE_NAME).allowMainThreadQueries().build();
        mCommerceService = mRetrofitSingleProduct.create(WooCommerceService.class);
        mCartProductList = getAllCartProduct();
    }

    public void insert(CartProduct cartProduct){

        mDatabase.getDao().insert(cartProduct);
    }

    public void update(CartProduct cartProduct){

        mDatabase.getDao().update(cartProduct);
    }

    public void delete(CartProduct cartProduct){
         mDatabase.getDao().delete(cartProduct);
    }

    public List<CartProduct> getAllCartProduct(){
        return mDatabase.getDao().getAll();
    }

    public CartProduct getCartProduct(int id){
        return mDatabase.getDao().getCartProductById(id);
    }

    public void fetchAllProduct() {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < mCartProductList.size(); i++) {

            String id = String.valueOf(mCartProductList.get(i).getProductId());

            Call<Product> productCall= mCommerceService.product(id, RequestParams.BASE_PARAM);
            productCall.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {
                        list.add(response.body());
                        mProductLiveData.setValue(list);
                        if (mCartProductList.size() == list.size()){
                            mStateMutableLiveData.setValue(State.NAVIGATE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    Log.d("MAJID", t.toString(), t);
                }
            });

        }

        Log.d("LIST", "fetchAll");
    }

    public MutableLiveData<List<Product>> getProductLiveData() {
        return mProductLiveData;
    }

    public MutableLiveData<State> getStateMutableLiveData() {
        return mStateMutableLiveData;
    }

    public List<CartProduct> getCartProductList() {
        return mCartProductList;
    }
}
