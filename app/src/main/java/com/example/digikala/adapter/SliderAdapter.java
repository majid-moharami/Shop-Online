package com.example.digikala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.digikala.R;
import com.example.digikala.data.model.poduct.Product;
import com.example.digikala.data.model.ProductImage;
import com.example.digikala.databinding.SliderItemBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context mContext;
    private List<ProductImage> mSliderItems = new ArrayList<>();

    public SliderAdapter(Context context , Product product) {
        mContext = context;
        mSliderItems = product.getImages();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        SliderItemBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.slider_item,
                parent,
                false
        );
        return new SliderAdapterVH(itemListBinding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        viewHolder.onBind(position , mContext);
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        SliderItemBinding mSliderItemBinding;
        public SliderAdapterVH(SliderItemBinding sliderItemBinding) {
            super(sliderItemBinding.getRoot());
           mSliderItemBinding  = sliderItemBinding;
        }

        public void onBind(int position , Context context){
            Glide.with(context).load(mSliderItems.get(position).getImageURL()).into(mSliderItemBinding.imageProduct);
        }
    }

}
