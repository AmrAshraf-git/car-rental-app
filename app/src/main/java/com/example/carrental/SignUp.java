package com.example.carrental;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class SignUp extends Fragment {

    EditText fName;
    EditText lName;
    EditText phone;
    EditText password;
    EditText confPassword;
    Button signUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        fName =view.findViewById(R.id.firstname_user_edittxt);
        lName =view.findViewById(R.id.lastname_user_edittxt);
        phone=view.findViewById(R.id.username_user_edittxt);
        password=view.findViewById(R.id.password_user_edittxt);
        confPassword =view.findViewById(R.id.cfpassword_user_edittxt);
        signUp=view.findViewById(R.id.signup_user_btn);

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