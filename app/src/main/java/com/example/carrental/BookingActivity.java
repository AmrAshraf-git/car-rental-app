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


import java.security.InvalidParameterException;
import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {

    private TextView carModel;
    private TextView companyName;
    private TextView companyAddress;
    //private ImageView carImage;
    private HomeListItem homeListItem;
    private TextView color;
    private TextView doorsNo;
    private TextView chairsNo;
    private TextView engine;
    private TextView price;
    private SliderView sliderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        initialization();
        //====================================RECEIVE DATA=====================================
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {

            //======================================DEBUG=======================================
            //Log.d("click","data Received successfully");
            //Log.d("id",String.valueOf(id));
            //Log.d("ReceivedData",String.valueOf(bundle.getParcelable("listItemObject")));
            //======================================DEBUG=======================================

            homeListItem=bundle.getParcelable("listItemObject");
            int id=bundle.getInt("id");
        }
        else
            throw new InvalidParameterException("Bundle is empty");
        //====================================RECEIVE DATA=====================================


        carModel.setText(homeListItem.getCarModel());
        companyName.setText(homeListItem.getCompanyName());
        //carImage.setImageResource(homeListItem.getCarImg());
        companyAddress.setText(homeListItem.getCompanyAddress());
        price.setText(String.valueOf(homeListItem.getPrice()));
        color.setText(homeListItem.getSpecs()[0]);
        doorsNo.setText(homeListItem.getSpecs()[1]);
        chairsNo.setText(homeListItem.getSpecs()[2]);
        engine.setText(homeListItem.getSpecs()[3]);



        //==================Image Slider Show=============================
        int[] images = {homeListItem.getCarImg(),
                R.drawable.ic_car_default_black,
                R.drawable.ic_car_default_black,
                R.drawable.ic_car_default_black,};
        //==================Image Slider Show=============================

        //==================Image Slider Show=============================
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycle(false);
        //==================Image Slider Show=============================
    }


    public void initialization() {
        carModel=findViewById(R.id.viewAllDetails_txtView_carModel);
        companyName=findViewById(R.id.viewAllDetails_txtView_companyName);
        //carImage=findViewById(R.id.viewAllDetails_imgView_carImage);
        companyAddress=findViewById(R.id.viewAllDetails_txtView_companyAddress);
        color=findViewById(R.id.viewAllDetails_txtView_color);
        doorsNo=findViewById(R.id.viewAllDetails_txtView_doorsNumber);
        chairsNo=findViewById(R.id.viewAllDetails_txtView_chairsNumber);
        engine=findViewById(R.id.textView12);
        price=findViewById(R.id.viewAllDetails_txtView_pricePerHour);
        sliderView = findViewById(R.id.image_slider);
    }
}