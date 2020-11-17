package com.example.digikala.data.network.retrofit;

import com.example.digikala.data.model.category.Category;
import com.example.digikala.data.model.category.CategoryImage;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoryDeserializer implements JsonDeserializer<List<Category>> {
    @Override
    public List<Category> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray baseObject = json.getAsJsonArray();
        List<Category> allCategory = new ArrayList<>();

        for (int i = 0; i < baseObject.size(); i++) {
            JsonObject categoryObject = baseObject.get(i).getAsJsonObject();

            //image
            JsonObject categoryImageObject =categoryObject.get("image").getAsJsonObject();
            int imageId = categoryImageObject.get("id").getAsInt();
            String src = categoryImageObject.get("src").getAsString();
            CategoryImage categoryImage = new CategoryImage(imageId,src);

            //category
            int id = categoryObject.get("id").getAsInt();
            String name = categoryObject.get("name").getAsString();
            int parent = categoryObject.get("parent").getAsInt();

            allCategory.add(new Category(id , name , parent , categoryImage));
        }

        return allCategory;
    }
}
