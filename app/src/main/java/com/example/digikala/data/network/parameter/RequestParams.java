package com.example.digikala.data.network.parameter;

import java.util.HashMap;
import java.util.Map;

public class RequestParams {
    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String BASE_PATH_PRODUCT = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/";
    public static final String BASE_PATH_CATEGORY = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/categories/";
    public static final String CONSUMER_KEY = "ck_23cd258c7b39b4820c4014c9d6be6448bb4f2264";
    public static final String CONSUMER_SECRET = "cs_1d3857709fc7b5c874fcec577cf601f9f00600cc";
    public static final String CUSTOMER_CREATE_PATH = BASE_URL + "customers" + "/?consumer_key=" +
            CONSUMER_KEY + "&consumer_secret=" + CONSUMER_SECRET;
    public static final String POPULARITY = "popularity";
    public static final String RATING = "rating";
    public static final int DIGITAL_ID = 52;
    public static final int HEALTH_ID = 121;
    public static final int SUPER_MARKET_ID = 81;
    public static final int SPECIAL_SALE_ID = 119;
    public static final int BOOK_ART_ID = 76;
    public static final int FASHION_CLOTHING_ID = 62;

    public static Map<String, String> BASE_PARAM = new HashMap<String, String>() {{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);
    }};

    public static Map<String, String> POPULAR_PRODUCT = new HashMap<String, String>() {{
        putAll(BASE_PARAM);
        put("orderby", POPULARITY);
    }};

    public static Map<String, String> RATING_PRODUCT = new HashMap<String, String>() {{
        putAll(BASE_PARAM);
        put("orderby", RATING);
    }};

    public static Map<String, String> SUB_CATEGORIES = new HashMap<String, String>() {{
        putAll(BASE_PARAM);
    }};

}
