package com.example.digikala.utillity;

import android.media.Image;
import android.net.Uri;

import com.example.digikala.data.model.ProductImage;
import com.example.digikala.data.model.poduct.Product;

import java.util.ArrayList;
import java.util.List;

public class ImageUtil {

    public static String getFirstImageUrlOfProduct(Product product) {
        List<ProductImage> images = product.getImages();
        if (images != null && !images.isEmpty())
            return images.get(0).getImageURL();
        else
            throw new NullPointerException("this product doesn't have any images");
    }

}
