package com.example.digikala.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digikala.R;
import com.example.digikala.data.model.Product;
import com.example.digikala.databinding.ProductItemListBinding;
import com.example.digikala.viewmodel.HomeFragmentViewModel;

import java.util.List;

public class HorizontalRecentProductListAdapter extends RecyclerView.Adapter<HorizontalRecentProductListAdapter.ProductHolder> {

    private List<Product> mProductList;
    private HomeFragmentViewModel mViewModel;
    private LifecycleOwner mOwner ;

    public HorizontalRecentProductListAdapter(LifecycleOwner owner , HomeFragmentViewModel viewModel) {
        mOwner = owner ;
        mViewModel = viewModel;
    }

    public List<Product> getProductList() {
        return mProductList;
    }

    public void setProductList(List<Product> productList) {
        mProductList = productList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemListBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mViewModel.getApplication()),
                R.layout.product_item_list,
                parent,
                false
        );
        return new ProductHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ProductHolder extends RecyclerView.ViewHolder{
        private ProductItemListBinding mItemListBinding;
        public ProductHolder(ProductItemListBinding productItemListBinding) {
            super(productItemListBinding.getRoot());
            mItemListBinding =productItemListBinding;
            mItemListBinding.setViewModel(mViewModel);
            mItemListBinding.setLifecycleOwner(mOwner);
            Typeface typeFace = Typeface.createFromAsset(mViewModel.getApplication().getAssets() , "fonts/Dirooz-FD.ttf");
            mItemListBinding.textViewProductName.setTypeface(typeFace);
            mItemListBinding.textViewProductPrice.setTypeface(typeFace);
        }

        public void onBind(int position){
            mItemListBinding.setPosition(position);
            mItemListBinding.executePendingBindings();
            String imageURL = mViewModel.getRecentProductLiveData().getValue().get(position).getImages().get(0).getImageURL();
            Glide.with(mViewModel.getApplication()).load(imageURL).into(mItemListBinding.imageViewProductImage);
//            String imageURL = product.getImages().get(0).getImageURL();
//            Glide.with(mContext).load(imageURL).into(mItemListBinding.imageViewProductImage);
//            mItemListBinding.textViewProductName.setText(product.getName());
//            String number = product.getPrice();
//            double amount = Double.parseDouble(number);
//            DecimalFormat formatter = new DecimalFormat("#,###");
//            mItemListBinding.textViewProductPrice.setText(formatter.format(amount) + " تومان ");
        }
    }
}
