package com.example.carrental.ui.main.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.carrental.R;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.SignUpResponse;
import com.example.carrental.ui.main.VehicleViewModel;
import com.example.carrental.utility.TextValidation;

public class SignUpFragment extends Fragment {

    private EditText fName;
    private EditText lName;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText confPassword;
    private Button signUp;
    private Dialog dialog;
    private Button dialogBtn;
    private TextView dialogTitle;
    private TextView dialogSubTitle;
    private View view;
    private boolean mAlreadyLoaded;
    private SignUpResponse mSignUpResponse;
    private ProgressBar progressBar;
    private VehicleViewModel vehicleViewModel;
    //private SignUpViewModel signUpViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            mAlreadyLoaded = false;
        //signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState == null && !mAlreadyLoaded) {
            mAlreadyLoaded = true;

            view = inflater.inflate(R.layout.fragment_sign_up, container, false);
            fName = view.findViewById(R.id.signUp_edTxt_fName);
            lName = view.findViewById(R.id.signUp_edTxt_lName);
            email = view.findViewById(R.id.signUp_edTxt_email);
            phone = view.findViewById(R.id.signUp_edTxt_phone);
            password = view.findViewById(R.id.signUp_edTxt_pswd);
            confPassword = view.findViewById(R.id.signUp_edTxt_cnfPswd);
            signUp = view.findViewById(R.id.signUp_btn_signUp);
            progressBar = view.findViewById(R.id.signUP_progressBar);


            //vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextValidation.isFirstnameValid(fName) &&
                            TextValidation.isLastName(lName) && TextValidation.isEmailValid(email) &&
                            TextValidation.isPhoneValid(phone) &&
                            TextValidation.isPasswordValid(password) &&
                            TextValidation.isConfPasswordSimulate(password,confPassword)) {


                        vehicleViewModel.signUpRequest(createNewUser());
                        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                        vehicleViewModel.getSignUpResponse().removeObservers(getViewLifecycleOwner());
                        //vehicleViewModel.signUpRequest(createNewUser());
                        progressBar.setVisibility(View.VISIBLE);
                        signUp.setEnabled(false);

                        vehicleViewModel.getSignUpResponse().observe(getViewLifecycleOwner(), new Observer<SignUpResponse>() {
                            @Override
                            public void onChanged(SignUpResponse signUpResponse) {
                                //Log.e("resume2", "onChanged");
                                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && signUpResponse != mSignUpResponse) {
                                    //Log.e("resume3", "Lifecycle_RESUMED(if1)");
                                    if (signUpResponse.getMessage() != null && signUpResponse.getMessage().equals("success")) {
                                        //Her we can receive user information (newUser[]) as a SignUpResponse object
                                        //signUpResponse.getNewUser();
                                        //Toast.makeText(getContext(), "successfully registered", Toast.LENGTH_SHORT).show();
                                        //Log.e("resume5", "if2");
                                        dialogSetup();
                                    } else if (signUpResponse.getMessage() != null && signUpResponse.getMessage().equals("400")) {
                                        //Log.e("resume6", "else if1");
                                        Toast.makeText(getContext(), signUpResponse.getMessage(), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getContext(), (signUpResponse.getMessage() != null ? signUpResponse.getMessage() : "Unknown"), Toast.LENGTH_SHORT).show();
                                        //Log.e("resume7", "else1");
                                    }
                                }
                                //vehicleViewModel.getNewUserResponse().removeObservers(getViewLifecycleOwner());
                                getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                                //vehicleViewModel.getNewUserResponse().removeObservers(getViewLifecycleOwner());
                                //vehicleViewModel.getUserResponse().removeObserver(this);

                                progressBar.setVisibility(View.INVISIBLE);
                                signUp.setEnabled(true);
                                mSignUpResponse=signUpResponse;
                                //Log.e("resume8","end of onChanged");
                            }
                        });
                    }
                }
            });


            /**
             * MVVM old method
             signUp.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
            if (checkEmpty() && validateFname() && validateLname() && validateEmail() && validatePassword() && validateConfPassword()) {
            progressBar.setVisibility(View.VISIBLE);

            //vehicleViewModel.getNewUserResponse(createNewUser()).removeObservers(getViewLifecycleOwner());
            //if (!signUpViewModel.getNewUserResponse(createNewUser()).hasObservers()) {
            vehicleViewModel.getNewUserResponse(createNewUser()).observe(getViewLifecycleOwner(), new Observer<Response>() {
            @Override public void onChanged(Response response) {
            if(getViewLifecycleOwner().getLifecycle().getCurrentState()== Lifecycle.State.RESUMED) {
            if (response == null || response.code() != 200 || !response.isSuccessful()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "An error occurred, please try again" + "Message: " + (response != null ? response.message() : null) + "  " + "Code: " + (response != null ? response.code() : null), Toast.LENGTH_LONG).show();
            //signUpViewModel.getNewUserResponse(createNewUser()).removeObservers(getViewLifecycleOwner());
            } else if (response.code() == 200) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "sign-up successful " + "Message: " + (response != null ? response.message() : null) + "  " + "Code: " + (response != null ? response.code() : null), Toast.LENGTH_SHORT).show();
            //signUpViewModel.getNewUserResponse(createNewUser()).removeObservers(getViewLifecycleOwner());
            } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Unexpected error occurred " + "Message: " + (response != null ? response.message() : null) + "  " + "Code: " + (response != null ? response.code() : null), Toast.LENGTH_SHORT).show();
            //signUpViewModel.getNewUserResponse(createNewUser()).removeObservers(getViewLifecycleOwner());
            }
            }
            //Log.e("tst",response.toString());
            //Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
            });

            }
            }
            //}
            });*/


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

        }
        return view;

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
            }
        });
        dialogTitle.setText("Registration successful!");
        dialogSubTitle.setText("Please verify your E-mail first, to be able to login");
        dialog.show();
    }

    private NewUser createNewUser() {
        NewUser newUser = new NewUser();

        newUser.setFirstName(fName.getText().toString());
        newUser.setLastName(lName.getText().toString());
        newUser.setEmail(email.getText().toString());
        newUser.setPassword(password.getText().toString());
        newUser.setCpassword(confPassword.getText().toString());
        newUser.setPhone(Integer.parseInt(phone.getText().toString()));

        //Dummy Data New User

         /*newUser.setFirstName("Test3");
         newUser.setLastName("Test3");
         newUser.setEmail("test3@example.com");
         newUser.setPassword("password.getText()");
         newUser.setCpassword("password.getText()");
         newUser.setPhone(123456);*/

        return newUser;
        //vehicleViewModel.signUp(newUser);
    }


/**
 *POST via encoded field
 *
 /*
 Retrofit retrofit = new Retrofit.Builder().baseUrl(Credentials.BASE_URL)
 .addConverterFactory(GsonConverterFactory.create()).build();
 ApiService2 apiService2 = retrofit.create(ApiService2.class);
 Call<ResponseBody> call= apiService2.signUp(newUser);
 call.enqueue(new Callback<ResponseBody>() {
@Override public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
Log.e("onResponseError", String.valueOf(response.code()));
//                        Log.e("onResponseError", String.valueOf(response.message()));
//                        Log.e("onResponseError", String.valueOf(response.errorBody()));
Log.e("onResponseError", String.valueOf(response.raw()));
}

@Override public void onFailure(Call<ResponseBody> call, Throwable t) {

}
});

 //                vehicleRepo.signUp("fName.getText().toString()",
 //                        "lName.getText().toString()","email.getText().toString()",
 //                        "password.getText().toString()","Password.getText().toString()",
 //                        123456789);

 public void register(String fName,String lName,String email,String phone,String pass, String confPass){

 StringRequest stringRequest =new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
@Override public void onResponse(String response) {
Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();


}
}, new Response.ErrorListener() {
@Override public void onErrorResponse(VolleyError error) {
Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
}
}){
@Nullable
@Override protected Map<String, String> getParams() throws AuthFailureError {
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