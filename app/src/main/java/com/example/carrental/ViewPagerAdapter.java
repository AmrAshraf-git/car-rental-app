package com.example.carrental;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final  List<FragmentTab> fragmentTabs;


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragmentTabs =new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentTabs.get(position).getFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTabs.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return fragmentTabs.size();
    }

    public void addFragmentTab(String title, Fragment fragment )
    {
        FragmentTab fragmentTab=new FragmentTab(title,fragment);
        fragmentTabs.add(fragmentTab);
    }
}
