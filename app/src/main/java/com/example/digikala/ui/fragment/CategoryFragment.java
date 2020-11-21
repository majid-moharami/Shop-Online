package com.example.digikala.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digikala.R;
import com.example.digikala.adapter.SubCategoryListAdapter;
import com.example.digikala.data.model.category.Category;
import com.example.digikala.databinding.FragmentCategoryBinding;
import com.example.digikala.utillity.HeadCategory;
import com.example.digikala.viewmodel.CategoryFragmentViewModel;
import com.example.digikala.viewmodel.HomeFragmentViewModel;

import java.util.List;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding mBinding;
    private CategoryFragmentViewModel mViewModel;

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
        return mBinding.getRoot();
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

            }
        });
    }


}