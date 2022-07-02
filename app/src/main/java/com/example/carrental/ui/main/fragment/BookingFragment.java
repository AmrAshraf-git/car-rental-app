package com.example.carrental.ui.main.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.RatingBar;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.carrental.model.Vehicle;
import com.example.carrental.R;
import com.example.carrental.ui.main.VehicleViewModel;
import com.example.carrental.utility.adapter.SliderAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;


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
    private VehicleViewModel vehicleViewModel;
    FragmentManager fragmentManager;
    private Button bookNow;
    private Vehicle vehicle;
    private TextView AwayFromU;
    double theta,dist;
    double ValueOFDistanceBetweenUserAndCompany;
    double Latitude, Longitude;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    DecimalFormat deciFormat;

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
        } else throw new InvalidParameterException("Incompatible argument");
        deciFormat = new DecimalFormat();
        deciFormat.setMaximumFractionDigits(1);
    }

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        //1 ->  Brand Image
        vehicleModel = view.findViewById(R.id.bookingActivity_txtView_vehicleModel);
        companyName = view.findViewById(R.id.bookingActivity_txtView_companyName);
        companyAddress = view.findViewById(R.id.bookingActivity_txtView_companyAddress);
        CompRate = view.findViewById(R.id.bookingActivity_ratingBar_companyRating);
        vehicleImg = view.findViewById(R.id.bookingActivity_imageSlider_imagesOfvehicle);
        vehicleColor = view.findViewById(R.id.bookingActivity_txtView_color);
        doorsNum = view.findViewById(R.id.bookingActivity_txtView_doorsNumber);
        seatingCapacity = view.findViewById(R.id.bookingActivity_txtView_chairsNumber);
        vehicleRate = view.findViewById(R.id.bookingActivity_ratingBar_vehicleRating);
        price = view.findViewById(R.id.bookingActivity_txtView_pricePerHour);
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
        automaticTransmission = view.findViewById(R.id.bookingActivity_txtView_engineType);
        extraTyre = view.findViewById(R.id.bookingActivity_txtView_extraTyre);
        charger = view.findViewById(R.id.bookingActivity_txtView_charger);
        fireExtinguisher = view.findViewById(R.id.bookingActivity_txtView_fireExtinguisher);
        firstAidKit = view.findViewById(R.id.bookingActivity_txtView_firstAidKit);
        carSeat = view.findViewById(R.id.bookingActivity_txtView_carSeat);
        noSmoking = view.findViewById(R.id.bookingActivity_txtView_no_smoking);
        Smoking = view.findViewById(R.id.bookingActivity_txtView_smoking);
        CC = view.findViewById(R.id.bookingActivity_txtView_enginePerformace);
        bookNow = view.findViewById(R.id.bookingActivity_btn_bookNow);
        AwayFromU = view.findViewById(R.id.bookingActivity_txtView_AwayFromU);

        getLocation();
        if((Latitude == 0 && Longitude == 0) ||
                (Double.parseDouble(vehicle.getCompanyLatitude()) == 0
                        && Double.parseDouble(vehicle.getCompanyLongitude()) == 0)) {
            AwayFromU.setText("Unknown");
        }
        else{

            ValueOFDistanceBetweenUserAndCompany = getDistanceBetweenUserAndCompany(Latitude,Longitude,
                    Double.parseDouble(vehicle.getCompanyLatitude()),Double.parseDouble(vehicle.getCompanyLongitude()));

            AwayFromU.setText(deciFormat.format(ValueOFDistanceBetweenUserAndCompany)+" Km away from you");
        }


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


        // TODO: Get data from 'Vehicle' and pass them into their proper view
        //1 ->  Brand Image
        vehicleModel.setText(vehicle.getVehicleModel());
        companyName.setText(vehicle.getCompanyName());
        companyAddress.setText(vehicle.getCompanyAddress());
        CompRate.setRating(vehicle.getCompRate());
        vehicleColor.setText(vehicle.getVehicleColor());
        doorsNum.setText(String.valueOf(vehicle.getDoorsNum()));
        seatingCapacity.setText(String.valueOf(vehicle.getSeatingCapacity()));
        vehicleRate.setRating(vehicle.getVehicleRate());
        price.setText(String.valueOf(vehicle.getPrice()) + " " + vehicle.getPriceLabel());
        automaticTransmission.setText(vehicle.getAutomaticTransmission() ? "Automatic" : "Manual");
        CC.setText("CC: " + String.valueOf(vehicle.getCC()));
        //carImage.setImageResource(homeListItem.getCarImg());


        //==================Image Slider Show=============================
        /*String[] images = {vehicle.getVehicleImgURL()[0],
                vehicle.getVehicleImgURL()[1],
                vehicle.getVehicleImgURL()[2]};*/
        String[] images = new String[vehicle.getVehicleImgURL().length];
        for (int i = 0; i < images.length; i++) {
            images[i] = vehicle.getVehicleImgURL()[i];
        }

        //==================Image Slider Show=============================


        //==================Image Slider Show=============================
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        vehicleImg.setSliderAdapter(sliderAdapter);
        vehicleImg.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        vehicleImg.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        vehicleImg.setAutoCycle(false);
        //==================Image Slider Show=============================

        setSpecs();

        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Transfer Required Data from Booking fragment to Confirmation fragment
                Fragment fragment = ConfirmationFragment.newInstance(vehicle.getVehicleImgURL()[0],
                        vehicle.getVehicleModel(), vehicle.get_id(),
                        vehicle.getPrice() + " " + vehicle.getPriceLabel(),
                        vehicle.getCompanyName(), vehicle.getCompanyAddress(), vehicle.getCompRate(),
                        vehicle.getCompanyLongitude(), vehicle.getCompanyLatitude());

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.navContent_frameLayout_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        setSpecs();

        return view;
    }

    private void setSpecs() {

        if (vehicle.getAirBag() == null || !(vehicle.getAirBag()))
            airBag.setVisibility(View.GONE);

        if (vehicle.getSeatBelts() == null || !(vehicle.getSeatBelts()))
            seatBelts.setVisibility(View.GONE);

        if (vehicle.getABS() == null || !(vehicle.getABS()))
            ABS.setVisibility(View.GONE);

        if (vehicle.getSunRoof() == null || !(vehicle.getSunRoof()))
            sunRoof.setVisibility(View.GONE);

        if (vehicle.getParkingSensors() == null || !(vehicle.getParkingSensors()))
            parkingSensors.setVisibility(View.GONE);

        if (vehicle.getRadio() == null || !(vehicle.getRadio()))
            radio.setVisibility(View.GONE);

        if (vehicle.getBluetooth() == null || !(vehicle.getBluetooth()))
            bluetooth.setVisibility(View.GONE);

        if (vehicle.getNavSystem() == null || !(vehicle.getNavSystem()))
            navSystem.setVisibility(View.GONE);

        if (vehicle.getRemoteStart() == null || !(vehicle.getRemoteStart()))
            remoteStart.setVisibility(View.GONE);

        if (vehicle.getAC() == null || !(vehicle.getAC()))
            AC.setVisibility(View.GONE);

        if (vehicle.getMusicPlayer() == null || !(vehicle.getMusicPlayer()))
            musicPlayer.setVisibility(View.GONE);

        if (vehicle.getExtraTyre() == null || !(vehicle.getExtraTyre()))
            extraTyre.setVisibility(View.GONE);

        if (vehicle.getCharger() == null || !(vehicle.getCharger()))
            charger.setVisibility(View.GONE);

        if (vehicle.getFireExtinguisher() == null || !(vehicle.getFireExtinguisher()))
            fireExtinguisher.setVisibility(View.GONE);

        if (vehicle.getFirstAidKit() == null || !(vehicle.getFirstAidKit()))
            firstAidKit.setVisibility(View.GONE);

        if (vehicle.getCarSeat() == null || !(vehicle.getCarSeat()))
            carSeat.setVisibility(View.GONE);

        if (vehicle.getSmokingPreferences() == null || !(vehicle.getSmokingPreferences())) {
            //No Smoking Action
            noSmoking.setVisibility(View.VISIBLE);
            Smoking.setVisibility(View.GONE);
        } else {
            //Smoking Action
            noSmoking.setVisibility(View.GONE);
            Smoking.setVisibility(View.VISIBLE);
        }

    }
    public void getLocation(){
        if (ContextCompat.checkSelfPermission(getContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Longitude = location.getLongitude();
                Latitude = location.getLatitude();
                final LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        Longitude = location.getLongitude();
                        Latitude = location.getLatitude();
                    }
                };
            } else {
                return;
            }
        } else {
            return;
        }
    }

    private double getDistanceBetweenUserAndCompany(double lat1, double lon1, double lat2, double lon2) {
        theta = lon1 - lon2;
        dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}