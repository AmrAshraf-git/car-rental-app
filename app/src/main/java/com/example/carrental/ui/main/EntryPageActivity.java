package com.example.carrental.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.carrental.R;
import com.example.carrental.utility.SessionManager;
import com.example.carrental.utility.adapter.EntryViewPagerAdapter;
import com.example.carrental.ui.main.fragment.SignInFragment;
import com.example.carrental.ui.main.fragment.SignUpFragment;
import com.google.android.material.tabs.TabLayout;

public class EntryPageActivity extends AppCompatActivity implements SignInFragment.FragmentOnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean mAlreadyLoaded;
    //SignInFragment signInFragment;
    //private SessionManager sessionManager;

/*
    @Override
    protected void onResume() {
        super.onResume();
        //if (!mAlreadyLoaded) {
            mAlreadyLoaded = true;
            SessionManager sessionManager = SessionManager.getInstance(EntryPageActivity.this);
            if (sessionManager.isLoggedIn()) {
                Intent intent = new Intent(EntryPageActivity.this, NavControllerActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                startActivity(intent);
                //finishAffinity();
            }
            else
            {

            }
        //} else
            //finish();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        //SessionManager sessionManager=SessionManager.getInstance(EntryPageActivity.this);
        if (savedInstanceState == null)
            mAlreadyLoaded = false;

        if (SessionManager.getInstance(EntryPageActivity.this).isLoggedIn()) {
            Intent intent = new Intent(EntryPageActivity.this, NavControllerActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
            startActivity(intent);
            finish();
            //requireActivity().finishAffinity();
        } else {
            initialization();
            EntryViewPagerAdapter entryViewPagerAdapter = new EntryViewPagerAdapter(getSupportFragmentManager());
            //signInFragment=new SignInFragment();
            //====================================VIEW PAGER SETUP====================================
            entryViewPagerAdapter.addFragmentTab("Login", new SignInFragment());
            entryViewPagerAdapter.addFragmentTab("Signup", new SignUpFragment());
            viewPager.setAdapter(entryViewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            //====================================VIEW PAGER SETUP====================================

        }
    }

    //Receive data from sign in FRG (if required).
    @Override
    public void onItemClick(String email) {
        //Log.e("emailTest",email);
        //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
    }


    public void initialization() {
        tabLayout=findViewById(R.id.entryPage_tabLayout);
        viewPager=findViewById(R.id.entryPage_viewPager);
    }
}