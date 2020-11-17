package com.example.digikala.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.digikala.data.model.category.Category;
import com.example.digikala.data.network.parameter.RequestParams;
import com.example.digikala.data.repository.CategoriesRepository;
import com.example.digikala.utillity.HeadCategory;

import java.util.List;

public class CategoryFragmentViewModel extends ViewModel {
    private CategoriesRepository mCategoriesRepository;

    private LiveData<List<Category>> mSubDigitalLiveData ;
    private LiveData<List<Category>> mSubSpecialSaleLiveData ;
    private LiveData<List<Category>> mSubHealthLiveData ;
    private LiveData<List<Category>> mSubArtLiveData ;
    private LiveData<List<Category>> mSubMarketLiveData ;
    private LiveData<List<Category>> mSubClothingLiveData ;

    public CategoryFragmentViewModel() {
        mCategoriesRepository = CategoriesRepository.getInstance();
        mSubDigitalLiveData = mCategoriesRepository.getSubDigitalCategory();
        mSubSpecialSaleLiveData = mCategoriesRepository.getSubSpecialSaleCategory();
        mSubHealthLiveData = mCategoriesRepository.getSubHealthCategory();
        mSubArtLiveData = mCategoriesRepository.getSubArtCategory();
        mSubMarketLiveData = mCategoriesRepository.getSubMarketCategory();
        mSubClothingLiveData = mCategoriesRepository.getSubClothingCategory();

        mCategoriesRepository.fetchCategory(HeadCategory.DIGITAL , RequestParams.DIGITAL_ID);
        mCategoriesRepository.fetchCategory(HeadCategory.SPECIAL_SALE , RequestParams.SPECIAL_SALE_ID);
        mCategoriesRepository.fetchCategory(HeadCategory.HEALTH , RequestParams.HEALTH_ID);
        mCategoriesRepository.fetchCategory(HeadCategory.BOOK_ART , RequestParams.BOOK_ART_ID);
        mCategoriesRepository.fetchCategory(HeadCategory.SUPER_MARKET , RequestParams.SUPER_MARKET_ID);
        mCategoriesRepository.fetchCategory(HeadCategory.FASHION_CLOTHING , RequestParams.FASHION_CLOTHING_ID);
    }
}
