package com.example.digikala.viewmodel;

import android.app.Application;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.digikala.data.model.Product;
import com.example.digikala.data.repository.ProductRepository;

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

}
