package com.example.carrental.ui.main.fragment.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carrental.R;
import com.example.carrental.utility.SessionManager;

public class ProfileFragment extends Fragment {

    private View view;
    private TextView fName;
    private TextView lName;
    private TextView email;
    private TextView phone;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_profile, container, false);
        fName =view.findViewById(R.id.profile_txtView_fName);
        lName =view.findViewById(R.id.profile_txtView_lName);
        email =view.findViewById(R.id.profile_txtView_email);
        phone =view.findViewById(R.id.profile_txtView_phone);
        if (SessionManager.getInstance(getContext()).isLoggedIn()) {
            fName.setText(String.format("%s", SessionManager.getInstance(getContext()).getLoginSession().getFirstName()));
            lName.setText(String.format("%s", SessionManager.getInstance(getContext()).getLoginSession().getLastName()));
            email.setText(String.format("%s", SessionManager.getInstance(getContext()).getLoginSession().getEmail()));
            phone.setText(String.format("%d", SessionManager.getInstance(getContext()).getLoginSession().getPhone()));
        }
        return view;
    }
}