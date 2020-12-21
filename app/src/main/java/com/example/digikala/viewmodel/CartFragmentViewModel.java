package com.example.digikala.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.digikala.data.database.entity.CartProduct;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.repository.CartProductDBRepository;
import com.example.digikala.utillity.DeleteProductHelper;
import com.example.digikala.utillity.State;

import java.text.DecimalFormat;
import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {

    private CartProductDBRepository mCartProductDBRepository;
    private LiveData<List<Product>> mListLiveData;
    private MutableLiveData<DeleteProductHelper> mDeleteProductHelperMutableLiveData = new MutableLiveData<>();

    public CartFragmentViewModel(@NonNull Application application) {
        super(application);

        mCartProductDBRepository = CartProductDBRepository.getInstance(application);
        Log.d("LIST", "ViewModelConstructor");
    }

    public void fetchAllProducts() {
        mCartProductDBRepository.fetchAllProduct();
    }

    public void deleteCartProduct(int position) {
        CartProduct cartProduct = getCartProducts().get(position);
        if (cartProduct.getCount() == 1) {
            mCartProductDBRepository.delete(cartProduct);
            return;
        }
        if (cartProduct.getCount() > 1) {
            cartProduct.setCount(cartProduct.getCount() - 1);
            mCartProductDBRepository.update(cartProduct);
        }
    }

    public String calculateAllPrice(List<Product> products) {
        List<CartProduct> cartProducts = mCartProductDBRepository.getAllCartProduct();
        int allPrice = 0;
        if (cartProducts.size() == products.size()) {
            for (int i = 0; i < cartProducts.size(); i++) {
                int productPrice = Integer.parseInt(products.get(i).basePrice());
                allPrice = (productPrice * cartProducts.get(i).getCount()) + allPrice;
            }
        }
        String price = String.valueOf(allPrice);
        double amount = Double.parseDouble(price);
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public void onUpdateDatabase(int position) {
        CartProduct cartProduct = getCartProducts().get(position);
        cartProduct.setCount(cartProduct.getCount() + 1);
        mCartProductDBRepository.update(cartProduct);
    }

    public Product getProduct(int position) {
        Log.d("LIST", "getProduct");
        return mCartProductDBRepository.getProductLiveData().getValue().get(position);
    }

    public CartProduct getCartProduct(int position) {
        return mCartProductDBRepository.getAllCartProduct().get(position);
    }


    public List<CartProduct> getCartProducts() {
        return mCartProductDBRepository.getAllCartProduct();
    }

    public LiveData<List<Product>> getProducts() {
        return mCartProductDBRepository.getProductLiveData();
    }

    public LiveData<State> getRequestState() {
        return mCartProductDBRepository.getStateMutableLiveData();
    }

    public void setState(State state) {
        mCartProductDBRepository.getStateMutableLiveData().setValue(state);
    }

    public MutableLiveData<DeleteProductHelper> getDeleteProductHelperMutableLiveData() {
        return mDeleteProductHelperMutableLiveData;
    }

}
