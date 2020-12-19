package com.example.digikala.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digikala.R;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.databinding.SearchListItemBinding;
import com.example.digikala.viewmodel.ProductStrategyViewModel;

import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ProductHolder> {

    private ProductStrategyViewModel mViewModel;
    private int mPage = 2;
    private LifecycleOwner mOwner;
    private String mWord;

    public SearchListAdapter(ProductStrategyViewModel viewModel , LifecycleOwner owner , String word) {
        mViewModel = viewModel;
        mOwner = owner;
        mWord = word;
    }
    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchListItemBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mViewModel.getApplication()),
                R.layout.search_list_item,
                parent,
                false
        );
        return new SearchListAdapter.ProductHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.onBind(position);

        if (position == mViewModel.getProductLiveData().getValue().size() - 1) {
            mViewModel.updateSearchList(mPage ,mWord);
            mPage++;
        }
    }

    @Override
    public int getItemCount() {
        final int[] size = {0};
        mViewModel.getProductLiveData().observe(mOwner, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                size[0] = products.size();
            }
        });
        return size[0];
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        private SearchListItemBinding mItemListBinding;

        public ProductHolder(SearchListItemBinding productItemListBinding) {
            super(productItemListBinding.getRoot());
            mItemListBinding = productItemListBinding;
            mItemListBinding.setViewModel(mViewModel);
//            Typeface typeFace = Typeface.createFromAsset(mViewModel.getApplication().getAssets(), "font/Dirooz-FD.ttf");
//            mItemListBinding.textViewProductName.setTypeface(typeFace);
//            mItemListBinding.textViewProductPrice.setTypeface(typeFace);
        }

        public void onBind(int position) {
            mItemListBinding.setPosition(position);
            mItemListBinding.executePendingBindings();
            String imageURL = null;

            imageURL = mViewModel.getProductLiveData().getValue().get(position).getImages().get(0).getImageURL();

            Glide.with(mViewModel.getApplication()).load(imageURL).into(mItemListBinding.imageViewProductImage);
        }
    }
}
