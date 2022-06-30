package com.example.carrental.ui.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carrental.R;
import com.example.carrental.model.BookingHistory;

public class HistoryDetailsFragment extends Fragment {

    private static final String HISTORY_LIST_ITEM = "historyObject";
    private BookingHistory mBookingHistory;
    private View view;
    private boolean mAlreadyLoaded;
    private TextView requestID;
    private TextView vehicleID;
    private TextView companyID;
    private TextView bookedDate;
    private TextView deliveredDate;
    private TextView pickupLoc;
    private TextView returnLoc;
    private TextView requestStatus;

    public HistoryDetailsFragment() {
        // Required empty public constructor
    }

    public static HistoryDetailsFragment newInstance(BookingHistory bookingHistory) {
        HistoryDetailsFragment fragment = new HistoryDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(HISTORY_LIST_ITEM, bookingHistory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBookingHistory = getArguments().getParcelable(HISTORY_LIST_ITEM);
        }
        if (savedInstanceState == null)
            mAlreadyLoaded = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!mAlreadyLoaded) {
            mAlreadyLoaded = true;
            view = inflater.inflate(R.layout.fragment_history_details, container, false);

            requestID = view.findViewById(R.id.historyDetails_txtView_requestID);
            vehicleID = view.findViewById(R.id.historyDetails_txtView_vehicleID);
            companyID = view.findViewById(R.id.historyDetails_txtView_companyID);
            bookedDate = view.findViewById(R.id.historyDetails_txtView_bookedDate);
            deliveredDate = view.findViewById(R.id.historyDetails_txtView_deliveredDate);
            pickupLoc=view.findViewById(R.id.historyDetails_txtView_pickupLoc);
            returnLoc=view.findViewById(R.id.historyDetails_txtView_returnLoc);
            requestStatus = view.findViewById(R.id.historyDetails_txtView_status);

            requestID.setText(String.format("Request ID: %s", mBookingHistory.get_id()));
            vehicleID.setText(String.format("Vehicle ID: %s", mBookingHistory.getVehicleID().get_id()));
            companyID.setText(String.format("Company ID: %s", mBookingHistory.getCompanyID()));
            bookedDate.setText(String.format("Booked Date: %s",mBookingHistory.getDateFrom()));
            deliveredDate.setText(String.format("Delivered Date: %s",mBookingHistory.getDateTo()));
            pickupLoc.setText(String.format("Pickup Location: %s",mBookingHistory.getPick_upLocation()));
            returnLoc.setText(String.format("Return Location: %s",mBookingHistory.getReturn_Location()));
            requestStatus.setText(String.format("Status: %s",String.valueOf(mBookingHistory.getStatus())));

        }
        return view;
    }
}