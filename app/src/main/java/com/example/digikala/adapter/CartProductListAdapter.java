package com.example.digikala.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digikala.R;
import com.example.digikala.data.database.entity.CartProduct;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.databinding.CartProductItemBinding;
import com.example.digikala.utillity.DeleteProductHelper;
import com.example.digikala.viewmodel.CartFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartProductListAdapter extends RecyclerView.Adapter<CartProductListAdapter.CartProductHolder> {

    private List<CartProduct> mCartProductList = new ArrayList<>();
    private LiveData<List<Product>> mProductLiveData;
    private LifecycleOwner mOwner;
    private CartFragmentViewModel mViewModel;
    public CartProductListAdapter(CartFragmentViewModel viewModel , LifecycleOwner owner) {
        mViewModel = viewModel;
        mCartProductList = viewModel.getCartProducts();
        mOwner = owner;
    }

    @NonNull
    @Override
    public CartProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartProductItemBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cart_product_item,
                parent,
                false
        );
        return new CartProductListAdapter.CartProductHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductHolder holder, int position) {
        holder.onBind2(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getCartProducts().size();
    }

    class CartProductHolder extends RecyclerView.ViewHolder {
        private CartProductItemBinding mBinding;

        public CartProductHolder(CartProductItemBinding cartProductItemBinding) {
            super(cartProductItemBinding.getRoot());
            mBinding = cartProductItemBinding;
            mBinding.setViewModel(mViewModel);

            //set font
//            Typeface typeFace = Typeface.createFromAsset(mViewModel.getApplication().getAssets(), "font/Dirooz-FD.ttf");
//            mBinding.textViewProductName.setTypeface(typeFace);
//            mBinding.textView2.setTypeface(typeFace);
//            mBinding.textView3.setTypeface(typeFace);
//            mBinding.textView6.setTypeface(typeFace);
//            mBinding.textView5.setTypeface(typeFace);
//            mBinding.textViewPrice.setTypeface(typeFace);
//            mBinding.textViewProductCount.setTypeface(typeFace);

            mViewModel.getDeleteProductHelperMutableLiveData().observe(mOwner, new Observer<DeleteProductHelper>() {
                @Override
                public void onChanged(DeleteProductHelper deleteProductHelper) {
                    if (deleteProductHelper== DeleteProductHelper.SUBMISSION){
                        mBinding.imageViewSubtrack.setImageResource(R.drawable.ic_subtrack_product);
                    }else mBinding.imageViewSubtrack.setImageResource(R.drawable.ic_delete_product);
                }
            });
        }

        public void onBind2(int position) {
            mBinding.setPosition(position);
            mBinding.executePendingBindings();

            String imageURL = null;
            imageURL =mViewModel.getProducts().getValue().get(position).getImages().get(0).getImageURL();
            Glide.with(mViewModel.getApplication()).load(imageURL).into(mBinding.imageViewProduct);
            //mBinding.textViewPrice.setText(mViewModel.getProduct(position).getPriceToman());
            //mBinding.textViewProductCount.setText(String.valueOf(mViewModel.getCartProduct(position).getCount()));
            //mBinding.textViewProductName.setText(mViewModel.getProduct(position).getName());
        }
    }


}
