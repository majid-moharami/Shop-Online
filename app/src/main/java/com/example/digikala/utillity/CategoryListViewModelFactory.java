package com.example.digikala.utillity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.digikala.viewmodel.CategoryProductListViewModel;

public class CategoryListViewModelFactory implements ViewModelProvider.Factory {
    private int mId;
    @NonNull
    private  Application mApplication;
    public CategoryListViewModelFactory(Application application , int id) {
        mApplication = application;
        mId = id;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CategoryProductListViewModel(mApplication,mId);
    }
}
