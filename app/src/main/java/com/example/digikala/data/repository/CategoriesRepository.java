package com.example.digikala.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.category.Category;
import com.example.digikala.data.network.parameter.RequestParams;
import com.example.digikala.data.network.retrofit.RetrofitInstance;
import com.example.digikala.data.network.retrofit.WooCommerceService;
import com.example.digikala.utillity.HeadCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoriesRepository {
    private static CategoriesRepository sCategoriesRepository;

    private WooCommerceService mCommerceService;
    private Retrofit mRetrofit = RetrofitInstance.getInstanceSubCategories();

    private MutableLiveData<List<Category>> mSubDigitalCategory = new MutableLiveData<>();
    private MutableLiveData<List<Category>> mSubSpecialSaleCategory = new MutableLiveData<>();
    private MutableLiveData<List<Category>> mSubArtCategory = new MutableLiveData<>();
    private MutableLiveData<List<Category>> mSubMarketCategory = new MutableLiveData<>();
    private MutableLiveData<List<Category>> mSubHealthCategory = new MutableLiveData<>();
    private MutableLiveData<List<Category>> mSubClothingCategory = new MutableLiveData<>();

    public static CategoriesRepository getInstance(){
        if (sCategoriesRepository == null)
            sCategoriesRepository = new CategoriesRepository();

        return sCategoriesRepository;
    }

    public CategoriesRepository() {

    }

    public void fetchCategory(HeadCategory headCategory , int parentId){
        mCommerceService = mRetrofit.create(WooCommerceService.class);
        Call<List<Category>> listCall = mCommerceService.SubCategories(RequestParams.SUB_CATEGORIES,parentId);
        listCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                switch (headCategory){
                    case DIGITAL:
                        mSubDigitalCategory.setValue(response.body());
                        break;
                    case HEALTH:
                        mSubHealthCategory.setValue(response.body());
                        break;
                    case SPECIAL_SALE:
                        mSubSpecialSaleCategory.setValue(response.body());
                        break;
                    case SUPER_MARKET:
                        mSubMarketCategory.setValue(response.body());
                        break;
                    case BOOK_ART:
                        mSubArtCategory.setValue(response.body());
                        break;
                    case FASHION_CLOTHING:
                        mSubClothingCategory.setValue(response.body());
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    //list getter setter----------------------------------------------------------------------------
    public MutableLiveData<List<Category>> getSubDigitalCategory() {
        return mSubDigitalCategory;
    }

    public MutableLiveData<List<Category>> getSubSpecialSaleCategory() {
        return mSubSpecialSaleCategory;
    }

    public MutableLiveData<List<Category>> getSubArtCategory() {
        return mSubArtCategory;
    }

    public MutableLiveData<List<Category>> getSubMarketCategory() {
        return mSubMarketCategory;
    }

    public MutableLiveData<List<Category>> getSubHealthCategory() {
        return mSubHealthCategory;
    }

    public MutableLiveData<List<Category>> getSubClothingCategory() {
        return mSubClothingCategory;
    }
    //----------------------------------------------------------------------------------------------
}
