package com.example.digikala.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.digikala.data.network.retrofit.WooCommerceService;
import com.example.digikala.view.activity.SingleFragmentActivity;
import com.example.digikala.view.fragment.HomeFragment;

public class MainActivity extends SingleFragmentActivity {

    private WooCommerceService mCommerceService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Fragment createFragment() {
        return HomeFragment.newInstance();
    }
}