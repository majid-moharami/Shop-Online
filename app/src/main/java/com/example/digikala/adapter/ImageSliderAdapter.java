package com.example.digikala.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.digikala.data.model.ProductImage;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImageSliderAdapter extends SliderViewAdapter {
    private List<ProductImage> mImageList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    class SliderHolder extends ViewHolder {

        public SliderHolder(ImageView itemView) {
            super(itemView);
        }
    }
}
