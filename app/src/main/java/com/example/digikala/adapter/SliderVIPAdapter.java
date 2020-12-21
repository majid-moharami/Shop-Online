package com.example.digikala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.digikala.R;
import com.example.digikala.databinding.SliderItemBinding;
import com.example.digikala.utillity.ImageConverterResource;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderVIPAdapter extends
        SliderViewAdapter<SliderVIPAdapter.SliderAdapterVH2>{

    private Context mContext;
    List<String> stringsResource = new ArrayList<>();


    public SliderVIPAdapter(Context context) {
        mContext = context;
        ImageConverterResource imageConverterResource = new ImageConverterResource();
        stringsResource = imageConverterResource.getAllImage();
    }

    @Override
    public SliderAdapterVH2 onCreateViewHolder(ViewGroup parent) {
        SliderItemBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.slider_item,
                parent,
                false
        );
        return new SliderVIPAdapter.SliderAdapterVH2(itemListBinding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH2 viewHolder, int position) {
        viewHolder.onBind(position , mContext);
    }

    @Override
    public int getCount() {
        return 5;
    }

    class SliderAdapterVH2 extends SliderViewAdapter.ViewHolder {
        SliderItemBinding mSliderItemBinding;
        public SliderAdapterVH2(SliderItemBinding sliderItemBinding) {
            super(sliderItemBinding.getRoot());
            mSliderItemBinding  = sliderItemBinding;
        }

        public void onBind(int position , Context context){
            Glide.with(context).load(stringsResource.get(position)).into(mSliderItemBinding.imageProduct);
        }
    }
}
