package com.example.carrental.ui.main.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carrental.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;


public class CompanyLocationOnMapFragment extends Fragment implements OnMapReadyCallback, OnConnectionFailedListener {


    private static final float DEFAULT_ZOOM = 15f;
    private GoogleMap mMap;
    String Details, subCity, Governorate, City, Country;
    double radiusInMeters = 100.0;
    int strokeColor = 0xff005173;
    int shadeColor = 0x44005173;
    ImageView moreInfo;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionsGranted = false;

    // TODO: Rename parameter arguments, choose names that match
    private static final String LATITUDE = "latitudeKey";
    private static final String LONGITUDE = "longitudeKey";

    // TODO: Rename and change types of parameters
    private double latitudeOfCompany;
    private double longitudeOfCompany;

    public CompanyLocationOnMapFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CompanyLocationOnMapFragment newInstance(double latitudeOfCompany, double longitudeOfCompany) {
        CompanyLocationOnMapFragment fragment = new CompanyLocationOnMapFragment();
        Bundle args = new Bundle();
        args.putDouble(LATITUDE, latitudeOfCompany);
        args.putDouble(LONGITUDE, longitudeOfCompany);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            latitudeOfCompany = getArguments().getDouble(LATITUDE);
            longitudeOfCompany = getArguments().getDouble(LONGITUDE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_location_on_map, container, false);
        moreInfo = view.findViewById(R.id.map_imgView_moreInfo);

        getLocationPermission();


        return view;
    }

    public void Init() {
        if (mLocationPermissionsGranted) {
            Details = getMoreDetailsFromLatLng(latitudeOfCompany, longitudeOfCompany);
            moreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Full Address");
                    builder.setMessage(Details);
                    builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });

            SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                    .findFragmentById(R.id.map_fragment_map);
            mapFragment.getMapAsync(this);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle(Details);
        } else {
            getLocationPermission();
        }


    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        moveCamera(new LatLng(latitudeOfCompany, longitudeOfCompany), DEFAULT_ZOOM);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
    }


    private void moveCamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        CircleOptions circleOptions = new CircleOptions()
                .center(latLng)
                .radius(radiusInMeters)
                .fillColor(shadeColor)
                .strokeColor(strokeColor)
                .strokeWidth(2);
        mMap.addCircle(circleOptions);


    }

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

    public void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(getContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if(ContextCompat.checkSelfPermission(getContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                Init();

            } else {
                ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
                Init();
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
            Init();

        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

}