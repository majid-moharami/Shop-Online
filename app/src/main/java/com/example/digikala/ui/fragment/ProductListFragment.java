package com.example.digikala.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digikala.R;
import com.example.digikala.adapter.ProductListAdapter;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.databinding.FragmentProductListBinding;
import com.example.digikala.ui.activity.ProductDetailActivity;
import com.example.digikala.utillity.ListType;
import com.example.digikala.viewmodel.HomeFragmentViewModel;
import com.example.digikala.viewmodel.PopularProductViewModel;
import com.example.digikala.viewmodel.ProductStrategyViewModel;
import com.example.digikala.viewmodel.RatingProductViewModel;
import com.example.digikala.viewmodel.RecentProductViewModel;

import java.util.List;

public class ProductListFragment extends Fragment {
    public static final String BUNDLE_KYE_LIST_TYPE = "com.example.digikala.ui_ListType";
    public static final String BUNDLE_KYE_CATEGORY_ID = "com.example.digikala.ui_CategoryId";

    private FragmentProductListBinding mBinding;
    private HomeFragmentViewModel mViewModel;
    private ListType mListType;
    private ProductStrategyViewModel mHomeViewModel;

    private ProductListAdapter mAdapter;



    public static ProductListFragment newInstance( ListType hemeListType) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        //args.putInt(BUNDLE_KYE_CATEGORY_ID, categoryId);
        args.putSerializable(BUNDLE_KYE_LIST_TYPE, hemeListType);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        mListType = (ListType) getArguments().getSerializable(BUNDLE_KYE_LIST_TYPE);
        switch (mListType){
            case RECENT_PRODUCT:
                mHomeViewModel= new ViewModelProvider(this).get(RecentProductViewModel.class);
                break;
            case POPULAR_PRODUCT:
                mHomeViewModel = new ViewModelProvider(this).get(PopularProductViewModel.class);
                break;
            default:
                mHomeViewModel = new ViewModelProvider(this).get(RatingProductViewModel.class);
        }
        observers();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_product_list , container , false);
        setRecyclerLayoutManager();
        setTitle();
        return mBinding.getRoot();

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

    public void setHomeListsObserver(LiveData<List<Product>> liveData){
        mViewModel.getRecentProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if (mAdapter == null){
                    mAdapter  = createAdapter();
                    mBinding.productsRecycler.setAdapter(mAdapter);
                }else mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void observers(){
        mHomeViewModel.getProductLiveData().observe(this , products -> {
            if (mAdapter == null){
                        mAdapter  = createAdapter();
                        mBinding.productsRecycler.setAdapter(mAdapter);
                    }else mAdapter.notifyDataSetChanged();
        });
        mHomeViewModel.getProductSelectedLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                startActivity(ProductDetailActivity.newIntent(getContext(), Integer.parseInt(product.getId())));
            }
        });
    }

    public ProductListAdapter createAdapter(){
      return new ProductListAdapter(
                ProductListFragment.this ,
                mHomeViewModel ,
                mListType);
    }
}