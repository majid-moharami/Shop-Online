package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.database.entity.CartProduct;
import com.example.digikala.data.repository.CartProductDBRepository;

import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {

    private CartProductDBRepository mCartProductDBRepository;
    private LiveData<List<CartProduct>> mCartProductLiveData;
    private MutableLiveData<CartProduct> mCartProductSelectedLiveData;

    public CartFragmentViewModel(@NonNull Application application) {
        super(application);
        mCartProductDBRepository = CartProductDBRepository.getInstance(application);
        mCartProductLiveData = mCartProductDBRepository.getCartProductLiveData();

        //when this method invoke, all CartProduct set in CartProductLiveData in repository.
        mCartProductLiveData=mCartProductDBRepository.getAllCartProduct();
    }

    public LiveData<List<CartProduct>> getCartProductLiveData() {
        return mCartProductLiveData;
    }
}
