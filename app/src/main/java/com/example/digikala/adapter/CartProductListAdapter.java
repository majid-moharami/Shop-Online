package com.example.digikala.adapter;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digikala.data.database.entity.CartProduct;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.network.parameter.RequestParams;
import com.example.digikala.data.network.retrofit.WooCommerceService;
import com.example.digikala.data.repository.CartProductDBRepository;
import com.example.digikala.databinding.CartProductItemBinding;
import com.example.digikala.viewmodel.CartFragmentViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartProductListAdapter extends RecyclerView.Adapter<CartProductListAdapter.CartProductHolder> {

    private CartFragmentViewModel mViewModel;

    public CartProductListAdapter(CartFragmentViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public CartProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CartProductHolder extends RecyclerView.ViewHolder{
        private CartProductItemBinding mBinding;
        public CartProductHolder(CartProductItemBinding cartProductItemBinding) {
            super(cartProductItemBinding.getRoot());
            mBinding = cartProductItemBinding;
            mBinding.setViewModel(mViewModel);

            //set font
            Typeface typeFace = Typeface.createFromAsset(mViewModel.getApplication().getAssets(), "fonts/Dirooz-FD.ttf");
            mBinding.textViewProductName.setTypeface(typeFace);
            mBinding.textView2.setTypeface(typeFace);
            mBinding.textView3.setTypeface(typeFace);
            mBinding.textView6.setTypeface(typeFace);
            mBinding.textView5.setTypeface(typeFace);
            mBinding.textViewPrice.setTypeface(typeFace);
            mBinding.textViewProductCount.setTypeface(typeFace);
        }

        public void onBind(int position) {
            mBinding.setPosition(position);
            mBinding.executePendingBindings();

            CartProduct cartProduct = mViewModel.getCartProductLiveData().getValue().get(position);

            String imageURL = null;
//            imageURL = mViewModel.getCartProductLiveData().getValue().get(position).getImages().get(0).getImageURL();
//            Glide.with(mViewModel.getApplication()).load(imageURL).into(mItemListBinding.imageViewProductImage);
        }
    }


}
