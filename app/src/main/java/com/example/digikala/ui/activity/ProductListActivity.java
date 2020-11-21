package com.example.digikala.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.digikala.R;
import com.example.digikala.databinding.ActivityProductListBinding;
import com.example.digikala.ui.fragment.ProductListFragment;
import com.example.digikala.utillity.ListType;

public class ProductListActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_CATEGORY_ID = "com.example.digikala.ui_CategoryId";
    public static final String EXTRA_KEY_LIST_TYPE = "com.example.digikala.ui_ListType";
    private ActivityProductListBinding mBinding;

    /**
     *
     * @param context
     * @param categoryId if start this activity from categories put the category id else return -1.
     * @param hemeListType if start this activity from home fragment send the type of list to show else return null.
     * @return
     */
    public static Intent newIntent(Context context , int categoryId, ListType hemeListType){
        Intent intent = new Intent(context , ProductListActivity.class);
        intent.putExtra(EXTRA_KEY_CATEGORY_ID , categoryId);
        intent.putExtra(EXTRA_KEY_LIST_TYPE, hemeListType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_product_list);

        int categoryId = getIntent().getIntExtra(EXTRA_KEY_CATEGORY_ID,-1);
        ListType listType = (ListType) getIntent().getSerializableExtra(EXTRA_KEY_LIST_TYPE);


        if (categoryId==-1) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.list_fragment_container,
                            ProductListFragment.newInstance(listType)
                    )
                    .commit();
        }else {

        }
    }
}