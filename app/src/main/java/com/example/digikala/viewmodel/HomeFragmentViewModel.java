package com.example.digikala.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.repository.ProductRepository;
import com.example.digikala.ui.activity.ProductListActivity;
import com.example.digikala.utillity.ListType;

import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {
    private ProductRepository mProductRepository;
    private static final int mPage = 1;

    private LiveData<List<Product>> mRecentProductLiveData;
    private LiveData<List<Product>> mPopularProductLiveData;
    private LiveData<List<Product>> mRatingProductLiveData;
    private MutableLiveData<Product> mProductSelectedLiveData = new MutableLiveData<>();

    public HomeFragmentViewModel(Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mRecentProductLiveData = mProductRepository.getRecentProductLiveData();
        mPopularProductLiveData = mProductRepository.getPopularProductLiveData();
        mRatingProductLiveData = mProductRepository.getRatingProductLiveData();
        mProductRepository.fetchProducts(ListType.RECENT_PRODUCT, mPage);
        mProductRepository.fetchProducts(ListType.POPULAR_PRODUCT, mPage);
        mProductRepository.fetchProducts(ListType.RATING_PRODUCT, mPage);
    }

    public LiveData<List<Product>> getRecentProductLiveData() {
        return mRecentProductLiveData;
    }

    public LiveData<List<Product>> getPopularProductLiveData() {
        return mPopularProductLiveData;
    }

    public LiveData<List<Product>> getRatingProductLiveData() {
        return mRatingProductLiveData;
    }

    public MutableLiveData<Product> getProductSelectedLiveData() {
        return mProductSelectedLiveData;
    }

    public Product getProduct(int position, ListType listType) {
        Product product;

        switch (listType) {
            case RECENT_PRODUCT:
                product = getRecentProductLiveData().getValue().get(position);
                break;
            case POPULAR_PRODUCT:
                product = getPopularProductLiveData().getValue().get(position);
                break;
            default:
                product = getRatingProductLiveData().getValue().get(position);
                break;

        }
        return product;
    }

    public void updateList(ListType listType, int page) {
        mProductRepository.fetchProducts(listType, page);
    }

    //when this method invoke, set the SelectedMutableLiveData and run the observers in fragment.
    public void onItemSelected(int position, ListType listType) {
        switch (listType) {
            case RECENT_PRODUCT:
                mProductSelectedLiveData.setValue(mRecentProductLiveData.getValue().get(position));
                break;
            case POPULAR_PRODUCT:
                mProductSelectedLiveData.setValue(mPopularProductLiveData.getValue().get(position));
                break;
            case RATING_PRODUCT:
                mProductSelectedLiveData.setValue(mRatingProductLiveData.getValue().get(position));
                break;
        }
    }

    public void seeMoreRecentClick(){
        Intent intent = ProductListActivity.newIntent(getApplication() , -1 , ListType.RECENT_PRODUCT);
        getApplication().startActivity(intent);
    }

    public void seeMorePopularClick(){
        Intent intent = ProductListActivity.newIntent(getApplication() , -1 , ListType.POPULAR_PRODUCT);
        getApplication().startActivity(intent);
    }
    public void seeMoreRatingClick(){
        Intent intent = ProductListActivity.newIntent(getApplication() , -1 , ListType.RATING_PRODUCT);
        getApplication().startActivity(intent);
    }

}
