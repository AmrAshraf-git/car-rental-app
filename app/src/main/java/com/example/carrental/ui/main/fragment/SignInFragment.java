package com.example.carrental.ui.main.fragment;

import android.app.Dialog;
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
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.carrental.R;
import com.example.carrental.model.ForgetPasswordResponse;
import com.example.carrental.model.SignInResponse;
import com.example.carrental.model.User;
import com.example.carrental.ui.main.NavControllerActivity;
import com.example.carrental.ui.main.VehicleViewModel;
import com.example.carrental.utility.SessionManager;
import com.example.carrental.utility.TextValidation;


public class SignInFragment extends Fragment {
    private EditText email;
    private EditText password;
    private Button signIn;
    //private FloatingActionButton floatingActionButton1;
    //private FloatingActionButton floatingActionButton2;
    //private FloatingActionButton floatingActionButton3;
    private Dialog dialog;
    private Button dialogBtn;
    private TextView dialogTitle;
    private TextView dialogSubTitle;
    private ProgressBar progressBar;
    private TextView loginTitle;
    private TextView forgetPassword;
    private View view;
    private boolean mAlreadyLoaded;
    private FragmentOnClickListener fragmentOnClickListener;
    private VehicleViewModel vehicleViewModel;
    private SignInResponse mSignInResponse;
    private ForgetPasswordResponse mForgetPasswordResponse;
    private SessionManager sessionManager;
    private Object MainActivity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentOnClickListener) {
            fragmentOnClickListener = (FragmentOnClickListener) context;
            sessionManager=SessionManager.getInstance(context);
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState==null && !mAlreadyLoaded) {
            mAlreadyLoaded = true;
            view = inflater.inflate(R.layout.fragment_sign_in, container, false);
            email = view.findViewById(R.id.signIn_edTxt_email);
            password = view.findViewById(R.id.signIn_edTxt_pswd);
            signIn = view.findViewById(R.id.signIn_btn_signIn);
            loginTitle = view.findViewById(R.id.signIn_txtView_title);
            forgetPassword=view.findViewById(R.id.signIn_txtView_forgetPswd);
            //floatingActionButton1 = view.findViewById(R.id.signIn_floatBtn_1);
            //floatingActionButton2 = view.findViewById(R.id.signIn_floatBtn_2);
            //floatingActionButton3 = view.findViewById(R.id.signIn_floatBtn_3);
            progressBar = view.findViewById(R.id.signIn_progressBar);



            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextValidation.isEmailValid(email)) {

                        vehicleViewModel.signInRequest(getUser());
                        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                        vehicleViewModel.getSignInResponse().removeObservers(getViewLifecycleOwner());

                        progressBar.setVisibility(View.VISIBLE);
                        signIn.setEnabled(false);
                        //User mUser=getUser();
                        //vehicleViewModel.signInRequest(getUser());
                        Log.e("resume1","onClick");
                        vehicleViewModel.getSignInResponse().observe(getViewLifecycleOwner(), new Observer<SignInResponse>() {
                            @Override
                            public void onChanged(SignInResponse signInResponse) {
                                Log.e("resume2","onChanged");
                                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && signInResponse !=mSignInResponse) {
                                    Log.e("resume3","Lifecycle_RESUMED(if1)");
                                    if (signInResponse.getMessage()!=null && signInResponse.getMessage().equals("success")) {
                                        email.setError(null);
                                        //Toast.makeText(getContext(), "sign-in successful " + "Message: " +  signInResponse.getToken() , Toast.LENGTH_SHORT).show();
                                        Log.e("resume4","if2");
                                        if(signInResponse.getToken()!=null) {
                                            //mUser.setToken(signInResponse.getToken());
                                            sessionManager.saveLoginSession(signInResponse);
                                            Log.e("sin",signInResponse.getFirstName());
                                            moveToHomeActivity();
                                            Log.e("resume5","if3");
                                        }
                                    }
                                    else if (signInResponse.getMessage()!=null && signInResponse.getMessage().equals("400")){
                                        email.setError("Invalid email or password");
                                        Log.e("msg",signInResponse.getMessage());
                                        Log.e("resume6","else if1");
                                    }
                                    else {
                                        email.setError(null);
                                        Toast.makeText(getContext(), (signInResponse.getMessage() != null ? signInResponse.getMessage() : "Unknown"), Toast.LENGTH_SHORT).show();
                                        Log.e("resume7","else1");
                                    }
                                }
                                //vehicleViewModel.getUserResponse().removeObservers(getViewLifecycleOwner());
                                getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                                //vehicleViewModel.getUserResponse().removeObserver(this);

                                progressBar.setVisibility(View.GONE);
                                signIn.setEnabled(true);
                                mSignInResponse=signInResponse;
                                //Log.e("resume8","end of onChanged");
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


            forgetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextValidation.isEmailValid(email)) {
                        vehicleViewModel.forgetPasswordRequest(email.getText().toString());
                        vehicleViewModel.getForgetPasswordResponse().removeObservers(getViewLifecycleOwner());
                        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                        progressBar.setVisibility(View.VISIBLE);
                        signIn.setEnabled(false);

                        //vehicleViewModel.forgetPasswordRequest(email.getText().toString());
                        vehicleViewModel.getForgetPasswordResponse().observe(getViewLifecycleOwner(), new Observer<ForgetPasswordResponse>() {
                            @Override
                            public void onChanged(ForgetPasswordResponse forgetPasswordResponse) {
                                Log.e("resume2","onChanged");
                                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && forgetPasswordResponse !=mForgetPasswordResponse) {
                                    Log.e("resume3","Lifecycle_RESUMED(if1)");
                                    if (forgetPasswordResponse.getMessage()!=null && forgetPasswordResponse.getMessage().equals("done")) {
                                        email.setError(null);
                                        dialogSetup();
                                        Log.e("resume4","if2");

                                    }
                                    else if (forgetPasswordResponse.getMessage()!=null){
                                        email.setError(forgetPasswordResponse.getMessage());
                                        Log.e("msg",forgetPasswordResponse.getMessage());
                                        Log.e("resume6","else if1");
                                    }
                                    else {
                                        email.setError(null);
                                        Toast.makeText(getContext(), (forgetPasswordResponse.getMessage() != null ? forgetPasswordResponse.getMessage() : "Unknown"), Toast.LENGTH_SHORT).show();
                                        Log.e("resume7","else1");
                                    }
                                }

                                getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                                //vehicleViewModel.getUserResponse().removeObserver(this);

                                progressBar.setVisibility(View.GONE);
                                signIn.setEnabled(true);
                                mForgetPasswordResponse=forgetPasswordResponse;
                                //Log.e("resume8","end of onChanged");
                            }
                        });
                    }
                }
            });


            actBtnAnim();

        }
        return view;
    }



    @Override
    public void onStop() {
        super.onStop();
        if(vehicleViewModel.getSignInResponse()!=null) {
            Log.e("SignIn->onStop()","There is a getSignInResponse() Observer");
            vehicleViewModel.getSignInResponse().removeObservers(getViewLifecycleOwner());
        }
        if(vehicleViewModel.getForgetPasswordResponse()!=null){
            Log.e("SignIn->onStop()","There is a getForgetPasswordResponse() Observer");
            vehicleViewModel.getForgetPasswordResponse().removeObservers(getViewLifecycleOwner());
        }
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
    }

    private void actBtnAnim() {
        //====================================ANIMATIONS==========================================
        /*
        floatingActionButton1.setTranslationX(1000);
        floatingActionButton2.setTranslationX(1000);
        floatingActionButton3.setTranslationX(1000);

        //floatingActionButton1.setAlpha(0);
        //floatingActionButton2.setAlpha(0);
        //floatingActionButton3.setAlpha(0);

        floatingActionButton1.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(200).start();
        floatingActionButton2.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(400).start();
        floatingActionButton3.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(600).start();
        */
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



    private void moveToHomeActivity() {
        Intent intent = new Intent(getContext(), NavControllerActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
        requireActivity().finish();
        //requireActivity().finishAffinity();
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
        dialogTitle.setText("E-mail has been sent successfully!");
        dialogSubTitle.setText("Please check your E-mail to reset your password");
        dialog.show();
    }

    public interface FragmentOnClickListener {
        void onItemClick(String email);
    }
}