package com.example.digikala.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.digikala.R;
import com.example.digikala.databinding.FragmentCategoryBinding;
import com.example.digikala.viewmodel.CategoryFragmentViewModel;
import com.example.digikala.viewmodel.HomeFragmentViewModel;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding mBinding;
    private CategoryFragmentViewModel mViewModel;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_category , container, false);
        return mBinding.getRoot();
    }
}