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
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.databinding.ProductItemListBinding;
import com.example.digikala.utillity.ListType;
import com.example.digikala.viewmodel.HomeFragmentViewModel;

import java.util.List;

public class HorizontalRecentProductListAdapter extends RecyclerView.Adapter<HorizontalRecentProductListAdapter.ProductHolder> {

    private List<Product> mProductList;
    private HomeFragmentViewModel mViewModel;
    private LifecycleOwner mOwner ;
    private ListType mListType;

    public HorizontalRecentProductListAdapter(
            LifecycleOwner owner ,
            HomeFragmentViewModel viewModel ,
            ListType listType) {

        mOwner = owner ;
        mViewModel = viewModel;
        mListType = listType;
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
           // mItemListBinding.setLifecycleOwner(mOwner);
            mItemListBinding.setListType(mListType);
            Typeface typeFace = Typeface.createFromAsset(mViewModel.getApplication().getAssets() , "fonts/Dirooz-FD.ttf");
            mItemListBinding.textViewProductName.setTypeface(typeFace);
            mItemListBinding.textViewProductPrice.setTypeface(typeFace);
        }

        public void onBind(int position){
            mItemListBinding.setPosition(position);
            mItemListBinding.executePendingBindings();
            String imageURL = null;
            switch (mListType){
                case RATING_PRODUCT:
                    imageURL = mViewModel.getRatingProductLiveData().getValue().get(position).getImages().get(0).getImageURL();
                    break;
                case RECENT_PRODUCT:
                    imageURL = mViewModel.getRecentProductLiveData().getValue().get(position).getImages().get(0).getImageURL();
                    break;
                case POPULAR_PRODUCT:
                    imageURL = mViewModel.getPopularProductLiveData().getValue().get(position).getImages().get(0).getImageURL();
                    break;
            }
            Glide.with(mViewModel.getApplication()).load(imageURL).into(mItemListBinding.imageViewProductImage);
        }
    }
}
