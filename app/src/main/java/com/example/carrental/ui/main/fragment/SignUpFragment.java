package com.example.carrental.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.example.carrental.R;

public class SignUpFragment extends Fragment {

    EditText fName;
    EditText lName;
    EditText email;
    EditText phone;
    EditText password;
    EditText confPassword;
    Button signUp;
    ProgressBar progressBar;
    final String URL="https://car-rental-eg.herokuapp.com/singup";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        fName =view.findViewById(R.id.signUp_edTxt_fName);
        lName =view.findViewById(R.id.signUp_edTxt_lName);
        email =view.findViewById(R.id.signUp_edTxt_email);
        phone = view.findViewById(R.id.signUp_edTxt_phone);
        password=view.findViewById(R.id.signUp_edTxt_pswd);
        confPassword =view.findViewById(R.id.signUp_edTxt_cnfPswd);
        signUp=view.findViewById(R.id.signUp_btn_signUp);
        progressBar= view.findViewById(R.id.signUP_progressBar);




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
/*
    public void register(String fName,String lName,String email,String phone,String pass, String confPass){

        StringRequest stringRequest =new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<String,String>();
                map.put("fName",fName);
                map.put("lName",lName);
                map.put("email",email);
                map.put("phone",phone);
                map.put("password",pass);
                map.put("confPassword",confPass);

                return map;
            }
        };

    }*/

}