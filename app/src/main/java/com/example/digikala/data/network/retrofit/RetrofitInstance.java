package com.example.digikala.data.network.retrofit;

import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.model.category.Category;
import com.example.digikala.data.network.parameter.RequestParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static Retrofit getInstance(){
        Type type = new TypeToken<List<Product>>(){}.getType();
        Object typeAdapter = new ProductItemsDeserializer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RequestParams.BASE_PATH_PRODUCT)
                .addConverterFactory(createGsonConverter(type , typeAdapter))
                .build();
        return retrofit;
    }

    public static Retrofit getInstanceSingleProduct(){
        Type type = new TypeToken<Product>(){}.getType();
        Object typeAdapter = new SingleProductDeserializer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RequestParams.BASE_PATH_PRODUCT)
                .addConverterFactory(createGsonConverter(type , typeAdapter))
                .build();
        return retrofit;
    }

    public static Retrofit getInstanceSubCategories(){
        Type type = new TypeToken<List<Category>>(){}.getType();
        Object typeAdapter = new CategoryDeserializer();
        return new Retrofit.Builder()
                .baseUrl(RequestParams.BASE_PATH_CATEGORY)
                .addConverterFactory(createGsonConverter(type , typeAdapter))
                .build();
    }

    public static Retrofit getProductsOfCategory(){
        Type type = new  TypeToken<List<Product>>(){}.getType();
        Object typeAdapter = new ProductItemsDeserializer();
        return new Retrofit.Builder()
                .baseUrl(RequestParams.BASE_PATH_PRODUCT)
                .addConverterFactory(createGsonConverter(type , typeAdapter))
                .build();
    }

    public static Retrofit getProductsOfSearch(){
        Type type = new  TypeToken<List<Product>>(){}.getType();
        Object typeAdapter = new ProductItemsDeserializer();
        return new Retrofit.Builder()
                .baseUrl(RequestParams.BASE_PATH_PRODUCT)
                .addConverterFactory(createGsonConverter(type , typeAdapter))
                .build();
    }

    private static Converter.Factory createGsonConverter(Type type , Object typeAdapter){
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(type , typeAdapter);
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }
}
