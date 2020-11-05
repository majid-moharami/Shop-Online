package com.example.digikala.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digikala.R;
import com.example.digikala.data.model.Product;
import com.example.digikala.data.network.RequestParams;
import com.example.digikala.data.network.retrofit.RetrofitInstance;
import com.example.digikala.data.network.retrofit.WooCommerceService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private WooCommerceService mCommerceService;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = RetrofitInstance.getInstance();
        mCommerceService = retrofit.create(WooCommerceService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView t = view.findViewById(R.id.ttttt);
        Call<List<Product>> listCall = mCommerceService.listAllProduct(RequestParams.RECENT_PRODUCT);
        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> s = response.body();
                t.setText(s.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("MAJID" , t.toString() , t);
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}