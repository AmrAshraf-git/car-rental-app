package com.example.carrental.ui.main.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carrental.R;
import com.example.carrental.ui.main.fragment.navigation.HomeFragment;


public class ChooseCategoryFragment extends Fragment {


    private CardView carCategory;
    private CardView busCategory;
    private CardView motorcycleCategory;
    private CardView allCategory;
    private View view;
    private FragmentManager fragmentManager;



    public ChooseCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_choose_category, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        carCategory = view.findViewById(R.id.category_cardView_Cars);
        busCategory=view.findViewById(R.id.category_cardView_Buses);
        motorcycleCategory=view.findViewById(R.id.category_cardView_Motorbikes);
        allCategory=view.findViewById(R.id.category_cardView_All);
        transmit(carCategory,"car");
        transmit(busCategory,"bus");
        transmit(motorcycleCategory,"motorcycle");
        transmit(allCategory,"all");

        return view;
    }


    private void transmit(CardView cardView, String category){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=HomeFragment.newInstance(category);
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.translate_enter, R.anim.translate_exit).addToBackStack(null)
                        .replace(R.id.navContent_frameLayout_container, fragment).commit();
            }
        });
    }
}