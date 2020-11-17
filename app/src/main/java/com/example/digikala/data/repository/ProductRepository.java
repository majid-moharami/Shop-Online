package com.example.digikala.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.network.parameter.RequestParams;
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
    private Retrofit retrofitSingleProduct = RetrofitInstance.getInstanceSingleProduct();

    private MutableLiveData<List<Product>> mRecentProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mPopularProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mRatingProductLiveData = new MutableLiveData<>();
    private MutableLiveData<Product> mSingleProductLiveData = new MutableLiveData<>();

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
        Call<List<Product>> listCall = mCommerceService.listAllProduct(RequestParams.BASE_PARAM);
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

    public void fetchProduct(String id){
        mCommerceService = retrofitSingleProduct.create(WooCommerceService.class);
        Call<Product> productCall = mCommerceService.product(id , RequestParams.BASE_PARAM);
        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                mSingleProductLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d("MAJID" , t.toString() , t);
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

    public MutableLiveData<Product> getSingleProductLiveData() {
        return mSingleProductLiveData;
    }
}
