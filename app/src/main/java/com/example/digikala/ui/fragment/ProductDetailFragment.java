package com.example.digikala.ui.fragment;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digikala.R;
import com.example.digikala.adapter.SliderAdapter;
import com.example.digikala.data.database.entity.CartProduct;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.databinding.FragmentProductDetailBinding;
import com.example.digikala.viewmodel.DetailFragmentViewModel;

public class ProductDetailFragment extends Fragment {


    public static final String BUNDLE_KEY_PRODUCT_ID = "productID";
    private DetailFragmentViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;
    private Product mProduct;
    private SliderAdapter mSliderAdapter;
    private int mId;

    public static ProductDetailFragment newInstance(int id) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_PRODUCT_ID , id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(DetailFragmentViewModel.class);
        mId = ProductDetailFragmentArgs.fromBundle(getArguments()).getProductId();

        mViewModel.fetchProduct(String.valueOf(mId));
        mViewModel.getProductLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                mViewModel.setProductSubjectLiveData(product);
                mBinding.productSlider.setSliderAdapter(
                        new SliderAdapter(getContext() ,
                                mViewModel.getProductSubjectLiveData().getValue()));
                formatTexts();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);
        Typeface typeFace = Typeface.createFromAsset(mViewModel.getApplication().getAssets(), "fonts/Dirooz-FD.ttf");
        mBinding.textViewProductName.setTypeface(typeFace);
        mBinding.textViewProductDescription.setTypeface(typeFace);
        mBinding.buttonAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.addProductToCart(mId);
            }
        });
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }



    private void formatTexts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding.textViewProductDescription.setText(
                    Html.fromHtml(
                            mViewModel.getProductSubjectLiveData().getValue().getDescription(),
                            Html.FROM_HTML_MODE_COMPACT));
        } else {
            mBinding.textViewProductDescription.setText(
                    Html.fromHtml(mViewModel.getProductSubjectLiveData().getValue().getDescription()));
        }
    }
}