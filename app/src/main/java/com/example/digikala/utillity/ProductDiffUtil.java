package com.example.digikala.utillity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.digikala.data.model.poduct.Product;

import java.util.List;

public class ProductDiffUtil extends DiffUtil.Callback {
    private List<Product> mOldList;
    private List<Product> mNewList;

    public ProductDiffUtil(List<Product> oldList, List<Product> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId().equals(mNewList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getName().equals(mNewList.get(newItemPosition).getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
