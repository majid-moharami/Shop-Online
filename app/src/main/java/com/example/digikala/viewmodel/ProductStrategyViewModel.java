package com.example.digikala.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.repository.ProductRepository;
import com.example.digikala.ui.activity.ProductListActivity;
import com.example.digikala.utillity.ListType;

import java.util.List;

public abstract class ProductStrategyViewModel extends AndroidViewModel {

    private LiveData<List<Product>> mProductLiveData;
    private MutableLiveData<Product> mProductSelectedLiveData = new MutableLiveData<>();
    private ProductRepository mProductRepository;


    public ProductStrategyViewModel(@NonNull Application application , MutableLiveData<List<Product>> listLiveData) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mProductLiveData = listLiveData;
    }

    public LiveData<List<Product>> getProductLiveData() {
        return mProductLiveData;
    }

    public MutableLiveData<Product> getProductSelectedLiveData() {
        return mProductSelectedLiveData;
    }


    public Product getProduct(int position, ListType listType) {
        return mProductLiveData.getValue().get(position);
    }


    public void updateList(ListType listType, int page) {
        mProductRepository.fetchProducts(listType, page);
    }

    public void onItemSelected(int position, ListType listType) {
        mProductSelectedLiveData.setValue(mProductLiveData.getValue().get(position));
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
