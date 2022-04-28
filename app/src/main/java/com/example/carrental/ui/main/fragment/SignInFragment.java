package com.example.carrental.ui.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.carrental.R;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.SignInResponse;
import com.example.carrental.model.User;
import com.example.carrental.ui.main.UserLocationActivity;
import com.example.carrental.ui.main.VehicleViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SignInFragment extends Fragment {
    private EditText email;
    private EditText password;
    private Button signIn;
    private FloatingActionButton floatingActionButton1;
    private FloatingActionButton floatingActionButton2;
    private FloatingActionButton floatingActionButton3;
    private ProgressBar progressBar;
    private TextView loginTitle;
    private View view;
    private boolean mAlreadyLoaded;
    private FragmentOnClickListener fragmentOnClickListener;
    private VehicleViewModel vehicleViewModel;
    private SignInResponse mSignInResponse;


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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null)
            mAlreadyLoaded=false;
        vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState==null && !mAlreadyLoaded) {
            mAlreadyLoaded = true;
            view = inflater.inflate(R.layout.fragment_sign_in, container, false);
            email = view.findViewById(R.id.signIn_edTxt_email);
            password = view.findViewById(R.id.signIn_edTxt_pswd);
            signIn = view.findViewById(R.id.signIn_btn_signIn);
            loginTitle = view.findViewById(R.id.signIn_txtView_title);
            floatingActionButton1 = view.findViewById(R.id.signIn_floatBtn_1);
            floatingActionButton2 = view.findViewById(R.id.signIn_floatBtn_2);
            floatingActionButton3 = view.findViewById(R.id.signIn_floatBtn_3);
            progressBar = view.findViewById(R.id.signIn_progressBar);




            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkEmpty() &&  validateEmail()) {
                        vehicleViewModel.getUserResponse().removeObservers(getViewLifecycleOwner());
                        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());

                        progressBar.setVisibility(View.VISIBLE);
                        signIn.setEnabled(false);
                        vehicleViewModel.login(getUser());
                        Log.e("resume1","onClick");
                        vehicleViewModel.getUserResponse().observe(getViewLifecycleOwner(), new Observer<SignInResponse>() {
                            @Override
                            public void onChanged(SignInResponse signInResponse) {
                                Log.e("resume2","onChanged");
                                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && signInResponse !=mSignInResponse) {
                                    Log.e("resume3","Lifecycle_RESUMED");
                                    if (signInResponse.getMessage()!=null && signInResponse.getMessage().equals("done")) {
                                        email.setError(null);
                                        Toast.makeText(getContext(), "sign-up successful " + "Message: " +  signInResponse.getToken() , Toast.LENGTH_SHORT).show();
                                        Log.e("resume4","if1");
                                    }
                                    else if (signInResponse.getMessage()!=null && signInResponse.getMessage().equals("400")){
                                        email.setError("Invalid email or password");
                                        Log.e("resume4","if2");
                                    }
                                    else {
                                        email.setError(null);
                                        Toast.makeText(getContext(), (signInResponse.getMessage() != null ? signInResponse.getMessage() : "Unknown"), Toast.LENGTH_SHORT).show();
                                        Log.e("resume4","if3");
                                    }
                                }
                                //vehicleViewModel.getUserResponse().removeObservers(getViewLifecycleOwner());
                                getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                                //vehicleViewModel.getUserResponse().removeObserver(this);

                                progressBar.setVisibility(View.GONE);
                                signIn.setEnabled(true);
                                Log.e("resume5","end of onChanged");
                                mSignInResponse=signInResponse;
                            }

                        });

                        /**
                         * Old Method
                        vehicleViewModel.getUserResponse(getUser()).observe(getViewLifecycleOwner(), new Observer<SignInResponse>() {
                            @Override
                            public void onChanged(SignInResponse signInResponse) {
                                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                                    if (signInResponse == null) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(getContext(), "An error occurred, please try again" + "Message: "+ (signInResponse != null ? signInResponse.getMessage() : null) , Toast.LENGTH_LONG).show();
                                        //signUpViewModel.getNewUserResponse(createNewUser()).removeObservers(getViewLifecycleOwner());
                                    } else if (signInResponse.getMessage().equals("done")) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(getContext(), "sign-up successful " + "Message: " + (signInResponse != null ? signInResponse.getToken() : null) , Toast.LENGTH_SHORT).show();
                                        //signUpViewModel.getNewUserResponse(createNewUser()).removeObservers(getViewLifecycleOwner());
                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(getContext(), "Unexpected error occurred " + "Message: " + (signInResponse != null ? signInResponse.getMessage() : null), Toast.LENGTH_SHORT).show();
                                        //signUpViewModel.getNewUserResponse(createNewUser()).removeObservers(getViewLifecycleOwner());
                                    }
                                }
                            }
                        });*/
                    }

                    /**
                     * Send data
                    //String s = String.valueOf(email.getText());
                    //Log.e("frg",String.valueOf(fragmentOnClickListener));
                    //fragmentOnClickListener.onItemClick(s);
                    //String e = email.getText().toString();
                    //String p = password.getText().toString();

                    if (e.equals("admin") && p.equals("admin")) {
                        //Intent intent = new Intent(getActivity(), NavControllerActivity.class);
                        Intent intent = new Intent(getActivity(), UserLocationActivity.class);
                        intent.putExtra("user", "admin");
                        startActivity(intent);
                    } else {
                        email.setError("email or password isn't connected");
                    }*/
                }
            });

            actBtnAnim();

        }
        return view;
    }



    //====================================VALIDATION=====================================
    private boolean checkEmpty() {
        if (email.getText().toString().isEmpty()) {
            email.requestFocus();
            email.setError("Email is required");
            return false;
        } else if (password.getText().toString().isEmpty()) {
            password.requestFocus();
            password.setError("password name is required");
            return false;
        } else
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
    //====================================VALIDATION=====================================


    @Override
    public void onStop() {
        super.onStop();
        vehicleViewModel.getUserResponse().removeObservers(this);
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
    }

    private void actBtnAnim() {
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
    }


    private User getUser() {
        User user = new User();

        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());

        //Dummy Data New User
         /*
         user.setEmail("test2@example.com");
         user.setPassword("password.getText()");
         */

        return  user;
    }





    public interface FragmentOnClickListener {
        void onItemClick(String email);
    }
}