package com.example.digikala.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digikala.R;
import com.example.digikala.data.database.entity.CartProduct;
import com.example.digikala.data.repository.CartProductDBRepository;
import com.example.digikala.viewmodel.CartFragmentViewModel;

import java.util.List;

public class BasketFragment extends Fragment {

    private CartFragmentViewModel mViewModel;
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
        mViewModel = new ViewModelProvider(this).get(CartFragmentViewModel.class);
        mViewModel.getCartProductLiveData().observe(this, new Observer<List<CartProduct>>() {
            @Override
            public void onChanged(List<CartProduct> cartProducts) {
                Toast.makeText(getContext(), cartProducts.size()+"", Toast.LENGTH_SHORT).show();
            }
        });
       // mCartProductDBRepository = CartProductDBRepository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_basket, container, false);

        return v;
    }
}