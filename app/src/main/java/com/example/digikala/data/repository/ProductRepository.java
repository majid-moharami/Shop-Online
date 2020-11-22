package com.example.digikala.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.network.parameter.RequestParams;
import com.example.digikala.data.network.retrofit.RetrofitInstance;
import com.example.digikala.data.network.retrofit.WooCommerceService;
import com.example.digikala.utillity.ListType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    private static ProductRepository sProductRepository;
    private Context mContext;
    private WooCommerceService mCommerceService;
    private Retrofit retrofit = RetrofitInstance.getInstance();
    private Retrofit retrofitSingleProduct = RetrofitInstance.getInstanceSingleProduct();

    private MutableLiveData<List<Product>> mRecentProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mPopularProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mRatingProductLiveData = new MutableLiveData<>();
    private MutableLiveData<Product> mSingleProductLiveData = new MutableLiveData<>();

    private List<Product> mOldRecentProduct = new ArrayList<>();
    private List<Product> mOldPopularProduct = new ArrayList<>();
    private List<Product> mOldRatingProduct = new ArrayList<>();

    public static ProductRepository getInstance() {
        if (sProductRepository == null)
            sProductRepository = new ProductRepository();
        return sProductRepository;
    }

    public ProductRepository() {
        //mContext = context.getApplicationContext();
    }

    public void fetchProducts(ListType listType, int page) {
        mCommerceService = retrofit.create(WooCommerceService.class);
        Call<List<Product>> listCall;
        switch (listType) {
            case RECENT_PRODUCT:
                listCall = mCommerceService.listAllProduct(RequestParams.BASE_PARAM, page);
                listCall.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.isSuccessful()){
                            if (response.body().size()>0){
                                if (page == 1){
                                    mRecentProductLiveData.setValue(response.body());
                                }else {
                                    List<Product> list = mRecentProductLiveData.getValue();
                                    list.addAll(response.body());
                                    mRecentProductLiveData.setValue(list);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }
                });
                break;
            case POPULAR_PRODUCT:
                listCall = mCommerceService.listAllProduct(RequestParams.POPULAR_PRODUCT, page);
                listCall.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.isSuccessful()){
                            assert response.body() != null;
                            if (response.body().size()>0){
                                if (page == 1){
                                    mPopularProductLiveData.setValue(response.body());
                                }else {
                                    List<Product> list = mPopularProductLiveData.getValue();
                                    list.addAll(response.body());
                                    mPopularProductLiveData.setValue(list);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }
                });
                break;
            case RATING_PRODUCT:
                listCall = mCommerceService.listAllProduct(RequestParams.RATING_PRODUCT, page);
                listCall.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.isSuccessful()){
                            assert response.body() != null;
                            if (response.body().size()>0){
                                if (page == 1){
                                    mRatingProductLiveData.setValue(response.body());
                                }else {
                                    List<Product> list = mRatingProductLiveData.getValue();
                                    list.addAll(response.body());
                                    mRatingProductLiveData.setValue(list);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }
                });
                break;
        }
    }

    public void fetchProduct(String id) {
        mCommerceService = retrofitSingleProduct.create(WooCommerceService.class);
        Call<Product> productCall = mCommerceService.product(id, RequestParams.BASE_PARAM);
        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                mSingleProductLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d("MAJID", t.toString(), t);
            }
        });
    }


    public MutableLiveData<List<Product>> getRecentProductLiveData() {
        fetchProducts(ListType.RECENT_PRODUCT , 1);
        return mRecentProductLiveData;
    }

    public MutableLiveData<List<Product>> getPopularProductLiveData() {
        fetchProducts(ListType.POPULAR_PRODUCT , 1);
        return mPopularProductLiveData;
    }

    public MutableLiveData<List<Product>> getRatingProductLiveData() {
        fetchProducts(ListType.RATING_PRODUCT , 1);
        return mRatingProductLiveData;
    }

    public MutableLiveData<Product> getSingleProductLiveData() {
        return mSingleProductLiveData;
    }


    public List<Product> getOldRecentProduct() {
        return mOldRecentProduct;
    }

    public void updateOldRecentProduct(List<Product> oldRecentProduct) {
        mOldRecentProduct.addAll(oldRecentProduct);
    }

    public List<Product> getOldPopularProduct() {
        return mOldPopularProduct;
    }

    public void updateOldPopularProduct(List<Product> oldPopularProduct) {
        mOldPopularProduct.addAll(oldPopularProduct);
    }

    public List<Product> getOldRatingProduct() {
        return mOldRatingProduct;
    }

    public void updateOldRatingProduct(List<Product> oldRatingProduct) {
        mOldRatingProduct.addAll(oldRatingProduct);
    }
}
