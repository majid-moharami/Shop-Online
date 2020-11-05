package com.example.digikala.data.network;

import java.util.HashMap;
import java.util.Map;

public class RequestParams {
    public static final String BASE_PATH_PRODUCT = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/";
    public static final String BASE_PATH_CATEGORY = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/categories/";
    public static final String CONSUMER_KEY = "ck_23cd258c7b39b4820c4014c9d6be6448bb4f2264";
    public static final String CONSUMER_SECRET = "cs_1d3857709fc7b5c874fcec577cf601f9f00600cc";

    public static Map<String,String> RECENT_PRODUCT = new HashMap<String,String>(){{
        put("consumer_key" , CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);

    }};
}
