package com.example.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carrental.adapters.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


import java.util.ArrayList;

public class ViewAllDetailsAndBooking extends AppCompatActivity {

    private TextView carModel;
    private TextView companyName;
    private ImageView carImage;
    private ArrayList<HomeListItem> homeListItemArrayList;

    //==================Image Slider Show=============================
    SliderView sliderView;
    int[] images = {R.drawable.ic_car_default_black,
                    R.drawable.ic_car_default_black,
                    R.drawable.ic_car_default_black,
                    R.drawable.ic_car_default_black,};
    //==================Image Slider Show=============================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_details_and_booking);

        initialization();

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {

            //test
            int pos = bundle.getInt("position");
            Log.e("position", String.valueOf(pos));
            //Log.e("model",homeListItemArrayList.get(pos).getCarModel());
            carModel.setText(bundle.getString("carModel"));
        }

    }
    public void initialization() {
        carModel=findViewById(R.id.viewAllDetails_txtView_carModel);
        companyName=findViewById(R.id.viewAllDetails_txtView_companyName);
        carImage=findViewById(R.id.viewAllDetails_imgView_carImage);

//==================Image Slider Show=============================
        sliderView = findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycle(false);
//==================Image Slider Show=============================

    }
}