package com.example.digikala.adapter;

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
import com.example.digikala.databinding.ProductPopularListItemBinding;
import com.example.digikala.viewmodel.HomeFragmentViewModel;

import java.util.List;

public class HorizontalPopularProductListAdapter  extends RecyclerView.Adapter<HorizontalPopularProductListAdapter.PopularProductHolder> {

    private List<Product> mProductList;
    private HomeFragmentViewModel mViewModel;
    private LifecycleOwner mOwner ;

    public HorizontalPopularProductListAdapter(LifecycleOwner owner , HomeFragmentViewModel viewModel) {
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
    public HorizontalPopularProductListAdapter.PopularProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductPopularListItemBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mViewModel.getApplication()),
                R.layout.product_popular_list_item,
                parent,
                false
        );
        return new HorizontalPopularProductListAdapter.PopularProductHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public int getItemCount() {
        return 5;
    }

    class PopularProductHolder extends RecyclerView.ViewHolder{
        private ProductPopularListItemBinding mItemListBinding;
        public PopularProductHolder(ProductPopularListItemBinding productPopularListItemBinding) {
            super(productPopularListItemBinding.getRoot());
            mItemListBinding =productPopularListItemBinding;
            mItemListBinding.setViewModel(mViewModel);
            mItemListBinding.setLifecycleOwner(mOwner);
            Typeface typeFace = Typeface.createFromAsset(mViewModel.getApplication().getAssets() , "fonts/Dirooz-FD.ttf");
            mItemListBinding.textViewProductName.setTypeface(typeFace);
            mItemListBinding.textViewProductPrice.setTypeface(typeFace);
        }

        public void onBind(int position){
            mItemListBinding.setPosition(position);
            mItemListBinding.executePendingBindings();
            String imageURL = mViewModel.getPopularProductLiveData().getValue().get(position).getImages().get(0).getImageURL();
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
