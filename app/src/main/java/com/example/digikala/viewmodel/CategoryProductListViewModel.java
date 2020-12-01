package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.repository.ProductRepository;
import com.example.digikala.utillity.ListType;

import java.util.List;

public class CategoryProductListViewModel extends ProductStrategyViewModel {
    private int mCategoryId;
    public CategoryProductListViewModel(@NonNull Application application, int id) {
        super(application, ProductRepository.getInstance().getProductCategoryLiveData(id), id);
        mCategoryId = id;
    }

    public int getCategoryId() {
        return mCategoryId;
    }
}
