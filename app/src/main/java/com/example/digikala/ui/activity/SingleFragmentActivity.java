package com.example.digikala.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.digikala.R;
import com.example.digikala.databinding.ActivitySingleFragmentBinding;
import com.example.digikala.ui.fragment.GroupingFragment;
import com.example.digikala.ui.fragment.HomeFragment;
import com.example.digikala.ui.fragment.MyDigikalaFragment;
import com.example.digikala.ui.fragment.ShoppingCartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    public static final String FRAGMENT_TAG = "fragmentActivity";
    private ActivitySingleFragmentBinding mBinding;

    public abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_single_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container , createFragment() , FRAGMENT_TAG)
                    .commit();
        }

        mBinding.bottomNav.setItemIconTintList(null);
        mBinding.bottomNav.setOnNavigationItemSelectedListener(mReselectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mReselectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home_nav:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container , HomeFragment.newInstance() , FRAGMENT_TAG)
                                    .commit();
                            break;
                        case R.id.grouping_nav:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container ,GroupingFragment.newInstance(), FRAGMENT_TAG)
                                    .commit();
                            break;
                        case R.id.basket_nav:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container ,ShoppingCartFragment.newInstance() , FRAGMENT_TAG)
                                    .commit();
                            break;
                        case R.id.my_dijikala_nav:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container , MyDigikalaFragment.newInstance() , FRAGMENT_TAG)
                                    .commit();
                            break;
                    }
                    return true;
                }
            };

}
