package com.example.digikala.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.digikala.R;
import com.example.digikala.data.database.entity.CartProduct;
import com.example.digikala.data.repository.CartProductDBRepository;

public class BasketFragment extends Fragment {


    private CartProductDBRepository mCartProductDBRepository;
    private TextView mTextView;
    public static BasketFragment newInstance() {
        BasketFragment fragment = new BasketFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCartProductDBRepository = CartProductDBRepository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_basket, container, false);
        mTextView = v.findViewById(R.id.ttttttttt);
        mTextView.setText(mCartProductDBRepository.getAllCartProduct().size()+"");
        return v;
    }
}