package com.example.digikala.data.network.retrofit;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.model.category.Category;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WooCommerceService {
    @GET(".")
    Call<List<Product>> listAllProduct(@QueryMap Map<String, String> products ,  @Query("page") int page );

    @GET("{id}/")
    Call<Product> product( @Path("id") String id , @QueryMap Map<String, String> products );

    @GET(".")
    Call<List<Category>> SubCategories( @QueryMap Map<String, String> products , @Query("parent") int parent);

    @GET(".")
    Call<List<Product>> productListOfCategory(
            @QueryMap Map<String, String> products ,
            @Query("category") int category,
            @Query("page") int page);
}
