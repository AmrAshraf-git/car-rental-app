package com.example.carrental.utility.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.carrental.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder>{

    private final String[] images;

    public SliderAdapter(String[] images){

        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_slider_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        //viewHolder.imageView.setImageResource(images[position]);
        Picasso.get().load(images[position]).fit().centerInside().error(R.drawable.img_logo_test).into(viewHolder.imageView);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    static class ViewHolder extends SliderViewAdapter.ViewHolder{

        ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.sliderItem_imgView_sliderImg);
        }
    }



}
