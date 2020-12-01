package com.example.digikala.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digikala.R;
import com.example.digikala.adapter.CategoryProductListAdapter;
import com.example.digikala.adapter.ProductListAdapter;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.databinding.FragmentProductListBinding;
import com.example.digikala.ui.activity.ProductDetailActivity;
import com.example.digikala.utillity.CategoryListViewModelFactory;
import com.example.digikala.utillity.ListType;

import com.example.digikala.viewmodel.CategoryProductListViewModel;
import com.example.digikala.viewmodel.PopularProductViewModel;
import com.example.digikala.viewmodel.ProductStrategyViewModel;
import com.example.digikala.viewmodel.RatingProductViewModel;
import com.example.digikala.viewmodel.RecentProductViewModel;

import java.util.List;

public class ProductListFragment extends Fragment {
    public static final String BUNDLE_KYE_LIST_TYPE = "com.example.digikala.ui_ListType";
    public static final String BUNDLE_KYE_CATEGORY_ID = "com.example.digikala.ui_CategoryId";

    private FragmentProductListBinding mBinding;
    private ListType mListType;
    private ProductStrategyViewModel mViewModel;

    private ProductListAdapter mAdapterHomeLists;
    private CategoryProductListAdapter mAdapterCategoryLists;
    int categoryId;


    public static ProductListFragment newInstance( ListType hemeListType , int categoryId) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KYE_CATEGORY_ID, categoryId);
        args.putSerializable(BUNDLE_KYE_LIST_TYPE, hemeListType);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MAJID" , "INVOKE ONCREATE");
        //if user come from home fragment
        mListType = ProductListFragmentArgs.fromBundle(getArguments()).getListType();
        if (mListType != null && mListType != ListType.NONE){
            switch (mListType){
                case RECENT_PRODUCT:
                    mViewModel = new ViewModelProvider(this).get(RecentProductViewModel.class);
                    break;
                case POPULAR_PRODUCT:
                    mViewModel = new ViewModelProvider(this).get(PopularProductViewModel.class);
                    break;
                case RATING_PRODUCT:
                    mViewModel = new ViewModelProvider(this).get(RatingProductViewModel.class);
            }
            mAdapterHomeLists = new ProductListAdapter(
                    ProductListFragment.this ,
                    mViewModel,
                    mListType);
            observersHomeLists();
        }else {
            categoryId = ProductListFragmentArgs.fromBundle(getArguments()).getCategoryId();
            mViewModel = new
                    ViewModelProvider(this ,
                    new CategoryListViewModelFactory((Application) getContext().getApplicationContext(),categoryId))
                    .get(CategoryProductListViewModel.class);
            mAdapterCategoryLists = new CategoryProductListAdapter(ProductListFragment.this  , mViewModel, mListType , categoryId);
            observerCategoryLists();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("MAJID" , "INVOKE onCreateView");
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_product_list , container , false);
        setRecyclerLayoutManager();
        //setTitle();
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("MAJID" , "INVOKE onViewCreated");

        /**
         * when user select a product and go to DetailFragment and
         * when back to this fragment we should set the adapter to recycler view.
         * if we dont this set , fragment show empty list.
         */
        if (mListType != null && mListType != ListType.NONE)
            mBinding.productsRecycler.setAdapter(mAdapterHomeLists);
        else {
            mBinding.productsRecycler.setAdapter(mAdapterCategoryLists);
        }

    }

    private void setTitle() {
        switch (mListType){
            case RECENT_PRODUCT:
                mBinding.listTitle.setText(ListType.getRecent());
                break;
            case POPULAR_PRODUCT:
                mBinding.listTitle.setText(ListType.getPopular());
                break;
            case RATING_PRODUCT:
                mBinding.listTitle.setText(ListType.getRating());
        }
    }


    private void setRecyclerLayoutManager() {
        mBinding.productsRecycler.setLayoutManager(new GridLayoutManager(getContext() , 2));
    }


    private void observersHomeLists(){
        mViewModel.getProductLiveData().observe(this , products -> {
            mAdapterHomeLists.notifyDataSetChanged();
        });
        mViewModel.getProductSelectedLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                startActivity(ProductDetailActivity.newIntent(getContext(), Integer.parseInt(product.getId())));
            }
        });
    }

    private void observerCategoryLists(){
        mViewModel.getProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mAdapterCategoryLists.notifyDataSetChanged();
            }
        });
        mViewModel.getProductSelectedLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                ProductListFragmentDirections.ActionProductListFragmentToProductDetailFragment action =
                        ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(Integer.parseInt(product.getId()));
                Navigation.findNavController(mBinding.getRoot()).navigate(action);
            }
        });
    }
}