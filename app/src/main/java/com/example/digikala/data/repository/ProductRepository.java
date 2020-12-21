package com.example.digikala.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.network.parameter.RequestParams;
import com.example.digikala.data.network.retrofit.RetrofitInstance;
import com.example.digikala.data.network.retrofit.WooCommerceService;
import com.example.digikala.utillity.ListType;
import com.example.digikala.utillity.State;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    private static ProductRepository sProductRepository;
    private WooCommerceService mCommerceService;
    private final Retrofit mRetrofit = RetrofitInstance.getInstance();
    private final Retrofit mRetrofitCategoryProducts = RetrofitInstance.getProductsOfCategory();
    private final Retrofit mRetrofitSingleProduct = RetrofitInstance.getInstanceSingleProduct();
    private final Retrofit mRetrofitSearchProduct = RetrofitInstance.getProductsOfSearch();

    private final MutableLiveData<List<Product>> mRecentProductLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mPopularProductLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mRatingProductLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mProductCategoryLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mProductsSearchResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<Product> mSingleProductLiveData = new MutableLiveData<>();

    private final MutableLiveData<State> mLoadingHomeFragmentStateLiveData = new MutableLiveData<>();

    private final List<Product> mOldRecentProduct = new ArrayList<>();
    private final List<Product> mOldPopularProduct = new ArrayList<>();
    private final List<Product> mOldRatingProduct = new ArrayList<>();
    private State mStateRecentProduct = State.LOADING;
    private State mStateRatingProduct = State.LOADING;
    private State mStatePopularProduct = State.LOADING;

    public static ProductRepository getInstance() {
        if (sProductRepository == null)
            sProductRepository = new ProductRepository();
        return sProductRepository;
    }

    public ProductRepository() {
        //mContext = context.getApplicationContext();
    }

    public void fetchProducts(ListType listType, int page) {
        mCommerceService = mRetrofit.create(WooCommerceService.class);
        Call<List<Product>> listCall;
        switch (listType) {
            case RECENT_PRODUCT:
                listCall = mCommerceService.listAllProduct(RequestParams.BASE_PARAM, page);
                listCall.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.isSuccessful()) {
                            if (response.body().size() > 0) {
                                if (page == 1) {
                                    mRecentProductLiveData.setValue(response.body());
                                } else {
                                    List<Product> list = mRecentProductLiveData.getValue();
                                    list.addAll(response.body());
                                    mRecentProductLiveData.setValue(list);
                                }
                                mStateRecentProduct = State.NAVIGATE;
                                setHomeFragmentState();
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
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            if (response.body().size() > 0) {
                                if (page == 1) {
                                    mPopularProductLiveData.setValue(response.body());
                                } else {
                                    List<Product> list = mPopularProductLiveData.getValue();
                                    list.addAll(response.body());
                                    mPopularProductLiveData.setValue(list);
                                }
                                mStatePopularProduct = State.NAVIGATE;
                                setHomeFragmentState();
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
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            if (response.body().size() > 0) {
                                if (page == 1) {
                                    mRatingProductLiveData.setValue(response.body());
                                } else {
                                    List<Product> list = mRatingProductLiveData.getValue();
                                    list.addAll(response.body());
                                    mRatingProductLiveData.setValue(list);
                                }
                                mStateRatingProduct = State.NAVIGATE;
                                setHomeFragmentState();
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

    public void fetchCategoryProduct(int categoryId, int page) {
        mCommerceService = mRetrofitCategoryProducts.create(WooCommerceService.class);
        Call<List<Product>> listCall = mCommerceService.productListOfCategory(RequestParams.BASE_PARAM, categoryId, page);
        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().size() > 0) {
                        if (page == 1) {
                            mProductCategoryLiveData.setValue(response.body());
                        } else {
                            List<Product> list = mProductCategoryLiveData.getValue();
                            list.addAll(response.body());
                            mProductCategoryLiveData.setValue(list);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public void fetchProduct(String id) {
        mCommerceService = mRetrofitSingleProduct.create(WooCommerceService.class);
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

    public void fetchSearchProducts(int page, String searchWord) {
        mCommerceService = mRetrofitSearchProduct.create(WooCommerceService.class);
        Call<List<Product>> listCall = mCommerceService.searchProducts(RequestParams.BASE_PARAM, searchWord, page);
        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        if (page == 1) {
                            mProductsSearchResultLiveData.setValue(response.body());
                        } else {
                            List<Product> list = mProductsSearchResultLiveData.getValue();
                            list.addAll(response.body());
                            mProductsSearchResultLiveData.setValue(list);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("MAJID", t.toString() + "fetchSearchProducts", t);
            }
        });
    }

    private void setHomeFragmentState() {
        if (mStateRecentProduct == State.NAVIGATE && mStatePopularProduct == State.NAVIGATE &&
                mStateRatingProduct == State.NAVIGATE) {
            mLoadingHomeFragmentStateLiveData.setValue(State.NAVIGATE);
        }
    }


    public MutableLiveData<List<Product>> getRecentProductLiveData() {
        fetchProducts(ListType.RECENT_PRODUCT, 1);
        return mRecentProductLiveData;
    }

    public MutableLiveData<List<Product>> getPopularProductLiveData() {
        fetchProducts(ListType.POPULAR_PRODUCT, 1);
        return mPopularProductLiveData;
    }

    public MutableLiveData<List<Product>> getRatingProductLiveData() {
        fetchProducts(ListType.RATING_PRODUCT, 1);
        return mRatingProductLiveData;
    }

    public MutableLiveData<List<Product>> getProductCategoryLiveData(int id) {
        fetchCategoryProduct(id, 1);
        return mProductCategoryLiveData;
    }

    public MutableLiveData<Product> getSingleProductLiveData() {
        return mSingleProductLiveData;
    }

    public MutableLiveData<List<Product>> getProductsSearchResultLiveData(String word) {
        fetchSearchProducts(1, word);
        return mProductsSearchResultLiveData;
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

    public MutableLiveData<State> getLoadingHomeFragmentStateLiveData() {
        return mLoadingHomeFragmentStateLiveData;
    }
}
