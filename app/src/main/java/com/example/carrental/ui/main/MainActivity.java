package com.example.carrental.ui.main;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.carrental.R;
import com.example.carrental.utility.SessionManager;

public class MainActivity extends AppCompatActivity {

    private Button continueUser;
    private Button continueGuest;
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


        if (SessionManager.getInstance(MainActivity.this).isLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, NavControllerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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



    public void initialization() {
        continueUser =findViewById(R.id.main_btn_continue);
        continueGuest =findViewById(R.id.main_btn_guest);
    }
}