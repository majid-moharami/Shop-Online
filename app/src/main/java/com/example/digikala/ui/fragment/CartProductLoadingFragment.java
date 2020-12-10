package com.example.digikala.ui.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digikala.R;
import com.example.digikala.databinding.FragmentCartProductLoadingBinding;
import com.example.digikala.utillity.State;
import com.example.digikala.viewmodel.CartFragmentViewModel;

public class CartProductLoadingFragment extends Fragment {

    private FragmentCartProductLoadingBinding mBinding;
    private CartFragmentViewModel mViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartFragmentViewModel.class);
        mViewModel.fetchAllProducts();
        mViewModel.getRequestState().observe(this, new Observer<State>() {
            @Override
            public void onChanged(State state) {
                if (state == State.NAVIGATE){
                    Navigation.findNavController(getView())
                            .navigate(CartProductLoadingFragmentDirections.actionFragmentBasketToFragmentBasketAfterLoad());
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart_product_loading, container, false);
        return mBinding.getRoot();
    }
}