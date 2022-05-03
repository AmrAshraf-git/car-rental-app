package com.example.carrental.ui.main;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.carrental.R;
import com.example.carrental.utility.SessionManager;

public class MainActivity extends AppCompatActivity {

    private Button continueUser;
    private Button continueGuest;
    private Dialog dialog;
    private Button dialogBtn;
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private boolean mAlreadyLoaded;
/*
    @Override
    protected void onResume() {
        super.onResume();
        if (!mAlreadyLoaded) {
            mAlreadyLoaded = true;
            SessionManager sessionManager = SessionManager.getInstance(MainActivity.this);
            if (sessionManager.isLoggedIn()) {
                Intent intent = new Intent(MainActivity.this, NavControllerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                startActivity(intent);
                finish();
            }
        } else {
            SessionManager sessionManager = SessionManager.getInstance(MainActivity.this);
            if (sessionManager.isLoggedIn())
                finish();
        }
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            mAlreadyLoaded = false;

        connectivityManager=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null||!networkInfo.isAvailable()||!networkInfo.isConnected())
            dialogSetup();

        else {
            if (SessionManager.getInstance(MainActivity.this).isLoggedIn()) {
                Intent intent = new Intent(MainActivity.this, NavControllerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            } else {


                initialization();
                continueUser.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, EntryPageActivity.class);
                    startActivity(intent);
                });

                continueGuest.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, NavControllerActivity.class);
                    startActivity(intent);
                });
            }
        }
    }



    public void initialization() {
        continueUser =findViewById(R.id.main_btn_continue);
        continueGuest =findViewById(R.id.main_btn_guest);
    }


    private void dialogSetup() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_no_connection);
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_bar_rounded));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
                System.exit(0);
            }
        });
        //dialogTitle = dialog.findViewById(R.id.confDialog_txtView_title);
        //dialogSubTitle = dialog.findViewById(R.id.confDialog_txtView_subTitle);
        dialogBtn = dialog.findViewById(R.id.noConnection_btn_reload);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        //dialogTitle.setText("Registration successful!");
        //dialogSubTitle.setText("Please verify your E-mail first, to be able to login");
        dialog.show();
    }



}