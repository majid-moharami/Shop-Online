package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.repository.ProductRepository;
import com.example.digikala.utillity.ListType;

import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {
    private ProductRepository mProductRepository;

    private LiveData<List<Product>> mRecentProductLiveData ;
    private LiveData<List<Product>> mPopularProductLiveData ;
    private LiveData<List<Product>> mRatingProductLiveData ;
    private MutableLiveData<Product> mProductSelectedLiveData = new MutableLiveData<>();

    public HomeFragmentViewModel(Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mRecentProductLiveData = mProductRepository.getRecentProductLiveData();
        mPopularProductLiveData = mProductRepository.getPopularProductLiveData();
        mRatingProductLiveData = mProductRepository.getRatingProductLiveData();
        mProductRepository.fetchRecentProduct();
        mProductRepository.fetchPopularProduct();
        mProductRepository.fetchRatingProduct();
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

    public Product getProduct(int position , ListType listType){
        Product product ;

        switch (listType){
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

    public String getProductPrice(int position , ListType listType){
        String productPrice = "";
        if (listType == null)
            listType = ListType.RECENT_PRODUCT;
        switch (listType){
            case RECENT_PRODUCT:
                productPrice = getRecentProductLiveData().getValue().get(position).getPriceToman();
                break;
            case POPULAR_PRODUCT:
                productPrice = getPopularProductLiveData().getValue().get(position).getPriceToman();
                break;
            case RATING_PRODUCT:
                productPrice = getRatingProductLiveData().getValue().get(position).getPriceToman();
                break;
        }
        return productPrice;
    }

    public void onItemSelectedRecentProduct(int position){
       Product product = mRecentProductLiveData.getValue().get(position);
       mProductSelectedLiveData.setValue(product);
    }

    public void onItemSelectedRatingProduct(int position){
        Product product = mRatingProductLiveData.getValue().get(position);
        mProductSelectedLiveData.setValue(product);
    }

    public void onItemSelectedPopularProduct(int position){
        Product product = mPopularProductLiveData.getValue().get(position);
        mProductSelectedLiveData.setValue(product);
    }

    public void onItemSelected(int position , ListType listType){
        switch (listType){
            case RECENT_PRODUCT:
                onItemSelectedRecentProduct(position);
                break;
            case POPULAR_PRODUCT:
                onItemSelectedPopularProduct(position);
                break;
            case RATING_PRODUCT:
                onItemSelectedRatingProduct(position);
                break;
        }
    }

}
