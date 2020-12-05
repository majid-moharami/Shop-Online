package com.example.digikala.utillity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.digikala.viewmodel.CategoryProductListViewModel;
import com.example.digikala.viewmodel.SearchResultFragmentViewModel;

public class SearchResultViewModelFactory implements ViewModelProvider.Factory  {

    private String mWord;
    @NonNull
    private Application mApplication;
    public SearchResultViewModelFactory(Application application , String word) {
        mApplication = application;
        mWord = word;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SearchResultFragmentViewModel( mApplication , mWord);
    }
}
