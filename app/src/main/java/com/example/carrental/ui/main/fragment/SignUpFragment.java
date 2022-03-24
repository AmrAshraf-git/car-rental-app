package com.example.carrental.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.carrental.R;
import com.example.carrental.repository.VehicleRepo;
import com.example.carrental.ui.main.VehicleViewModel;

public class SignUpFragment extends Fragment {

    private EditText fName;
    private EditText lName;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText confPassword;
    private Button signUp;
    private ProgressBar progressBar;
    private VehicleViewModel vehicleViewModel;
    private VehicleRepo vehicleRepo;


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

        //vehicleViewModel=new ViewModelProvider(this).get(VehicleViewModel.class);
        vehicleRepo=VehicleRepo.getInstance();



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fName.getText().toString().isEmpty())
                {
                    fName.requestFocus();
                    fName.setError("First name is required");
                }
                if(lName.getText().toString().isEmpty())
                {
                    lName.requestFocus();
                    lName.setError("Last name is required");
                }
                if(email.getText().toString().isEmpty())
                {
                    email.requestFocus();
                    email.setError("Email is required");
                }
                if(password.getText().toString().isEmpty())
                {
                    password.requestFocus();
                    password.setError("password name is required");
                }
                if(confPassword.getText().toString().isEmpty())
                {
                    confPassword.requestFocus();
                    confPassword.setError("Confirm password name is required");
                }
                if(phone.getText().toString().isEmpty())
                {
                    phone.requestFocus();
                    phone.setError("Phone name is required");
                }

                vehicleRepo.signUp("fName.getText().toString()",
                        "lName.getText().toString()","email.getText().toString()",
                        "password.getText().toString()","Password.getText().toString()",
                        123456789);
            }
        });


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