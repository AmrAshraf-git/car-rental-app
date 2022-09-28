package com.example.carrental.ui.main.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.R;
import com.example.carrental.model.ForgetPasswordResponse;
import com.example.carrental.ui.main.VehicleViewModel;
import com.example.carrental.utility.SessionManager;


public class RatingFragment extends Fragment {


    private static final String VEHICLE_ID = "vehicleID";
    private static final String COMPANY_ID = "companyID";

    private String mVehicleID;
    private String mCompanyID;
    private RatingBar vehicleRateBar;
    private RatingBar companyRateBar;
    private Button submitBtn;
    private Dialog dialog;
    private View view;
    private TextView dialogTitle;
    private TextView dialogSubTitle;
    private Button dialogBtn;
    private boolean isVehicleRateSuccess;
    private boolean isCompanyRateSuccess;

    private ProgressBar progressBar;
    private ForgetPasswordResponse mForgetPasswordResponse1;
    private ForgetPasswordResponse mForgetPasswordResponse2;
    private VehicleViewModel vehicleViewModel;

    public RatingFragment() {
        // Required empty public constructor
    }

    public static RatingFragment newInstance(String vehicleID, String companyID) {
        RatingFragment fragment = new RatingFragment();
        Bundle args = new Bundle();
        args.putString(VEHICLE_ID, vehicleID);
        args.putString(COMPANY_ID, companyID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVehicleID = getArguments().getString(VEHICLE_ID);
            mCompanyID = getArguments().getString(COMPANY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rating, container, false);
        vehicleRateBar = view.findViewById(R.id.rating_ratingBar_vehicle);
        companyRateBar = view.findViewById(R.id.rating_ratingBar_company);
        submitBtn = view.findViewById(R.id.rating_btn_submit);
        progressBar=view.findViewById(R.id.rating_progressBar);
        vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vehicleRateBar.getRating()>0&&companyRateBar.getRating()>0)
                    observeViewModel();
                else
                    Toast.makeText(getContext(), "Please enter a valid value", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void observeViewModel() {
        isVehicleRateSuccess=false;
        isCompanyRateSuccess=false;
        vehicleViewModel.updateVehicleRateRequest(SessionManager.getInstance(getContext()).getLoginSession().getId(), (int) vehicleRateBar.getRating(),mVehicleID);
        vehicleViewModel.updateCompanyRateRequest(SessionManager.getInstance(getContext()).getLoginSession().getId(), (int) companyRateBar.getRating(),mCompanyID);
        progressBar.setVisibility(View.VISIBLE);
        submitBtn.setEnabled(false);
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getUpdateCompanyRateResponse().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getUpdateVehicleRateResponse().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getUpdateVehicleRateResponse().observe(getViewLifecycleOwner(), new Observer<ForgetPasswordResponse>() {
            @Override
            public void onChanged(ForgetPasswordResponse forgetPasswordResponse) {
                //Log.e("resume1","onChanged");
                //Log.e("response",String.valueOf(vehicleResponse));
                //Log.e("mResponse",String.valueOf(mVehicleResponse));
                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && forgetPasswordResponse != mForgetPasswordResponse1) {
                    //Log.e("resume2","Lifecycle_RESUMED(if1)");
                    if (forgetPasswordResponse.getMessage() != null && forgetPasswordResponse.getMessage().equals("success")) {
                        //Log.e("resume3","if2");
                        //dialogSetup();
                        isVehicleRateSuccess=true;
                        if(isCompanyRateSuccess)
                            dialogSetup();
                    }
                    else {
                        Toast.makeText(getContext(), (forgetPasswordResponse.getMessage() != null ? forgetPasswordResponse.getMessage() : "Unknown response, please try again"), Toast.LENGTH_SHORT).show();
                        //Log.e("resume5","else1");
                    }
                }

                //getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                mForgetPasswordResponse1 = forgetPasswordResponse;
                //progressBar.setVisibility(View.GONE);
                //submitBtn.setEnabled(true);
                //Log.e("resume6","end of onChanged");
            }
        });

        vehicleViewModel.getUpdateCompanyRateResponse().observe(getViewLifecycleOwner(), new Observer<ForgetPasswordResponse>() {
            @Override
            public void onChanged(ForgetPasswordResponse forgetPasswordResponse) {
                Log.e("resume1","onChanged");
                //Log.e("response",String.valueOf(vehicleResponse));
                //Log.e("mResponse",String.valueOf(mVehicleResponse));
                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && forgetPasswordResponse != mForgetPasswordResponse2) {
                    //Log.e("resume2","Lifecycle_RESUMED(if1)");
                    if (forgetPasswordResponse.getMessage() != null && forgetPasswordResponse.getMessage().equals("success")) {
                        Log.e("resume3","if2");
                        //dialogSetup();
                        isCompanyRateSuccess=true;
                        if(isVehicleRateSuccess)
                            dialogSetup();
                    }
                    else {
                        Toast.makeText(getContext(), (forgetPasswordResponse.getMessage() != null ? forgetPasswordResponse.getMessage() : "Unknown response, please try again"), Toast.LENGTH_SHORT).show();
                        //Log.e("resume5","else1");
                    }
                }

                getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                mForgetPasswordResponse2 = forgetPasswordResponse;
                progressBar.setVisibility(View.GONE);
                submitBtn.setEnabled(true);
                //Log.e("resume6","end of onChanged");
            }
        });
    }

    private void dialogSetup() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_confirm_dialog);
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.ic_bar_rounded));
        dialog.setCancelable(false);
        dialogTitle = dialog.findViewById(R.id.confDialog_txtView_title);
        dialogSubTitle = dialog.findViewById(R.id.confDialog_txtView_subTitle);
        dialogBtn = dialog.findViewById(R.id.confDialog_btn_ok);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(getActivity()!=null)
                    getActivity().onBackPressed();
            }
        });
        dialogTitle.setText("Thanks!");
        dialogSubTitle.setText("Your review has been submitted successfully");
        dialog.show();
    }

/*
    private UpdateRate getRate() {
        UpdateRate updateRate =new UpdateRate();
        updateRate.setVehicleRate((int) vehicleRateBar.getRating());
        updateRate.setVehicleID(mVehicleID);
        return updateRate;
    }*/
}