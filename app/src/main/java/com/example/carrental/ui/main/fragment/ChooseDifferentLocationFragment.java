package com.example.carrental.ui.main.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.R;
import com.example.carrental.ui.main.NavControllerActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;


public class ChooseDifferentLocationFragment extends Fragment {


    EditText customAddressFiled;
    Button useCustomLocation;
    Button useCurrentLocation;
    TextView currentLocation;
    String customAddressForPick;
    String customAddressForDrop;
    Bundle result;
    String Details, subCity, Governorate, City, Country, fullAddress;
    double Latitude, Longitude;
    FusedLocationProviderClient mFusedLocationClient = null;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionsGranted = false;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FLAG = "flag";

    // TODO: Rename and change types of parameters
    private String valueOfFlag;

    public ChooseDifferentLocationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChooseDifferentLocationFragment newInstance(String flag) {
        ChooseDifferentLocationFragment fragment = new ChooseDifferentLocationFragment();
        Bundle args = new Bundle();
        args.putString(FLAG, flag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            valueOfFlag = getArguments().getString(FLAG);
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_different_location, container, false);

        customAddressFiled = view.findViewById(R.id.chooseDiffLocation_EditText_addressManually);
        useCustomLocation = view.findViewById(R.id.chooseDiffLocation_btn_useCustomLocation);
        useCurrentLocation = view.findViewById(R.id.chooseDiffLocation_btn_useCurrentLocation);
        currentLocation = view.findViewById(R.id.chooseDiffLocation_TextView_location);

        useCustomLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAddressForPick = customAddressFiled.getText().toString().trim();
                if (customAddressForPick.equals("")) {
                    Toast.makeText(getContext(), "Field is Empty", Toast.LENGTH_SHORT).show();
                } else {

                    result = new Bundle();

                    if (valueOfFlag.equals("Pick")) {
                        customAddressForPick = customAddressFiled.getText().toString();
                        result.putString("customAddressForPickKey", customAddressForPick);
                    } else {
                        customAddressForDrop = customAddressFiled.getText().toString();
                        result.putString("customAddressForDropKey", customAddressForDrop);
                    }
                    getParentFragmentManager().setFragmentResult("requestKey", result);
                    HideSoftKeyboard();
                    getActivity().onBackPressed();

                }
            }
        });

        useCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocationPermission();
                getLastLocation();
            }
        });

        return view;
    }

    private void HideSoftKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

    }


    public void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(getContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                getLastLocation();
            } else {
                ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);

        }
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation() {
        if (mLocationPermissionsGranted) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            Latitude = location.getLatitude();
                            Longitude = location.getLongitude();
                            fullAddress = getMoreDetailsFromLatLng(Latitude, Longitude);
                            currentLocation.setText(fullAddress);
                            ActiveContinueBtn();
                        }
                    }
                });
            } else {
                Toast.makeText(getContext(), "Please Turn On Your Location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            getMoreDetailsFromLatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }
    };

    public String getMoreDetailsFromLatLng(double Latitude, double Longitude) {
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> addresses = null;
            addresses = geocoder.getFromLocation(Latitude, Longitude, 1);

            subCity = addresses.get(0).getLocality();
            City = addresses.get(0).getSubAdminArea();
            Governorate = addresses.get(0).getAdminArea();
            Country = addresses.get(0).getCountryName();
            Details = subCity + ", " + City + ", " + Governorate + ", " + Country;
            return Details;


        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown";
        }

    }
    private void ActiveContinueBtn() {
        useCurrentLocation.setText("Continue");
        useCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result = new Bundle();

                if (valueOfFlag.equals("Pick")) {
                    customAddressForPick = fullAddress;
                    result.putString("customAddressForPickKey", customAddressForPick);
                } else {
                    customAddressForDrop = fullAddress;
                    result.putString("customAddressForDropKey", customAddressForDrop);
                }
                getParentFragmentManager().setFragmentResult("requestKey", result);
                HideSoftKeyboard();
                getActivity().onBackPressed();


            }
        });
    }

}