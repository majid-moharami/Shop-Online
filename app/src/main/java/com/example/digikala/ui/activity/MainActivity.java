package com.example.digikala.ui.activity;

import androidx.fragment.app.Fragment;

import com.example.digikala.data.network.retrofit.WooCommerceService;
import com.example.digikala.ui.fragment.HomeFragment;

public class MainActivity extends SingleFragmentActivity {

    private WooCommerceService mCommerceService;

    @Override
    public Fragment createFragment() {
        return HomeFragment.newInstance();
    }
}