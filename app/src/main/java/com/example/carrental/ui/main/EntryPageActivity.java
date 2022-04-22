package com.example.carrental.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.carrental.R;
import com.example.carrental.utility.adapter.EntryViewPagerAdapter;
import com.example.carrental.ui.main.fragment.SignInFragment;
import com.example.carrental.ui.main.fragment.SignUpFragment;
import com.google.android.material.tabs.TabLayout;

public class EntryPageActivity extends AppCompatActivity implements SignInFragment.FragmentOnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //SignInFragment signInFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        initialization();

        EntryViewPagerAdapter entryViewPagerAdapter = new EntryViewPagerAdapter(getSupportFragmentManager());
        //signInFragment=new SignInFragment();


        //====================================VIEW PAGER SETUP====================================
        entryViewPagerAdapter.addFragmentTab("Login",new SignInFragment());
        entryViewPagerAdapter.addFragmentTab("Signup",new SignUpFragment());
        viewPager.setAdapter(entryViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //====================================VIEW PAGER SETUP====================================
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