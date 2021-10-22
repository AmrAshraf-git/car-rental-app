package com.example.carrental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class EntryPage extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        initialization();

        viewPagerAdapter.addFragmentTab("Login",new SignIn());
        viewPagerAdapter.addFragmentTab("Signup",new SignUp());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }


    public void initialization() {
        tabLayout=findViewById(R.id.signUp_tabLayout);
        viewPager=findViewById(R.id.signUp_viewPager);
        viewPagerAdapter =new ViewPagerAdapter(getSupportFragmentManager());
    }
}