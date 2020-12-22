package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.repository.ProductRepository;
import com.example.digikala.utillity.ListType;
import com.example.digikala.utillity.State;

import java.util.List;

public abstract class ProductStrategyViewModel extends AndroidViewModel {

    private LiveData<List<Product>> mProductLiveData;
    private MutableLiveData<Product> mProductSelectedLiveData = new MutableLiveData<>();
    private ProductRepository mProductRepository;
    private int mId;


    public ProductStrategyViewModel(@NonNull Application application , MutableLiveData<List<Product>> listLiveData) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mProductLiveData = listLiveData;
    }

    public ProductStrategyViewModel(@NonNull Application application, MutableLiveData<List<Product>> listLiveData, int id) {
        super(application);
        mId = id;
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

    public Product getProduct(int position) {
        return mProductLiveData.getValue().get(position);
    }

    public void updateList(ListType listType, int page) {
        mProductRepository.fetchProducts(listType, page);
    }

    public void updateSearchList(int page , String searchWord){
        mProductRepository.fetchSearchProducts(page , searchWord);
    }

    public void updateCategoryProductsList(int page) {
        ProductRepository.getInstance().fetchCategoryProduct(mId, page);
    }

    public void onItemSelected(int position, ListType listType) {
        mProductSelectedLiveData.setValue(mProductLiveData.getValue().get(position));
    }

    public LiveData<State> getFragmentState(){
        return mProductRepository.getLoadingHomeFragmentStateLiveData();
    }

    public void setFragmentState(State state){
        mProductRepository.getLoadingHomeFragmentStateLiveData().setValue(state);
    }

}
