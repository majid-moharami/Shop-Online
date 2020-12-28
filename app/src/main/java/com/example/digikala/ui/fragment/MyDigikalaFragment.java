package com.example.digikala.ui.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.digikala.R;
import com.example.digikala.databinding.FragmentMyDigikalaBinding;
import com.example.digikala.viewmodel.CustomerViewModel;

public class MyDigikalaFragment extends Fragment {

    private FragmentMyDigikalaBinding mBinding;
    private CustomerViewModel mViewModel;

    public static MyDigikalaFragment newInstance() {
        MyDigikalaFragment fragment = new MyDigikalaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        mViewModel.getIsEmailExistLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean){
                    Toast.makeText(getContext(), "ایمیل وارد شده قبلا ثبت شده", Toast.LENGTH_SHORT).show();
                }else {
                    MyDigikalaFragmentDirections.ActionNavMyDigikalaFragment1ToPersonalInfoFragment action=
                            MyDigikalaFragmentDirections.actionNavMyDigikalaFragment1ToPersonalInfoFragment(mBinding.editTextPersian.getText().toString());
                    Navigation.findNavController(mBinding.getRoot()).navigate(action);
                }
                mBinding.progressBarLoadingFragment.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_digikala, container, false);
        mBinding.button.setOnClickListener(v -> {
            mBinding.progressBarLoadingFragment.setVisibility(View.VISIBLE);
            if (!(Patterns.EMAIL_ADDRESS.matcher(mBinding.editTextPersian.getText().toString()).matches())){
                Toast.makeText(getContext(), "ایمیل وارد شده صحیح نیست", Toast.LENGTH_SHORT).show();
                mBinding.progressBarLoadingFragment.setVisibility(View.INVISIBLE);
            }else if (TextUtils.isEmpty(mBinding.editTextPersian.getText().toString())){
                Toast.makeText(getContext(), "ایمیل خود را وارد کنید", Toast.LENGTH_SHORT).show();
                mBinding.progressBarLoadingFragment.setVisibility(View.INVISIBLE);
            }else {
                mViewModel.checkEmailExist(mBinding.editTextPersian.getText().toString());
            }
        });
        return mBinding.getRoot();
    }
}