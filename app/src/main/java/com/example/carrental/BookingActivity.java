package com.example.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.adapters.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


import java.security.InvalidParameterException;

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
/*
    private ImageView imgView_abs;
    private ImageView imgView_airbags;
    private ImageView imgView_seatbelt;
    private ImageView imgView_ac;
    private ImageView imgView_bluetooth;
    private ImageView imgView_sunroof;
    private ImageView imgView_radio;
    private ImageView imgView_music_player;
    private ImageView imgView_remote_start;
    private ImageView imgView_parking_sensors;
    private ImageView imgView_navigation_system;
    private ImageView imgView_extra_tyre;
    private ImageView imgView_charger;
    private ImageView imgView_fire_extinguisher;
    private ImageView imgView_car_seat;
    private ImageView imgView_first_aid_kit;
    private ImageView imgView_Smoking;
    private ImageView imgView_Non_Smoking;
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        initialization();

        //====================================RECEIVE DATA====================================
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {

            //============================DEBUG=================
            //Log.d("click","data Received successfully");
            //Log.d("id",String.valueOf(id));
            //Log.d("ReceivedData",String.valueOf(bundle.getParcelable("listItemObject")));
            //============================DEBUG=================

            homeListItem=bundle.getParcelable("listItemObject");
            int id=bundle.getInt("id");
        }
        else
            throw new InvalidParameterException("Bundle is empty");
        //====================================RECEIVE DATA====================================



        //================================SET DATA============================================
        carModel.setText(homeListItem.getCarModel());
        companyName.setText(homeListItem.getCompanyName());
        //carImage.setImageResource(homeListItem.getCarImg());
        companyAddress.setText(homeListItem.getCompanyAddress());
        price.setText(String.valueOf(homeListItem.getPrice()));
        color.setText(homeListItem.getSpecs()[0]);
        doorsNo.setText(homeListItem.getSpecs()[1]);
        chairsNo.setText(homeListItem.getSpecs()[2]);
        engine.setText(homeListItem.getSpecs()[3]);

        //==================Image Slider Show=====================
        int[] images = {homeListItem.getCarImg(),
                R.drawable.ic_car_default_black,
                R.drawable.ic_car_default_black,
                R.drawable.ic_car_default_black,};
        //==================Image Slider Show=====================

        //================================SET DATA============================================




        //==================Image Slider Show=============================
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycle(false);
        //==================Image Slider Show=============================
    }

    public void initialization() {
        carModel=findViewById(R.id.bookingActivity_txtView_carModel);
        companyName=findViewById(R.id.bookingActivity_txtView_companyName);
        //carImage=findViewById(R.id.viewAllDetails_imgView_carImage);
        companyAddress=findViewById(R.id.bookingActivity_txtView_companyAddress);
        color=findViewById(R.id.bookingActivity_txtView_color);
        doorsNo=findViewById(R.id.bookingActivity_txtView_doorsNumber);
        chairsNo=findViewById(R.id.bookingActivity_txtView_chairsNumber);
        engine=findViewById(R.id.bookingActivity_txtView_engineType);
        price=findViewById(R.id.bookingActivity_txtView_pricePerHour);
        sliderView = findViewById(R.id.bookingActivity_imageSlider_imagesOfCar);

    /*
        imgView_abs = findViewById(R.id.bookingActivity_imgView_abs);
        imgView_airbags = findViewById(R.id.bookingActivity_imgView_airbags);
        imgView_seatbelt = findViewById(R.id.bookingActivity_imgView_seatbelt);
        imgView_ac = findViewById(R.id.bookingActivity_imgView_ac);
        imgView_bluetooth = findViewById(R.id.bookingActivity_imgView_bluetooth);
        imgView_sunroof = findViewById(R.id.bookingActivity_imgView_sunroof);
        imgView_radio = findViewById(R.id.bookingActivity_imgView_radio);
        imgView_music_player = findViewById(R.id.bookingActivity_imgView_music_player);
        imgView_remote_start = findViewById(R.id.bookingActivity_imgView_remote_start);
        imgView_parking_sensors = findViewById(R.id.bookingActivity_imgView_parking_sensors);
        imgView_navigation_system = findViewById(R.id.bookingActivity_imgView_navigation_system);
        imgView_extra_tyre = findViewById(R.id.bookingActivity_imgView_extra_tyre);
        imgView_charger = findViewById(R.id.bookingActivity_imgView_charger);
        imgView_fire_extinguisher = findViewById(R.id.bookingActivity_imgView_fire_extinguisher);
        imgView_car_seat = findViewById(R.id.bookingActivity_imgView_car_seat);
        imgView_first_aid_kit = findViewById(R.id.bookingActivity_imgView_first_aid_kit);
        imgView_Smoking = findViewById(R.id.bookingActivity_imgView_Smoking);
        imgView_Non_Smoking = findViewById(R.id.bookingActivity_imgView_Non_Smoking);
    */






    }
}