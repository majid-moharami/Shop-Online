package com.example.digikala.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digikala.R;
import com.example.digikala.adapter.HorizontalPopularProductListAdapter;
import com.example.digikala.adapter.HorizontalRatingProductListAdapter;
import com.example.digikala.adapter.HorizontalRecentProductListAdapter;
import com.example.digikala.data.model.Product;
import com.example.digikala.databinding.FragmentHomeBinding;
import com.example.digikala.viewmodel.HomeFragmentViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mHomeBinding;
    private HomeFragmentViewModel mViewModel;
    private HorizontalRecentProductListAdapter mRecentProductAdapter;
    private HorizontalPopularProductListAdapter mPopularProductAdapter;
    private HorizontalRatingProductListAdapter mRatingProductAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeFragmentViewModel.class);
        observers();
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setFont();
        setRecyclerLayouts();
        getActivity().setActionBar(mHomeBinding.toolbar);
        return mHomeBinding.getRoot();
    }

    private void setFont() {
        Typeface typeFaceTitle = Typeface.createFromAsset(getActivity().getAssets() , "fonts/Far_Casablanca.ttf");
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets() , "fonts/Far_Zar.ttf");
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

    private void setRecentProductAdapter(List<Product> products) {
        mRecentProductAdapter = new HorizontalRecentProductListAdapter(this, mViewModel);
        mHomeBinding.newsProductRecycler.setAdapter(mRecentProductAdapter);
    }

    private void setPopularProductAdapter(List<Product> products) {
        mPopularProductAdapter = new HorizontalPopularProductListAdapter(this, mViewModel);
        mHomeBinding.mostReviewRecycler.setAdapter(mPopularProductAdapter);
    }

    private void setRatingProductAdapter(List<Product> products) {
        mRatingProductAdapter = new HorizontalRatingProductListAdapter(this, mViewModel);
        mHomeBinding.mostRateRecycler.setAdapter(mRatingProductAdapter);
    }

    private void observers() {
        mViewModel.getRecentProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setRecentProductAdapter(products);
            }
        });

        mViewModel.getPopularProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setPopularProductAdapter(products);
            }
        });

        mViewModel.getRatingProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setRatingProductAdapter(products);
            }
        });

        mViewModel.getProductSelectedLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container , ProductDetailFragment.newInstance())
                        .commit();
            }
        });
    }
}