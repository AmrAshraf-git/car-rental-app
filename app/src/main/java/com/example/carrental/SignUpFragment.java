package com.example.carrental;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class SignUpFragment extends Fragment {

    EditText fName;
    EditText lName;
    EditText email;
    EditText password;
    EditText confPassword;
    Button signUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        fName =view.findViewById(R.id.signUp_edTxt_fName);
        lName =view.findViewById(R.id.signUp_edTxt_lName);
        email =view.findViewById(R.id.signUp_edTxt_usrName);
        password=view.findViewById(R.id.signUp_edTxt_pswd);
        confPassword =view.findViewById(R.id.signUp_edTxt_cnfPswd);
        signUp=view.findViewById(R.id.signUp_btn_signUp);

/*
//=========================================Animations===============================================
        fName.setTranslationX(800);
        lName.setTranslationX(800);
        phone.setTranslationX(800);
        password.setTranslationX(800);
        confPassword.setTranslationX(800);
        signUp.setTranslationX(800);

        fName.setAlpha(0);
        lName.setAlpha(0);
        phone.setAlpha(0);
        password.setAlpha(0);
        confPassword.setAlpha(0);
        signUp.setAlpha(0);

        fName.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        lName.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        phone.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signUp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
*/
        return view;
    }

}