package com.example.carrental.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.carrental.R;
import com.example.carrental.getUserLocation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SignInFragment extends Fragment {
    private EditText email;
    private EditText password;
    private Button signIn;
    private FloatingActionButton floatingActionButton1;
    private FloatingActionButton floatingActionButton2;
    private FloatingActionButton floatingActionButton3;
    private TextView loginTitle;
    private FragmentOnClickListener fragmentOnClickListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentOnClickListener) {
            fragmentOnClickListener = (FragmentOnClickListener) context;
        }
        else
            throw new ClassCastException(context.toString() + "must implements FragmentOnClickListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentOnClickListener=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_sign_in, container, false);
        email =view.findViewById(R.id.signIn_edTxt_email);
        password=view.findViewById(R.id.signIn_edTxt_pswd);
        signIn=view.findViewById(R.id.signIn_btn_signIn);
        loginTitle=view.findViewById(R.id.signIn_txtView_title);
        floatingActionButton1=view.findViewById(R.id.signIn_floatBtn_1);
        floatingActionButton2=view.findViewById(R.id.signIn_floatBtn_2);
        floatingActionButton3=view.findViewById(R.id.signIn_floatBtn_3);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=String.valueOf(email.getText());
                //Log.e("frg",String.valueOf(fragmentOnClickListener));
                fragmentOnClickListener.onItemClick(s);

                String e=email.getText().toString();
                String p=password.getText().toString();
                if(e.equals("admin")&&p.equals("admin")) {
                    //Intent intent = new Intent(getActivity(), NavControllerActivity.class);
                    Intent intent=new Intent(getActivity(), getUserLocation.class);
                    intent.putExtra("user","admin");
                    startActivity(intent);
                }
                else{
                    email.setError("email or password isn't connected");
                }
            }
        });


        //====================================ANIMATIONS==========================================
        floatingActionButton1.setTranslationX(1000);
        floatingActionButton2.setTranslationX(1000);
        floatingActionButton3.setTranslationX(1000);

        //floatingActionButton1.setAlpha(0);
        //floatingActionButton2.setAlpha(0);
        //floatingActionButton3.setAlpha(0);

        floatingActionButton1.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(200).start();
        floatingActionButton2.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(400).start();
        floatingActionButton3.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(600).start();
        //====================================ANIMATIONS==========================================

        return view;
    }

    public interface FragmentOnClickListener {
        void onItemClick(String email);
    }
}