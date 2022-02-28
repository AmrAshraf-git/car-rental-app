package com.example.carrental;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button continueUser;
    private Button continueGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();

        continueUser.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, EntryPageActivity.class);
            startActivity(intent);
        });

        continueGuest.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, NavControllerActivity.class);
            startActivity(intent);
        });
    }



    public void initialization() {
        continueUser =findViewById(R.id.main_btn_continue);
        continueGuest =findViewById(R.id.main_btn_guest);
    }
}