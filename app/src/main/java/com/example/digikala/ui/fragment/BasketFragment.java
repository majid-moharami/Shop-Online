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
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.digikala.R;
import com.example.digikala.adapter.CartProductListAdapter;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.databinding.FragmentBasketBinding;
import com.example.digikala.utillity.DeleteProductHelper;
import com.example.digikala.utillity.State;
import com.example.digikala.viewmodel.CartFragmentViewModel;

import java.util.List;

public class BasketFragment extends Fragment {
    private CartFragmentViewModel mViewModel;
    private FragmentBasketBinding mBinding;
    private CartProductListAdapter mAdapter;

    //private CartProductDBRepository mCartProductDBRepository;
    public static BasketFragment newInstance() {
        BasketFragment fragment = new BasketFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BasketFragment", "onCreate");
        mViewModel = new ViewModelProvider(this).get(CartFragmentViewModel.class);
        mViewModel.fetchAllProducts();
        mAdapter = new CartProductListAdapter(mViewModel, this);
        observer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

    private void observer() {


        mViewModel.getRequestState().observe(this, new Observer<State>() {
            @Override
            public void onChanged(State state) {
                if (state == State.NAVIGATE) {
                    Log.d("CartProductLoadingFragment", state.toString());
                    mBinding.progressBarLoadingFragment.setVisibility(View.GONE);
                    mBinding.baseLayout.setVisibility(View.VISIBLE);
                    mViewModel.getProducts().observe(BasketFragment.this, new Observer<List<Product>>() {
                        @Override
                        public void onChanged(List<Product> products) {
                            mAdapter.notifyDataSetChanged();
                            mBinding.textViewAllProductPrice.setText(mViewModel.calculateAllPrice(products)+" تومان ");
                        }
                    });
                    /*because repository is alive and next time that we go to this fragment the
                    repository is alive, we set state NONE to get the default station.*/
                    mViewModel.setState(State.NONE);
                }

                if (state == State.LOADING){
                    mBinding.progressBarLoadingFragment.setVisibility(View.VISIBLE);
                    mBinding.baseLayout.setVisibility(View.GONE);
                    mViewModel.setState(State.NONE);
                }
            }
        });

        mViewModel.getDeleteProductHelperMutableLiveData().observe(this, new Observer<DeleteProductHelper>() {
            @Override
            public void onChanged(DeleteProductHelper deleteProductHelper) {

            }
        });
    }

}