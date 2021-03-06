package com.example.digikala.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digikala.R;
import com.example.digikala.adapter.SubCategoryListAdapter;
import com.example.digikala.data.model.category.Category;
import com.example.digikala.databinding.FragmentCategoryBinding;
import com.example.digikala.utillity.HeadCategory;
import com.example.digikala.utillity.ListType;
import com.example.digikala.utillity.State;
import com.example.digikala.viewmodel.CategoryFragmentViewModel;

import java.util.List;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding mBinding;
    private CategoryFragmentViewModel mViewModel;
    private boolean isFirst = true;
    private SubCategoryListAdapter mDigitalAdapter;
    private SubCategoryListAdapter mHealthAdapter;
    private SubCategoryListAdapter mArtAdapter;
    private SubCategoryListAdapter mMarketAdapter;
    private SubCategoryListAdapter mClothingAdapter;
    private SubCategoryListAdapter mSpecialSaleAdapter;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryFragmentViewModel.class);
        mViewModel.checkNetWork();
        observers();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_category , container, false);
        setRecyclerLayout(mBinding.subDigitalRecycler);
        setRecyclerLayout(mBinding.subHealthRecycler);
        setRecyclerLayout(mBinding.subSpecialSaleRecycler);
        setRecyclerLayout(mBinding.subBookArtRecycler);
        setRecyclerLayout(mBinding.subSuperMarketRecycler);
        setRecyclerLayout(mBinding.subClothingRecycler);
        mBinding.textViewNoNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.fetchAllSubCategory();
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isFirst){
            mBinding.categoryRoot.setVisibility(View.VISIBLE);
            mBinding.progressBarLoadingFragment.setVisibility(View.GONE);
            setAdapter(mDigitalAdapter , HeadCategory.DIGITAL , mBinding.subDigitalRecycler);
            setAdapter(mClothingAdapter , HeadCategory.FASHION_CLOTHING , mBinding.subClothingRecycler);
            setAdapter(mArtAdapter , HeadCategory.BOOK_ART , mBinding.subBookArtRecycler);
            setAdapter(mHealthAdapter , HeadCategory.HEALTH , mBinding.subHealthRecycler);
            setAdapter(mMarketAdapter , HeadCategory.SUPER_MARKET , mBinding.subSuperMarketRecycler);
            setAdapter(mSpecialSaleAdapter , HeadCategory.SPECIAL_SALE , mBinding.subSpecialSaleRecycler);
        }
    }

    public void setRecyclerLayout(RecyclerView recyclerView){
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        RecyclerView.HORIZONTAL,
                        true));
    }

    private void setAdapter(SubCategoryListAdapter adapter , HeadCategory headCategory,RecyclerView recyclerView){
        adapter = new SubCategoryListAdapter(headCategory , mViewModel);
        recyclerView.setAdapter(adapter);
    }

    private void observers() {
        mViewModel.getSubDigitalLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
               setAdapter(mDigitalAdapter , HeadCategory.DIGITAL , mBinding.subDigitalRecycler);
                if (mViewModel.getSubDigitalLiveData().getValue().size()==0){
                    mBinding.textViewEmptyDigital.setVisibility(View.VISIBLE);
                }

            }
        });

        mViewModel.getSubClothingLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
               setAdapter(mClothingAdapter , HeadCategory.FASHION_CLOTHING , mBinding.subClothingRecycler);
                if (mViewModel.getSubClothingLiveData().getValue().size()==0){
                    mBinding.textViewEmptyClothing.setVisibility(View.VISIBLE);
                }
            }
        });

        mViewModel.getSubArtLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                setAdapter(mArtAdapter , HeadCategory.BOOK_ART , mBinding.subBookArtRecycler);
                if (mViewModel.getSubArtLiveData().getValue().size()==0){
                    mBinding.textViewEmptyArt.setVisibility(View.VISIBLE);
                }
            }
        });

        mViewModel.getSubHealthLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                setAdapter(mHealthAdapter , HeadCategory.HEALTH , mBinding.subHealthRecycler);
                if (mViewModel.getSubHealthLiveData().getValue().size()==0){
                    mBinding.textViewEmptyHealth.setVisibility(View.VISIBLE);
                }
            }
        });

        mViewModel.getSubMarketLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                setAdapter(mMarketAdapter , HeadCategory.SUPER_MARKET , mBinding.subSuperMarketRecycler);
                if (mViewModel.getSubMarketLiveData().getValue().size()==0){
                    mBinding.textViewEmptySuperMarket.setVisibility(View.VISIBLE);
                }
            }
        });

        mViewModel.getSubSpecialSaleLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                setAdapter(mSpecialSaleAdapter , HeadCategory.SPECIAL_SALE , mBinding.subSpecialSaleRecycler);
                if (mViewModel.getSubSpecialSaleLiveData().getValue().size()==0){
                    mBinding.textViewEmptySpecialSale.setVisibility(View.VISIBLE);
                }
            }
        });

        mViewModel.getCategorySubject().observe(this, new Observer<Category>() {
            @Override
            public void onChanged(Category category) {
                Log.d("IDNumber" , category.getId()+"");
                goToListFragment(ListType.NONE , category.getId());
            }
        });
        mViewModel.getFragmentState().observe(this , state -> {
            if (state == State.NAVIGATE){
                mBinding.categoryRoot.setVisibility(View.VISIBLE);
                mBinding.progressBarLoadingFragment.setVisibility(View.GONE);
                mBinding.textViewNoNet.setVisibility(View.GONE);
                isFirst = false;
                mViewModel.setStateFragment(State.LOADING);
            }
        });
        mViewModel.getIsNetworkLiveData().observe(this , aBoolean -> {
            if (!aBoolean){
                mBinding.categoryRoot.setVisibility(View.GONE);
                mBinding.progressBarLoadingFragment.setVisibility(View.VISIBLE);
                mBinding.textViewNoNet.setVisibility(View.VISIBLE);
            }else {
                mBinding.textViewNoNet.setVisibility(View.GONE);
            }
        });
    }

    private void goToListFragment(ListType listType ,int categoryId){
        CategoryFragmentDirections.ActionCategoryFragmentToProductListFragment action =
                CategoryFragmentDirections.actionCategoryFragmentToProductListFragment(listType , categoryId);
        Navigation.findNavController(mBinding.getRoot()).navigate(action);
    }

}