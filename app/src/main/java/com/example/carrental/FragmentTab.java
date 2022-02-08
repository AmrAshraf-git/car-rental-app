package com.example.carrental;

import androidx.fragment.app.Fragment;

public class FragmentTab {
    private final String title;
    private final Fragment fragment;

    public FragmentTab(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }
    public Fragment getFragment() {
        return fragment;
    }

    /*
    public void setTitle(String title) {
        this.title = title;
    }
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
    */
}
