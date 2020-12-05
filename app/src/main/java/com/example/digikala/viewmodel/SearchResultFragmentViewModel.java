package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.repository.ProductRepository;

import java.util.List;

public class SearchResultFragmentViewModel extends ProductStrategyViewModel {

    public SearchResultFragmentViewModel(@NonNull Application application , String word) {
        super(application, ProductRepository.getInstance().getProductsSearchResultLiveData(word));
    }
}
