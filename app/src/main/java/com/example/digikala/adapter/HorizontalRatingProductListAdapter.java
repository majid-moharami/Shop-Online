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
import com.example.digikala.databinding.ProductRatingListItemBinding;
import com.example.digikala.viewmodel.HomeFragmentViewModel;

import java.util.List;

public class HorizontalRatingProductListAdapter extends RecyclerView.Adapter<HorizontalRatingProductListAdapter.RatingProductHolder> {

    private List<Product> mProductList;
    private HomeFragmentViewModel mViewModel;
    private LifecycleOwner mOwner;

    public HorizontalRatingProductListAdapter(LifecycleOwner owner, HomeFragmentViewModel viewModel) {
        mOwner = owner;
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
    public HorizontalRatingProductListAdapter.RatingProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductRatingListItemBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mViewModel.getApplication()),
                R.layout.product_rating_list_item,
                parent,
                false
        );
        return new HorizontalRatingProductListAdapter.RatingProductHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingProductHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class RatingProductHolder extends RecyclerView.ViewHolder {
        private ProductRatingListItemBinding mItemListBinding;

        public RatingProductHolder(ProductRatingListItemBinding productRatingListItemBinding) {
            super(productRatingListItemBinding.getRoot());
            mItemListBinding = productRatingListItemBinding;
            mItemListBinding.setViewModel(mViewModel);
            mItemListBinding.setLifecycleOwner(mOwner);
            Typeface typeFace = Typeface.createFromAsset(mViewModel.getApplication().getAssets(), "fonts/Dirooz-FD.ttf");
            mItemListBinding.textViewProductName.setTypeface(typeFace);
            mItemListBinding.textViewProductPrice.setTypeface(typeFace);
        }

        public void onBind(int position) {
            mItemListBinding.setPosition(position);
            mItemListBinding.executePendingBindings();
            String imageURL = mViewModel.getRatingProductLiveData().getValue().get(position).getImages().get(0).getImageURL();
            Glide.with(mViewModel.getApplication()).load(imageURL).into(mItemListBinding.imageViewProductImage);
//            mItemListBinding.textViewProductName.setText(product.getName());
//            String number = product.getPrice();
//            double amount = Double.parseDouble(number);
//            DecimalFormat formatter = new DecimalFormat("#,###");
//            mItemListBinding.textViewProductPrice.setText(formatter.format(amount) + " تومان ");
        }
    }
}
