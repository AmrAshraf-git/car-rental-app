package com.example.carrental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class EntryPage extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EntryViewPagerAdapter entryViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        initialization();

        entryViewPagerAdapter.addFragmentTab("Login",new SignIn());
        entryViewPagerAdapter.addFragmentTab("Signup",new SignUp());
        viewPager.setAdapter(entryViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }


    public void initialization() {
        tabLayout=findViewById(R.id.entryPage_tabLayout);
        viewPager=findViewById(R.id.entryPage_viewPager);
        entryViewPagerAdapter =new EntryViewPagerAdapter(getSupportFragmentManager());
    }
}