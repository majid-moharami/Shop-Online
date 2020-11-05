package com.example.digikala.ui.activity;

import androidx.fragment.app.Fragment;

import com.example.digikala.ui.fragment.HomeFragment;

public class BaseActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return HomeFragment.newInstance();
    }
}