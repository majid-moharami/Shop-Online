package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.repository.ProductRepository;

public class DetailFragmentViewModel extends AndroidViewModel {

    private ProductRepository mRepository = ProductRepository.getInstance();
    private MutableLiveData<Product> mProductSubjectLiveData= new MutableLiveData<>();
    private LiveData<Product> mProductLiveData ;

    public DetailFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductLiveData = mRepository.getSingleProductLiveData();
    }

    public LiveData<Product> getProductLiveData() {
        return mProductLiveData;
    }

    public MutableLiveData<Product> getProductSubjectLiveData() {
        return mProductSubjectLiveData;
    }

    public void setProductSubjectLiveData(Product product) {
        mProductSubjectLiveData.setValue(product);
    }

    public void fetchProduct(String id){
        mRepository.fetchProduct(id);
    }


}
