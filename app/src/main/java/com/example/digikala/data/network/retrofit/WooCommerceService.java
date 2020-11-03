package com.example.digikala.data.network.retrofit;

import com.example.digikala.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WooCommerceService {
    @GET(".")
    Call<List<Product>> listAllProduct();

}
