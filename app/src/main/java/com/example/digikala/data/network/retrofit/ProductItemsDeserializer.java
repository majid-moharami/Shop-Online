package com.example.digikala.data.network.retrofit;

import com.example.digikala.data.model.Product;
import com.example.digikala.data.model.ProductCategory;
import com.example.digikala.data.model.ProductImage;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductItemsDeserializer implements JsonDeserializer<List<Product>> {
    @Override
    public List<Product> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        List<Product> products = new ArrayList<>();

        JsonArray jsonArray = object.getAsJsonArray();
        for (int i = 0; i <jsonArray.size() ; i++) {
            JsonObject productObject = jsonArray.get(i).getAsJsonObject();

            String name = productObject.get("name").getAsString();
            String id = productObject.get("id").getAsString();
            String description = productObject.get("description").getAsString();
            String price = productObject.get("price").getAsString();
            String regularPrice = productObject.get("regular_price").getAsString();
            String averageRating = productObject.get("average_rating").getAsString();
            String ratingCount = productObject.get("rating_count").getAsString();

            List<ProductCategory> categoryList = new ArrayList<>();
            JsonArray category = productObject.get("categories").getAsJsonArray();
            for (int j = 0; j <category.size() ; j++) {
                JsonObject categoryObject = category.get(i).getAsJsonObject();
                String catName = categoryObject.get("name").getAsString();
                String catId = categoryObject.get("id").getAsString();
                ProductCategory productCategory = new ProductCategory(catName , catId);
                categoryList.add(productCategory);
            }

            List<ProductImage> imageList = new ArrayList<>();
            JsonArray images = productObject.get("categories").getAsJsonArray();
            for (int j = 0; j <images.size() ; j++) {
                JsonObject imageObject = images.get(i).getAsJsonObject();
                String url = imageObject.get("src").getAsString();
                String imageName = imageObject.get("name").getAsString();
                String imageId = imageObject.get("id").getAsString();
                ProductImage productImage = new ProductImage(url , imageName , imageId);
                imageList.add(productImage);
            }

            Product product = new Product(
                    name,
                    id,
                    description,
                    price,
                    regularPrice,
                    averageRating,
                    ratingCount,
                    imageList,
                    categoryList
            );
            products.add(product);
        }

        return products;
    }
}
