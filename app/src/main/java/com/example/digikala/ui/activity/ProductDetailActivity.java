package com.example.digikala.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digikala.R;
import com.example.digikala.ui.fragment.ProductDetailFragment;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_ID = "extraID";

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_KEY_ID , id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_container, ProductDetailFragment.newInstance(getIntent().getIntExtra(
                        EXTRA_KEY_ID ,
                        664)))
                .commit();
    }
}