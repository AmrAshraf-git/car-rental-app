package com.example.carrental;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SignIn extends Fragment {
    EditText phone;
    EditText password;
    Button signIn;
    FloatingActionButton floatingActionButton1;
    FloatingActionButton floatingActionButton2;
    FloatingActionButton floatingActionButton3;
    TextView loginTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_sign_in, container, false);
        phone=view.findViewById(R.id.signIn_edTxt_usrName);
        password=view.findViewById(R.id.signIn_edTxt_pswd);
        signIn=view.findViewById(R.id.signIn_btn_signIn);
        loginTitle=view.findViewById(R.id.signIn_txtView_title);
        floatingActionButton1=view.findViewById(R.id.signIn_floatBtn_1);
        floatingActionButton2=view.findViewById(R.id.signIn_floatBtn_3);
        floatingActionButton3=view.findViewById(R.id.signIn_floatBtn_2);

/*
//=========================================Animations===============================================

        phone.setTranslationX(800);
        password.setTranslationX(800);
        signIn.setTranslationX(800);

        phone.setAlpha(0);
        password.setAlpha(0);
        signIn.setAlpha(0);

        phone.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signIn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
*/
        return view;
    }
}