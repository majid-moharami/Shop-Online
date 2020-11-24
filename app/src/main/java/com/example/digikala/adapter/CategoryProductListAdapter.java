package com.example.digikala.adapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.example.digikala.utillity.ListType;
import com.example.digikala.viewmodel.ProductStrategyViewModel;

public class CategoryProductListAdapter extends ProductListAdapter {
    private int mCategoryId;
    private int mPage = 2;
    private ProductStrategyViewModel mViewModel;

    public CategoryProductListAdapter(LifecycleOwner owner, ProductStrategyViewModel viewModel, ListType listType , int categoryId ) {
        super(owner, viewModel, listType);
        mCategoryId = categoryId;
        mViewModel = viewModel;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.onBind(position);
        if (position == mViewModel.getProductLiveData().getValue().size() - 1) {
            mViewModel.updateCategoryProductsList(mPage);
            mPage++;
        }

    }
}
