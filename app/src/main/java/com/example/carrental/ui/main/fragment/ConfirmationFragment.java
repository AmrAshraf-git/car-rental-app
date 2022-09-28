package com.example.carrental.ui.main.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.R;
import com.example.carrental.constant.DateConverter;
import com.example.carrental.model.Booking;
import com.example.carrental.model.BookingResponse;
import com.example.carrental.ui.main.VehicleViewModel;
import com.example.carrental.utility.SessionManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ConfirmationFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    Bitmap vehicleImageAsBitmap;
    ImageView vehicleImageImgView;
    TextView vehicleModelTxtView;
    TextView vehiclePriceTxtView;

    CardView pickUpDateCard;
    CardView dropOffDateCard;

    TextView pickUpDayOfMonthTxtView;
    TextView pickUpDayOfWeekTxtView;
    TextView pickUpMonthTxtView;

    TextView dropOffDayOfMonthTxtView;
    TextView dropOffDayOfWeekTxtView;
    TextView dropOffMonthTxtView;

    String pickUpDate;
    String dropOffDate;

    Drawable deliveryArrow = null;
    Drawable returnArrow = null;
    Drawable edit = null;

    SwitchCompat pickUpLocationSwitch;
    SwitchCompat dropOffLocationSwitch;
    CardView pickUpLocationCard;
    CardView dropOffLocationCard;
    TextView pickUpLocationTxtView;
    TextView dropOffLocationTxtView;
    TextView companyNameTxtView;
    TextView companyAddressTxtView;
    TextView companyPhoneTxtView;
    RatingBar companyRateRB;
    ImageView mapCover;
    private static final int ERROR_DIALOG_REQUEST = 9001;


    Button sendRequestBtn;

    int dayOfWeekAsNumber;
    String dayOfWeek;
    String monthOfYear;

    String CustomAddressPick;
    String CustomAddressDrop;
    private boolean mAlreadyLoaded;
    View view;
    private VehicleViewModel vehicleViewModel;

    private boolean whichFocused = false; // (false -> Pick-up) , (true -> Drop-off)


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String VEHICLE_IMAGE = "imageKey";
    private static final String VEHICLE_MODEL = "modelKey";
    private static final String VEHICLE_ID = "idKey";
    private static final String VEHICLE_PRICE = "priceKey";
    private static final String COMPANY_NAME = "companyNameKey";
    private static final String COMPANY_ADDRESS = "companyAddressKey";
    private static final String COMPANY_RATE = "companyRateKey";
    private static final String COMPANY_LONGITUDE = "companyLongitudeKey";
    private static final String COMPANY_LATITUDE = "companyLatitudeKey";


    // TODO: Rename and change types of parameters
    private String vehicleImage;
    private String vehicleModel;
    private String vehicleId;
    private String vehiclePrice;
    private String companyName;
    private String companyAddress;
    private float companyRate;
    private String companyLongitude;
    private String companyLatitude;

    private String userPickupLoc;
    private String userDropLoc;


    public ConfirmationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ConfirmationFragment newInstance(String vehicleImage, String vehicleModel, @NonNull String vehicleId,
                                                   String vehiclePrice, String companyName, String companyAddress,
                                                   float companyRate, String longitude,
                                                   String latitude) {
        ConfirmationFragment fragment = new ConfirmationFragment();
        Bundle args = new Bundle();
        args.putString(VEHICLE_IMAGE, vehicleImage);
        args.putString(VEHICLE_MODEL, vehicleModel);
        args.putString(VEHICLE_ID, vehicleId);
        args.putString(VEHICLE_PRICE, vehiclePrice);
        args.putString(COMPANY_NAME, companyName);
        args.putString(COMPANY_ADDRESS, companyAddress);
        args.putString(COMPANY_LONGITUDE,longitude);
        args.putString(COMPANY_LATITUDE,latitude);
        args.putFloat(COMPANY_RATE, companyRate);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            mAlreadyLoaded = false;
        if (getArguments() != null) {
            vehicleImage = getArguments().getString(VEHICLE_IMAGE);
            vehicleModel = getArguments().getString(VEHICLE_MODEL);
            vehicleId = getArguments().getString(VEHICLE_ID);
            vehiclePrice = getArguments().getString(VEHICLE_PRICE);
            companyName = getArguments().getString(COMPANY_NAME);
            companyAddress = getArguments().getString(COMPANY_ADDRESS);
            companyRate = getArguments().getFloat(COMPANY_RATE);
            companyLongitude=getArguments().getString(COMPANY_LONGITUDE);
            companyLatitude=getArguments().getString(COMPANY_LATITUDE);

            userPickupLoc=companyAddress;
            userDropLoc=companyAddress;
        }
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                CustomAddressPick = bundle.getString("customAddressForPickKey");
                CustomAddressDrop = bundle.getString("customAddressForDropKey");

                if(CustomAddressPick != null && !CustomAddressPick.equals("".trim())){
                    pickUpLocationTxtView.setText(CustomAddressPick);
                    userPickupLoc=CustomAddressDrop;
                }

                if(CustomAddressDrop != null && !CustomAddressDrop.equals("".trim())){
                    dropOffLocationTxtView.setText(CustomAddressDrop);
                    userDropLoc=CustomAddressDrop;
                }

            }
        });

        deliveryArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_forward);
        returnArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back);
        edit = getResources().getDrawable(R.drawable.ic_baseline_edit);
        deliveryArrow.setTint(getResources().getColor(R.color.black));
        returnArrow.setTint(getResources().getColor(R.color.black));
        edit.setTint(getResources().getColor(R.color.dark_gray));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!mAlreadyLoaded) {
            mAlreadyLoaded = true;

        view = inflater.inflate(R.layout.fragment_confirmation, container, false);

        vehicleImageImgView = view.findViewById(R.id.confirmation_imgView_imageOfVehicle);
        vehicleModelTxtView = view.findViewById(R.id.confirmation_txtView_vehicleModel);
        vehiclePriceTxtView = view.findViewById(R.id.confirmation_txtView_vehiclePrice);

        pickUpDateCard = view.findViewById(R.id.confirmation_cardView_PickUp);
        dropOffDateCard = view.findViewById(R.id.confirmation_cardView_DropOff);
        pickUpDayOfMonthTxtView = view.findViewById(R.id.confirmation_txtView_pickUpDayOfMonth);

        pickUpDayOfWeekTxtView = view.findViewById(R.id.confirmation_txtView_pickUpDayOfWeek);
        pickUpMonthTxtView = view.findViewById(R.id.confirmation_txtView_pickUpMonth);
        dropOffDayOfMonthTxtView = view.findViewById(R.id.confirmation_txtView_dropOffDayOfMonth);

        dropOffDayOfWeekTxtView = view.findViewById(R.id.confirmation_txtView_dropOffDayOfWeek);
        dropOffMonthTxtView = view.findViewById(R.id.confirmation_txtView_dropOffMonth);
        pickUpLocationSwitch = view.findViewById(R.id.confirmation_switch_pickUp);

        dropOffLocationSwitch = view.findViewById(R.id.confirmation_switch_dropOff);
        pickUpLocationCard = view.findViewById(R.id.confirmation_cardView_PickUpLocation);
        dropOffLocationCard = view.findViewById(R.id.confirmation_cardView_dropOffLocation);

        companyNameTxtView = view.findViewById(R.id.confirmation_txtView_companyName);
        companyAddressTxtView = view.findViewById(R.id.confirmation_txtView_companyAddress);
        companyPhoneTxtView = view.findViewById(R.id.confirmation_txtView_companyPhoneNumber);

        companyRateRB = view.findViewById(R.id.confirmation_ratingBar_companyRating);
        mapCover = view.findViewById(R.id.confirmation_imgView_mapCover);
        sendRequestBtn = view.findViewById(R.id.confirmation_btn_sendRequest);
        vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);

        Picasso.get().load(vehicleImage).fit().centerInside().error(R.drawable.img_logo_test).into(vehicleImageImgView);
        pickUpLocationTxtView = view.findViewById(R.id.confirmation_txtView_pickUpLocation);
        dropOffLocationTxtView = view.findViewById(R.id.confirmation_txtView_dropOffLocation);
        vehicleModelTxtView.setText(vehicleModel);
        vehiclePriceTxtView.setText(vehiclePrice);
        companyNameTxtView.setText("Name: " + companyName);
        pickUpLocationTxtView.setHint(companyAddress);
        dropOffLocationTxtView.setHint(companyAddress);
        companyAddressTxtView.setText("Address: " + companyAddress);
        companyRateRB.setRating(companyRate);


        pickUpDateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichFocused = false;
                showDatePickerDialog();
            }
        });

        dropOffDateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichFocused = true;
                showDatePickerDialog();
            }
        });

        pickUpLocationTxtView.setCompoundDrawablesWithIntrinsicBounds(deliveryArrow, null, null, null);
        dropOffLocationTxtView.setCompoundDrawablesWithIntrinsicBounds(returnArrow, null, null, null);


        pickUpLocationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pickUpLocationTxtView.setHint("Pick-up Location");
                    pickUpLocationTxtView.setCompoundDrawablesWithIntrinsicBounds(deliveryArrow, null, edit, null);
                    pickUpLocationCard.setClickable(true);
                } else {
                    pickUpLocationTxtView.setText("");
                    pickUpLocationTxtView.setHint(companyAddress);
                    pickUpLocationTxtView.setCompoundDrawablesWithIntrinsicBounds(deliveryArrow, null, null, null);
                    pickUpLocationCard.setClickable(false);
                }
            }
        });

        dropOffLocationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dropOffLocationTxtView.setHint("Drop-off Location");
                    dropOffLocationTxtView.setCompoundDrawablesWithIntrinsicBounds(returnArrow, null, edit, null);
                    dropOffLocationCard.setClickable(true);
                } else {
                    dropOffLocationTxtView.setText("");
                    dropOffLocationTxtView.setHint(companyAddress);
                    dropOffLocationTxtView.setCompoundDrawablesWithIntrinsicBounds(returnArrow, null, null, null);
                    dropOffLocationCard.setClickable(false);
                }
            }
        });


        pickUpLocationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // This if statement NOT IMPORTANT, but added for more handling
                if (pickUpLocationSwitch.isChecked()) {
                    Fragment fragment = ChooseDifferentLocationFragment.newInstance("Pick");
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.navContent_frameLayout_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getContext(), "Please active the switch To set different address", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dropOffLocationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // This if statement NOT IMPORTANT, but added for more handling
                if (dropOffLocationSwitch.isChecked()) {
                    Fragment fragment = ChooseDifferentLocationFragment.newInstance("Drop");
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.navContent_frameLayout_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();


                } else {
                    Toast.makeText(getContext(), "Please active the switch To set different address", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Initial value for pickUpLocationCard and dropOffLocationCard
        pickUpLocationCard.setClickable(false);
        dropOffLocationCard.setClickable(false);


        mapCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(companyLongitude !=null && companyLatitude!=null){
                    if(Float.parseFloat(companyLongitude)==0 && Float.parseFloat(companyLatitude)==0){
                        Toast.makeText(getContext(), "Location is not available for this company", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (isServicesOK()) {
                            Fragment fragment = CompanyLocationOnMapFragment.newInstance(Double.parseDouble(companyLatitude),Double.parseDouble(companyLongitude));
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.navContent_frameLayout_container, fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    }
                }
            }
        });

        sendRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsValidDates(pickUpDate, dropOffDate))
                {
                    if (SessionManager.getInstance(getContext()).isLoggedIn()){
                        Confirmation();
                    }
                    else
                        Toast.makeText(getContext(), "You must login first", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Set Current Date for (Pick-up Date and Drop-off Date) as Initialize Date
        DateConverter dateConverter = new DateConverter(Calendar.getInstance().get(Calendar.DAY_OF_WEEK),
                Calendar.getInstance().get(Calendar.MONTH));
        dayOfWeek = dateConverter.getDayOfWeek();
        monthOfYear = dateConverter.getMonthOfYear();

        pickUpDate = dropOffDate = Calendar.getInstance().get(Calendar.YEAR) +
                "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) +
                "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        pickUpDayOfMonthTxtView.setText(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        pickUpMonthTxtView.setText(monthOfYear);
        pickUpDayOfWeekTxtView.setText(dayOfWeek);

        dropOffDayOfMonthTxtView.setText(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        dropOffMonthTxtView.setText(monthOfYear);
        dropOffDayOfWeekTxtView.setText(dayOfWeek);
        //Set Current Date for (Pick-up Date and Drop-off Date) as Initialize Date

        }
        return view;
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    public boolean IsValidDates(String start, String end) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            if (sdf.parse(start).before(sdf.parse(end))) {
                return true;
            } else {
                Toast.makeText(getContext(), "Rent Period is Not Valid", Toast.LENGTH_SHORT).show();
                return false;
            }

        } catch (Exception e) {
            Toast.makeText(getContext(), "Occurred an Error, Please Enter Rent Period Again", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    /*
    year: 2022, 2023, etc...
    dayOfMonth: 1,2,3,4,...,31

    monthOfYearAsNumber: 0 -> 11
    monthOfYear: January, February, etc...

    dayOfWeekAsNumber: 1 -> 7
    dayOfWeek: Sunday,Monday, etc...
     */
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYearAsNumber, int dayOfMonth) {

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, monthOfYearAsNumber, dayOfMonth);
        dayOfWeekAsNumber = gregorianCalendar.get(Calendar.DAY_OF_WEEK);

        DateConverter dateConverter = new DateConverter(dayOfWeekAsNumber, monthOfYearAsNumber);
        dayOfWeek = dateConverter.getDayOfWeek();
        monthOfYear = dateConverter.getMonthOfYear();

        if (!(whichFocused)) {
            // Pick-up is Focused
            monthOfYearAsNumber++;
            pickUpDate = year + "-" + monthOfYearAsNumber + "-" + dayOfMonth;
            pickUpDayOfMonthTxtView.setText(String.valueOf(dayOfMonth));
            pickUpDayOfWeekTxtView.setText(dayOfWeek);
            pickUpMonthTxtView.setText(monthOfYear);
        } else {
            // Drop-off is Focused
            monthOfYearAsNumber++;
            dropOffDate = year + "-" + monthOfYearAsNumber + "-" + dayOfMonth;
            dropOffDayOfMonthTxtView.setText(String.valueOf(dayOfMonth));
            dropOffDayOfWeekTxtView.setText(dayOfWeek);
            dropOffMonthTxtView.setText(monthOfYear);
        }

    }


    public boolean isServicesOK() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());
        if (available == ConnectionResult.SUCCESS) {
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(getContext(), "You Cannot Make Map Request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    private void Confirmation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmation");
        builder.setMessage("You can't undo this action, are you sure about renting this vehicle?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_baseline_check_circle_outline);
        builder.setPositiveButton("Send Request", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Booking booking = new Booking();
                booking.setVehicleID(vehicleId);
                booking.setPick_upLocation(userPickupLoc);
                booking.setReturn_Location(userDropLoc);
                //booking.setDateFrom(pickUpDate);
                booking.setDateFrom("2022-08-10");
                //booking.setDateTo(dropOffDate);
                booking.setDateTo("2022-08-11");
                //Log.e("ddd",userPickupLoc);
                //Log.e("ddd",userDropLoc);
                vehicleViewModel.bookingRequest(SessionManager.getInstance(getContext()).getLoginSession().getToken(), booking);
                vehicleViewModel.getBookingLiveDataResponse().observe(getViewLifecycleOwner(), new Observer<BookingResponse>() {
                    @Override
                    public void onChanged(BookingResponse bookingResponse) {
                        if (bookingResponse.getMessage() != null) {
                            Log.e("bookingResponse", bookingResponse.getMessage());
                        }
                    }
                });
                Toast.makeText(getContext(), "Your request has been sent.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }




}