package com.example.digikala.ui.fragment;

import android.content.Intent;
import android.graphics.Typeface;
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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digikala.R;
import com.example.digikala.adapter.ProductListAdapter;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.databinding.FragmentHomeBinding;
import com.example.digikala.ui.activity.ProductDetailActivity;
import com.example.digikala.ui.activity.ProductListActivity;
import com.example.digikala.utillity.ListType;
import com.example.digikala.utillity.ProductDiffUtil;
import com.example.digikala.viewmodel.PopularProductViewModel;
import com.example.digikala.viewmodel.ProductStrategyViewModel;
import com.example.digikala.viewmodel.RatingProductViewModel;
import com.example.digikala.viewmodel.RecentProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mHomeBinding;
    private ProductStrategyViewModel mRecentViewModel;
    private ProductStrategyViewModel mPopularViewModel;
    private ProductStrategyViewModel mRatingViewModel;

    private ProductListAdapter mRecentProductAdapter;
    private ProductListAdapter mPopularProductAdapter;
    private ProductListAdapter mRatingProductAdapter;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HomeFragment" ,"onCreate" );
        mRecentViewModel = new ViewModelProvider(this).get(RecentProductViewModel.class);
        mPopularViewModel = new ViewModelProvider(this).get(PopularProductViewModel.class);
        mRatingViewModel = new ViewModelProvider(this).get(RatingProductViewModel.class);
        initAdapter();
        observers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mHomeBinding.setRecentViewModel((RecentProductViewModel) mRecentViewModel);
        mHomeBinding.setPopularViewModel((PopularProductViewModel) mPopularViewModel);
        mHomeBinding.setRatingViewModel((RatingProductViewModel) mRatingViewModel);
        setFont();
        //
        setRecyclerLayouts();
        //getActivity().setActionBar(mHomeBinding.toolbar);
        
        return mHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeBinding.newsProductRecycler.setAdapter(mRecentProductAdapter);
        mHomeBinding.mostReviewRecycler.setAdapter(mPopularProductAdapter);
        mHomeBinding.mostRateRecycler.setAdapter(mRatingProductAdapter);

    }

    private void initAdapter(){
        mRecentProductAdapter = new ProductListAdapter(this, mRecentViewModel, ListType.RECENT_PRODUCT);
        mPopularProductAdapter = new ProductListAdapter(this, mPopularViewModel, ListType.POPULAR_PRODUCT);
        mRatingProductAdapter = new ProductListAdapter(this, mRatingViewModel, ListType.RATING_PRODUCT);
    }
    private void setFont() {
        Typeface typeFaceTitle = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Far_Casablanca.ttf");
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Far_Zar.ttf");
        mHomeBinding.textViewNewsTitle.setTypeface(typeFaceTitle);
        mHomeBinding.textViewPopularTitle.setTypeface(typeFaceTitle);
        mHomeBinding.textViewRatingTitle.setTypeface(typeFaceTitle);
        mHomeBinding.newsSeeMoreText.setTypeface(typeFace);
        mHomeBinding.mostViewSeeMoreText.setTypeface(typeFace);
        mHomeBinding.mostRateSeeMoreText.setTypeface(typeFace);
    }

    private void setRecyclerLayouts() {
        mHomeBinding.newsProductRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        RecyclerView.HORIZONTAL,
                        true));

        mHomeBinding.mostReviewRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        RecyclerView.HORIZONTAL,
                        true));

        mHomeBinding.mostRateRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        RecyclerView.HORIZONTAL,
                        true));
    }


    private void observers() {
        mRecentViewModel.getProductLiveData().observe(this, products -> {
                mRecentProductAdapter.notifyDataSetChanged();
        });

        mPopularViewModel.getProductLiveData().observe(this,products -> {
                mPopularProductAdapter.notifyDataSetChanged();
        });

        mRatingViewModel.getProductLiveData().observe(this,  products -> {
            mRatingProductAdapter.notifyDataSetChanged();
        });

        mRecentViewModel.getProductSelectedLiveData().observe(this,  product -> {
                goToDetailFragment(Integer.parseInt(product.getId()));
        });

        mPopularViewModel.getProductSelectedLiveData().observe(this, product -> {
                goToDetailFragment(Integer.parseInt(product.getId()));
        });
        mRatingViewModel.getProductSelectedLiveData().observe(this,  product ->{
                goToDetailFragment(Integer.parseInt(product.getId()));
        });
    }


    private void goToDetailFragment(int productId){
        HomeFragmentDirections.ActionHomeFragmentToProductDetailFragment action =
                HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(productId);
        Navigation.findNavController(mHomeBinding.getRoot()).navigate(action);
    }
}