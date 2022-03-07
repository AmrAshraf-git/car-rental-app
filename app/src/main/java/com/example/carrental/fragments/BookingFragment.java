package com.example.carrental.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.carrental.dataModels.Vehicle;
import com.example.carrental.R;
import com.example.carrental.adapters.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.security.InvalidParameterException;


public class BookingFragment extends Fragment {


    private static final String HOME_LIST_ITEM = "listItemObject";

    private TextView carModel;
    private TextView companyName;
    private TextView companyAddress;
    //private ImageView carImage;
    //private HomeListItem homeListItem;
    private TextView color;
    private TextView doorsNo;
    private TextView chairsNo;
    private TextView engine;
    private TextView price;
    private SliderView sliderView;
    private Button bookNow;

    private Vehicle vehicle;

    public BookingFragment() {
        // Required empty public constructor
    }


    public static BookingFragment newInstance(Vehicle Vehicle) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putParcelable(HOME_LIST_ITEM, Vehicle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vehicle = getArguments().getParcelable(HOME_LIST_ITEM);
        }
        else throw new InvalidParameterException("Incompatible argument");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_booking, container, false);
        /*
        carModel=view.findViewById(R.id.viewAllDetails_txtView_carModel);
        companyName=view.findViewById(R.id.viewAllDetails_txtView_companyName);
        //carImage=findViewById(R.id.viewAllDetails_imgView_carImage);
        companyAddress=view.findViewById(R.id.viewAllDetails_txtView_companyAddress);
        color=view.findViewById(R.id.viewAllDetails_txtView_color);
        doorsNo=view.findViewById(R.id.viewAllDetails_txtView_doorsNumber);
        chairsNo=view.findViewById(R.id.viewAllDetails_txtView_chairsNumber);
        engine=view.findViewById(R.id.textView12);
        price=view.findViewById(R.id.viewAllDetails_txtView_pricePerHour);
        sliderView = view.findViewById(R.id.image_slider);*/


        carModel=view.findViewById(R.id.bookingActivity_txtView_carModel);
        companyName=view.findViewById(R.id.bookingActivity_txtView_companyName);
        //carImage=findViewById(R.id.viewAllDetails_imgView_carImage);
        companyAddress=view.findViewById(R.id.bookingActivity_txtView_companyAddress);
        color=view.findViewById(R.id.bookingActivity_txtView_color);
        doorsNo=view.findViewById(R.id.bookingActivity_txtView_doorsNumber);
        chairsNo=view.findViewById(R.id.bookingActivity_txtView_chairsNumber);
        engine=view.findViewById(R.id.bookingActivity_txtView_engineType);
        price=view.findViewById(R.id.bookingActivity_txtView_pricePerHour);
        sliderView = view.findViewById(R.id.bookingActivity_imageSlider_imagesOfCar);
        bookNow = view.findViewById(R.id.bookingActivity_btn_bookNow);


        //====================================RECEIVE DATA=====================================
        //Bundle bundle=this.getArguments();
        //if(bundle!=null) {

            //======================================DEBUG=======================================
            //Log.d("click","data Received successfully");
            //Log.d("id",String.valueOf(id));
            //Log.d("ReceivedData",String.valueOf(bundle.getParcelable("listItemObject")));
            //======================================DEBUG=======================================

            //homeListItem=bundle.getParcelable("listItemObject");
            //int id=bundle.getInt("id");
        //}
        //else
            //throw new InvalidParameterException("Bundle is empty");
        //====================================RECEIVE DATA=====================================



        // TODO: Get data from 'homeListDataModel' or 'homeListDataModel.getVehicleSpecs' and pass them into their proper view
        carModel.setText(vehicle.getVehicleModel());
        companyName.setText(vehicle.getCompanyName());
        //carImage.setImageResource(homeListItem.getCarImg());
        companyAddress.setText(vehicle.getCompanyAddress());
        price.setText(String.valueOf(vehicle.getPrice()));
        color.setText(vehicle.getVehicleColor());
        doorsNo.setText(String.valueOf(vehicle.getDoorsNum()));
        chairsNo.setText(String.valueOf(vehicle.getSeatingCapacity()));
        engine.setText(vehicle.getVehicleSpecs().getAutomaticTransmission()?"Automatic":"Manual");

        //==================Image Slider Show=============================
        int[] images = {vehicle.getVehicleImg()[0],
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


        return view;
    }



}