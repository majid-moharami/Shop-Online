package com.example.digikala.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digikala.R;
import com.example.digikala.data.model.category.Category;
import com.example.digikala.databinding.SubCategoryItemListBinding;
import com.example.digikala.utillity.HeadCategory;
import com.example.digikala.viewmodel.CategoryFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryListAdapter extends RecyclerView.Adapter<SubCategoryListAdapter.CategoryHolder> {
    private List<Category> mCategoryList = new ArrayList<>();
    private HeadCategory mCategoryType;
    private CategoryFragmentViewModel mViewModel;

    public SubCategoryListAdapter(HeadCategory categoryType, CategoryFragmentViewModel viewModel) {
        mCategoryType = categoryType;
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SubCategoryItemListBinding listBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mViewModel.getApplication()),
                R.layout.sub_category_item_list,
                parent,
                false);
        return new CategoryHolder(listBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        switch (mCategoryType){
            case DIGITAL:
               size = mViewModel.getSubDigitalLiveData().getValue().size();
               break;
            case HEALTH:
                size = mViewModel.getSubHealthLiveData().getValue().size();
                break;
            case SPECIAL_SALE:
                size = mViewModel.getSubSpecialSaleLiveData().getValue().size();
                break;
            case BOOK_ART:
                size = mViewModel.getSubArtLiveData().getValue().size();
                break;
            case SUPER_MARKET:
                size = mViewModel.getSubMarketLiveData().getValue().size();
                break;
            case FASHION_CLOTHING:
                size = mViewModel.getSubClothingLiveData().getValue().size();
                break;
        }
        return size;
    }

    class CategoryHolder extends RecyclerView.ViewHolder{
        SubCategoryItemListBinding mItemListBinding;
        public CategoryHolder(SubCategoryItemListBinding itemListBinding) {
            super(itemListBinding.getRoot());
            mItemListBinding = itemListBinding;
            mItemListBinding.setViewModel(mViewModel);
            mItemListBinding.setCategoryType(mCategoryType);

            Typeface typeFace = Typeface.createFromAsset(mViewModel.getApplication().getAssets() , "fonts/Dirooz-FD.ttf");
            mItemListBinding.textViewCategoryName.setTypeface(typeFace);
        }

        public void onBind(int position){
           mItemListBinding.setPosition(position);
           mItemListBinding.executePendingBindings();
           String imageURL = null;
           switch (mCategoryType){
               case DIGITAL:
                  imageURL = mViewModel.getSubDigitalLiveData().getValue().get(position).getCategoryImage().getSrc();
                  break;
               case HEALTH:
                   imageURL = mViewModel.getSubHealthLiveData().getValue().get(position).getCategoryImage().getSrc();
                   break;
               case SPECIAL_SALE:
                   imageURL = mViewModel.getSubSpecialSaleLiveData().getValue().get(position).getCategoryImage().getSrc();
                   break;
               case SUPER_MARKET:
                   imageURL = mViewModel.getSubMarketLiveData().getValue().get(position).getCategoryImage().getSrc();
                   break;
               case BOOK_ART:
                   imageURL = mViewModel.getSubArtLiveData().getValue().get(position).getCategoryImage().getSrc();
                   break;
               case FASHION_CLOTHING:
                   imageURL = mViewModel.getSubClothingLiveData().getValue().get(position).getCategoryImage().getSrc();
           }
           Glide.with(mViewModel.getApplication()).load(imageURL).into(mItemListBinding.imageViewCategoryImage);
        }
    }
}
