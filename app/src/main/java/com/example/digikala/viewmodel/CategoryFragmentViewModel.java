package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.category.Category;
import com.example.digikala.data.network.parameter.RequestParams;
import com.example.digikala.data.repository.CategoriesRepository;
import com.example.digikala.utillity.HeadCategory;

import java.util.List;

public class CategoryFragmentViewModel extends AndroidViewModel {
    private CategoriesRepository mCategoriesRepository;

    private LiveData<List<Category>> mSubDigitalLiveData ;
    private LiveData<List<Category>> mSubSpecialSaleLiveData ;
    private LiveData<List<Category>> mSubHealthLiveData ;
    private LiveData<List<Category>> mSubArtLiveData ;
    private LiveData<List<Category>> mSubMarketLiveData ;
    private LiveData<List<Category>> mSubClothingLiveData ;
    private MutableLiveData<Category> mCategorySubject = new MutableLiveData<>();

    public CategoryFragmentViewModel(Application application) {
        super(application);
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

    public Category getCategory(int position , HeadCategory headCategory){
        Category category = new Category();
        switch (headCategory){
            case DIGITAL:
                category = getSubDigitalLiveData().getValue().get(position);
                break;
            case HEALTH:
                category =  getSubHealthLiveData().getValue().get(position);
                break;
            case SPECIAL_SALE:
                category =  getSubSpecialSaleLiveData().getValue().get(position);
                break;
            case SUPER_MARKET:
                category =  getSubMarketLiveData().getValue().get(position);
                break;
            case BOOK_ART:
                category =  getSubArtLiveData().getValue().get(position);
                break;
            case FASHION_CLOTHING:
                category = getSubClothingLiveData().getValue().get(position);
                break;
        }
        return category;
    }

    //when this method invoke, set the SelectedMutableLiveData and run the observers in fragment.
    public void onCategorySubjectSelected(int position , HeadCategory headCategory){
        switch (headCategory){
            case DIGITAL:
                mCategorySubject.setValue(mSubDigitalLiveData.getValue().get(position));
                break;
            case HEALTH:
                mCategorySubject.setValue(mSubHealthLiveData.getValue().get(position));
                break;
            case SPECIAL_SALE:
                mCategorySubject.setValue(mSubSpecialSaleLiveData.getValue().get(position));
                break;
            case BOOK_ART:
                mCategorySubject.setValue(mSubArtLiveData.getValue().get(position));
                break;
            case SUPER_MARKET:
                mCategorySubject.setValue(mSubMarketLiveData.getValue().get(position));
                break;
            case FASHION_CLOTHING:
                mCategorySubject.setValue(mSubClothingLiveData.getValue().get(position));
                break;
        }
    }


    public MutableLiveData<Category> getCategorySubject() {
        return mCategorySubject;
    }

    public LiveData<List<Category>> getSubDigitalLiveData() {
        return mSubDigitalLiveData;
    }

    public LiveData<List<Category>> getSubSpecialSaleLiveData() {
        return mSubSpecialSaleLiveData;
    }

    public LiveData<List<Category>> getSubHealthLiveData() {
        return mSubHealthLiveData;
    }

    public LiveData<List<Category>> getSubArtLiveData() {
        return mSubArtLiveData;
    }

    public LiveData<List<Category>> getSubMarketLiveData() {
        return mSubMarketLiveData;
    }

    public LiveData<List<Category>> getSubClothingLiveData() {
        return mSubClothingLiveData;
    }
}
