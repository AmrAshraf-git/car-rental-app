package com.example.carrental.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.R;
import com.google.android.gms.location.FusedLocationProviderClient;


import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;


public class UserLocationActivity extends AppCompatActivity {

    Button getMyLocation;
    TextView UserLocation;
    FusedLocationProviderClient mFusedLocationClient;
    double Latitude,Longitude;
    Drawable icon = null;
    int PERMISSION_ID = 44;
    String Details, subCity, Governorate, City, Country, fullAddress;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionsGranted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);

        icon = getResources().getDrawable(R.drawable.ic_baseline_location_on);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        UserLocation = findViewById(R.id.getLocation_txtView_location);

        getMyLocation = findViewById(R.id.getLocation_btn_get_my_location);
        getMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocationPermission();
                getLastLocation();
                fullAddress = getMoreDetailsFromLatLng(Latitude,Longitude);
                UserLocation.setText(fullAddress);
                UserLocation.setCompoundDrawablesWithIntrinsicBounds(icon,null,null,null);
                ActiveContinueBtn();

            }
        });

    }

    @SuppressLint("MissingPermission")
    public void getLastLocation() {
        getLocationPermission();
        Log.e("f",mLocationPermissionsGranted+"");

        // check if permissions are given
        if(mLocationPermissionsGranted) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            Latitude = location.getLatitude();
                            Longitude = location.getLongitude();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        }
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
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }


    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            getMoreDetailsFromLatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
        }
    };

    public void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.e("A","A");

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                Log.e("B","B");


            } else {
                Log.e("C","C");
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            Log.e("D","D");
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);

        }
    }


/*
    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }
*/


    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getLocationPermission();
        if (mLocationPermissionsGranted) {
            getLastLocation();
        }
    }

    public String getMoreDetailsFromLatLng(double Latitude, double Longitude) {
        try {
            Geocoder geocoder = new Geocoder(this);
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


    private void ActiveContinueBtn()
    {
        getMyLocation.setText("Continue");
        getMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserLocationActivity.this, NavControllerActivity.class);
                startActivity(intent);
            }
        });
    }


    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

}