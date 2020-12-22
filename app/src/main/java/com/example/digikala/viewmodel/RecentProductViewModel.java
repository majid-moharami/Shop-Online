package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.repository.ProductRepository;
import com.example.digikala.utillity.State;

import java.util.List;

public class RecentProductViewModel extends ProductStrategyViewModel {

    public RecentProductViewModel(@NonNull Application application) {
        super(application, ProductRepository.getInstance().getRecentProductLiveData());
    }
}
