package com.example.digikala.data.network.retrofit;

import com.example.digikala.data.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WooCommerceService {
    @GET(".")
    Call<List<Product>> listAllProduct(@QueryMap Map<String, String> products );

    @GET("{id}/")
    Call<Product> product( @Path("id") String id , @QueryMap Map<String, String> products );
}
