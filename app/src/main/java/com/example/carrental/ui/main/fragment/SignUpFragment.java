package com.example.carrental.ui.main.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.carrental.R;
import com.example.carrental.constant.Credentials;
import com.example.carrental.data.ApiClient;
import com.example.carrental.data.ApiService;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.SignUpResponse;
import com.example.carrental.repository.VehicleRepo;
import com.example.carrental.ui.main.VehicleViewModel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        fName = view.findViewById(R.id.signUp_edTxt_fName);
        lName = view.findViewById(R.id.signUp_edTxt_lName);
        email = view.findViewById(R.id.signUp_edTxt_email);
        phone = view.findViewById(R.id.signUp_edTxt_phone);
        password = view.findViewById(R.id.signUp_edTxt_pswd);
        confPassword = view.findViewById(R.id.signUp_edTxt_cnfPswd);
        signUp = view.findViewById(R.id.signUp_btn_signUp);
        progressBar = view.findViewById(R.id.signUP_progressBar);


        vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty() && validateFname() && validateLname() && validateEmail() && validatePassword() && validateConfPassword()) {
                    createNewUser();


                    vehicleViewModel.getNewUserResponse().observe(getViewLifecycleOwner(), new Observer<ResponseBody>() {
                        @Override
                        public void onChanged(ResponseBody responseBody) {
                            Log.e("getNewUserResponse", String.valueOf(responseBody));
                            if (responseBody == null)
                                Toast.makeText(getContext(), "An error occurred, check your inputs", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });


/**
 * Animations
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


    private void createNewUser() {
        NewUser newUser = new NewUser();
        newUser.setFirstName(fName.getText().toString());
        newUser.setLastName(lName.getText().toString());
        newUser.setEmail(email.getText().toString());
        newUser.setPassword(password.getText().toString());
        newUser.setCpassword(confPassword.getText().toString());
        newUser.setPhone(Integer.parseInt(phone.getText().toString()));
        /**
         * Dummy Data New User
         NewUser newUser=new NewUser();
         newUser.setFirstName("fName.getText().toString()");
         newUser.setLastName("lName.getText().toString()");
         newUser.setEmail("test2@example.com");
         newUser.setPassword("password.getText()");
         newUser.setCpassword("cpassword.getText()");
         newUser.setPhone(123456789);
         */
        vehicleViewModel.signUp(newUser);
    }

    //====================================VALIDATION=====================================
    private boolean checkEmpty() {
        if (fName.getText().toString().isEmpty()) {
            fName.requestFocus();
            fName.setError("First name is required");
            return false;

        } else if (lName.getText().toString().isEmpty()) {
            lName.requestFocus();
            lName.setError("Last name is required");
            return false;
        } else if (email.getText().toString().isEmpty()) {
            email.requestFocus();
            email.setError("Email is required");
            return false;
        } else if (password.getText().toString().isEmpty()) {
            password.requestFocus();
            password.setError("password name is required");
            return false;
        } else if (confPassword.getText().toString().isEmpty()) {
            confPassword.requestFocus();
            confPassword.setError("Confirm password name is required");
            return false;
        } else if (phone.getText().toString().isEmpty()) {
            phone.requestFocus();
            phone.setError("Phone name is required");
            return false;
        } else
            return true;
    }

    private boolean validateFname() {
        String regVal = "^[a-zA-Z]{3,}[\\s]?[a-zA-Z]*[\\s]?[a-zA-Z]*$";               // Ends with at least one char
        if (!fName.getText().toString().matches(regVal)) {
            fName.setError("Invalid name");
            fName.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateLname() {
        String regVal ="^[a-zA-Z]{3,}[\\s]?[a-zA-Z]*[\\s]?[a-zA-Z]*$";
                /*
                "[a-zA-Z]*" +       // Begin with chars
                "[\\s]{1}" +        // Only one space
                "[a-zA-Z].*";       // Ends with at least one char
                */

        if (!lName.getText().toString().matches(regVal)) {
            lName.setError("Invalid name");
            lName.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String regVal = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.getText().toString().matches(regVal)) {
            email.setError("Invalid email");
            email.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        String regVal = "^" +
                "(?=.*[0-9])" +             // At least one digit
                "(?=.*[a-z])" +             // At least one lowe case
                "(?=.*[A-Z])" +             // At least one upper case
                "(?=.*[@#$%^&+=])" +        // At least one special character
                //"(?=\\S+$)" +               // No white space
                ".{8,}" +                   //  At least eight places
                "$";
        if (!password.getText().toString().matches(regVal)) {
            password.setError("Password is too weak");
            password.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateConfPassword() {
        if (!confPassword.getText().toString().equals(password.getText().toString())) {
            confPassword.setError("Password doesn't match");
            confPassword.requestFocus();
            return false;
        }
        return true;
    }
//====================================VALIDATION=====================================

/**
 *POST via encoded field
 *
 /*
 Retrofit retrofit = new Retrofit.Builder().baseUrl(Credentials.BASE_URL)
 .addConverterFactory(GsonConverterFactory.create()).build();
 ApiService2 apiService2 = retrofit.create(ApiService2.class);
 Call<ResponseBody> call= apiService2.signUp(newUser);
 call.enqueue(new Callback<ResponseBody>() {
@Override
public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
Log.e("onResponseError", String.valueOf(response.code()));
//                        Log.e("onResponseError", String.valueOf(response.message()));
//                        Log.e("onResponseError", String.valueOf(response.errorBody()));
Log.e("onResponseError", String.valueOf(response.raw()));
}

@Override
public void onFailure(Call<ResponseBody> call, Throwable t) {

}
});

 //                vehicleRepo.signUp("fName.getText().toString()",
 //                        "lName.getText().toString()","email.getText().toString()",
 //                        "password.getText().toString()","Password.getText().toString()",
 //                        123456789);

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