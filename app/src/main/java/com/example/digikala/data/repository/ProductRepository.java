package com.example.digikala.data.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.Product;
import com.example.digikala.data.network.RequestParams;
import com.example.digikala.data.network.retrofit.RetrofitInstance;
import com.example.digikala.data.network.retrofit.WooCommerceService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    private static ProductRepository sProductRepository ;
    private Context mContext;
    private WooCommerceService mCommerceService;
    private Retrofit retrofit = RetrofitInstance.getInstance();

    private MutableLiveData<List<Product>> mRecentProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mPopularProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mRatingProductLiveData = new MutableLiveData<>();

    public static ProductRepository getInstance(){
        if (sProductRepository == null)
            sProductRepository = new ProductRepository();
        return sProductRepository;
    }

    public ProductRepository() {
        //mContext = context.getApplicationContext();

    }

    public void fetchRecentProduct(){
        mCommerceService = retrofit.create(WooCommerceService .class);
        Call<List<Product>> listCall = mCommerceService.listAllProduct(RequestParams.RECENT_PRODUCT);
        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mRecentProductLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public void fetchPopularProduct(){
        mCommerceService = retrofit.create(WooCommerceService .class);
        Call<List<Product>> listCall = mCommerceService.listAllProduct(RequestParams.POPULAR_PRODUCT);
        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mPopularProductLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public void fetchRatingProduct(){
        mCommerceService = retrofit.create(WooCommerceService.class);
        Call<List<Product>> listCall = mCommerceService.listAllProduct(RequestParams.RATING_PRODUCT);
        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mRatingProductLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Product>> getRecentProductLiveData() {
        return mRecentProductLiveData;
    }

    public MutableLiveData<List<Product>> getPopularProductLiveData() {
        return mPopularProductLiveData;
    }

    public MutableLiveData<List<Product>> getRatingProductLiveData() {
        return mRatingProductLiveData;
    }
}
