package com.example.digikala.ui.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digikala.R;
import com.example.digikala.data.model.Product;
import com.example.digikala.databinding.FragmentProductDetailBinding;
import com.example.digikala.viewmodel.HomeFragmentViewModel;

public class ProductDetailFragment extends Fragment {


    private HomeFragmentViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;
    private Product mProduct;
    public static ProductDetailFragment newInstance() {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeFragmentViewModel.class);
        mProduct = mViewModel.getProductSelectedLiveData().getValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);

        return mBinding.getRoot();
    }
}