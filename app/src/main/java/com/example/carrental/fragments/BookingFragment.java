package com.example.carrental.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
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
    //1 ->  Brand Image
    private TextView vehicleModel; //2
    private TextView companyName; //3
    private TextView companyAddress; //4
    private RatingBar CompRate; //5
    private SliderView vehicleImg; //6
    private TextView vehicleColor; //7
    private TextView doorsNum; //8
    private TextView seatingCapacity; //9
    private RatingBar vehicleRate; //10
    private TextView price; //11
    private String priceLabel; //12

    private TextView airBag; //13
    private TextView seatBelts; //14
    private TextView ABS; //15
    private TextView sunRoof; //16
    private TextView parkingSensors; //17
    private TextView radio; //18
    private TextView bluetooth; //19
    private TextView navSystem; //20
    private TextView remoteStart; //21
    private TextView AC; //22
    private TextView musicPlayer; //23
    private TextView automaticTransmission; //24
    private TextView extraTyre; //25
    private TextView charger; //26
    private TextView fireExtinguisher; //27
    private TextView firstAidKit; //28
    private TextView carSeat; //29
    private TextView noSmoking; //30
    private TextView Smoking; //30.1
    private TextView CC; //31





    private Button bookNow;
    private Vehicle vehicle;

    //private ImageView carImage;
    //private HomeListItem homeListItem;

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


        //1 ->  Brand Image
        vehicleModel=view.findViewById(R.id.bookingActivity_txtView_vehicleModel);
        companyName=view.findViewById(R.id.bookingActivity_txtView_companyName);
        companyAddress=view.findViewById(R.id.bookingActivity_txtView_companyAddress);
        CompRate = view.findViewById(R.id.bookingActivity_ratingBar_companyRating);
        vehicleImg = view.findViewById(R.id.bookingActivity_imageSlider_imagesOfvehicle);
        vehicleColor=view.findViewById(R.id.bookingActivity_txtView_color);
        doorsNum=view.findViewById(R.id.bookingActivity_txtView_doorsNumber);
        seatingCapacity=view.findViewById(R.id.bookingActivity_txtView_chairsNumber);
        vehicleRate = view.findViewById(R.id.bookingActivity_ratingBar_vehicleRating);
        price=view.findViewById(R.id.bookingActivity_txtView_pricePerHour);

        airBag = view.findViewById(R.id.bookingActivity_txtView_airbag);
        seatBelts = view.findViewById(R.id.bookingActivity_txtView_seatbelts);
        ABS = view.findViewById(R.id.bookingActivity_txtView_ABS);
        sunRoof = view.findViewById(R.id.bookingActivity_txtView_sunroof);
        parkingSensors = view.findViewById(R.id.bookingActivity_txtView_parkingSensors);
        radio = view.findViewById(R.id.bookingActivity_txtView_radio);
        bluetooth = view.findViewById(R.id.bookingActivity_txtView_bluetooth);
        navSystem = view.findViewById(R.id.bookingActivity_txtView_navigationSystem);
        remoteStart = view.findViewById(R.id.bookingActivity_txtView_remoteStart);
        AC = view.findViewById(R.id.bookingActivity_txtView_airConditioner);
        musicPlayer = view.findViewById(R.id.bookingActivity_txtView_musicPlayer);
        automaticTransmission=view.findViewById(R.id.bookingActivity_txtView_engineType);

        extraTyre = view.findViewById(R.id.bookingActivity_txtView_extraTyre);
        charger = view.findViewById(R.id.bookingActivity_txtView_charger);
        fireExtinguisher = view.findViewById(R.id.bookingActivity_txtView_fireExtinguisher);
        firstAidKit = view.findViewById(R.id.bookingActivity_txtView_firstAidKit);
        carSeat = view.findViewById(R.id.bookingActivity_txtView_carSeat);
        noSmoking = view.findViewById(R.id.bookingActivity_txtView_no_smoking);
        Smoking = view.findViewById(R.id.bookingActivity_txtView_smoking);
        CC = view.findViewById(R.id.bookingActivity_txtView_enginePerformace);
        bookNow = view.findViewById(R.id.bookingActivity_btn_bookNow);


        //carImage=findViewById(R.id.viewAllDetails_imgView_carImage);


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
        //1 ->  Brand Image
        vehicleModel.setText(vehicle.getVehicleModel());
        companyName.setText(vehicle.getCompanyName());
        companyAddress.setText(vehicle.getCompanyAddress());
        CompRate.setRating(vehicle.getCompRate());
        vehicleColor.setText(vehicle.getVehicleColor());
        doorsNum.setText(String.valueOf(vehicle.getDoorsNum()));
        seatingCapacity.setText(String.valueOf(vehicle.getSeatingCapacity()));
        vehicleRate.setRating(vehicle.getVehicleRate());
        price.setText(String.valueOf(vehicle.getPrice())+" "+vehicle.getPriceLabel());
        automaticTransmission.setText(vehicle.getVehicleSpecs().getAutomaticTransmission()?"Automatic":"Manual");
        CC.setText("CC: "+String.valueOf(vehicle.getVehicleSpecs().getCC()));
        //carImage.setImageResource(homeListItem.getCarImg());

        //==================Image Slider Show=============================
        int[] images = {vehicle.getVehicleImg()[0],
                vehicle.getVehicleImg()[1],
                vehicle.getVehicleImg()[2]};
        //==================Image Slider Show=============================


        //==================Image Slider Show=============================
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        vehicleImg.setSliderAdapter(sliderAdapter);
        vehicleImg.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        vehicleImg.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        vehicleImg.setAutoCycle(false);
        //==================Image Slider Show=============================

        setSpecs();




        return view;
    }

    private void setSpecs()
    {

        if( vehicle.getVehicleSpecs().getAirBag() == null || !(vehicle.getVehicleSpecs().getAirBag()))
            airBag.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getSeatBelts() == null || !(vehicle.getVehicleSpecs().getSeatBelts()))
            seatBelts.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getABS() == null || !(vehicle.getVehicleSpecs().getABS()))
            ABS.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getSunRoof() == null || !(vehicle.getVehicleSpecs().getSunRoof()))
            sunRoof.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getParkingSensors() == null || !(vehicle.getVehicleSpecs().getParkingSensors()))
            parkingSensors.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getRadio() == null || !(vehicle.getVehicleSpecs().getRadio()))
            radio.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getBluetooth() == null || !(vehicle.getVehicleSpecs().getBluetooth()))
            bluetooth.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getNavSystem() == null || !(vehicle.getVehicleSpecs().getNavSystem()))
            navSystem.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getRemoteStart() == null || !(vehicle.getVehicleSpecs().getRemoteStart()))
            remoteStart.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getAC() == null || !(vehicle.getVehicleSpecs().getAC()))
            AC.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getMusicPlayer() == null || !(vehicle.getVehicleSpecs().getMusicPlayer()))
            musicPlayer.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getExtraTyre() == null || !(vehicle.getVehicleSpecs().getExtraTyre()))
            extraTyre.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getCharger() == null || !(vehicle.getVehicleSpecs().getCharger()))
            charger.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getFireExtinguisher() == null || !(vehicle.getVehicleSpecs().getFireExtinguisher()))
            fireExtinguisher.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getFirstAidKit() == null || !(vehicle.getVehicleSpecs().getFirstAidKit()))
            firstAidKit.setVisibility(View.GONE);

        if( vehicle.getVehicleSpecs().getCarSeat() == null || !(vehicle.getVehicleSpecs().getCarSeat()))
            carSeat.setVisibility(View.GONE);

        if(vehicle.getVehicleSpecs().getSmokingPreferences() == null || !(vehicle.getVehicleSpecs().getSmokingPreferences()))
        {
            //No Smoking Action
            noSmoking.setVisibility(View.VISIBLE);
            Smoking.setVisibility(View.GONE);
        }
        else
        {
            //Smoking Action
            noSmoking.setVisibility(View.GONE);
            Smoking.setVisibility(View.VISIBLE);
        }


    }



}