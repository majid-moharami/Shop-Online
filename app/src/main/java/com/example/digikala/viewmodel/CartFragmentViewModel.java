package com.example.digikala.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.database.entity.CartProduct;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.network.parameter.RequestParams;
import com.example.digikala.data.network.retrofit.RetrofitInstance;
import com.example.digikala.data.network.retrofit.WooCommerceService;
import com.example.digikala.data.repository.CartProductDBRepository;
import com.example.digikala.utillity.State;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartFragmentViewModel extends AndroidViewModel {

    private CartProductDBRepository mCartProductDBRepository;

    public CartFragmentViewModel(@NonNull Application application) {
        super(application);

        mCartProductDBRepository = CartProductDBRepository.getInstance(application);
        Log.d("LIST", "ViewModelConstructor");
    }

    public void fetchAllProducts(){
        mCartProductDBRepository.fetchAllProduct();
    }
    public Product getProduct(int position) {
        Log.d("LIST", "getProduct");
        return mCartProductDBRepository.getProductLiveData().getValue().get(position);
    }

    public CartProduct getCartProduct(int position) {
        return mCartProductDBRepository.getCartProductList().get(position);
    }


    public List<CartProduct> getCartProducts() {
        return mCartProductDBRepository.getCartProductList();
    }

    public LiveData<List<Product>> getProducts() {
        return mCartProductDBRepository.getProductLiveData();
    }

    public LiveData<State> getRequestState(){
        return mCartProductDBRepository.getStateMutableLiveData();
    }
}
