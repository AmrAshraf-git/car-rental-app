package com.example.carrental;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.adapters.HomeListAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class HomePageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomeListAdapter homeListAdapter;
    private ArrayList<HomeListItem> homeListItemArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        Bundle bundle2=getIntent().getExtras();
        if(bundle2 !=null && bundle2.getString("user").equals("admin")) {
            String test=bundle2.getString("aEmail");
            Objects.requireNonNull(getSupportActionBar()).setTitle("Administrator (DEV)");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        }

        recyclerView = findViewById(R.id.homePage_recyclerView_main);
        homeListItemArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);

        //=======================================RV SETUP=======================================
        homeListAdapter = new HomeListAdapter(homeListItemArrayList, (id, homeListItem) -> {

            //======================================DEBUG=======================================
            //Log.e("click","HomePage onItemClick");
            //Log.e("ReceivedData",homeListItem.getCarModel());
            //======================================DEBUG=======================================

            //====================================SEND DATA=====================================
            Intent intent=new Intent(HomePageActivity.this, BookingActivity.class);
            intent.putExtra("listItemObject",homeListItem);
            intent.putExtra("id",id);
            startActivity(intent);
            //====================================SEND DATA=====================================
        });
        recyclerView.setAdapter(homeListAdapter);
        recyclerView.setLayoutManager(layoutManager);
        //performance
        //homeListAdapter.setHasStableIds(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setNestedScrollingEnabled(false);
        //=======================================RV SETUP=======================================



        //======================================DUMMY DATA======================================
        HomeListItem homeListItem = new HomeListItem();
        HomeListItem homeListItem2 = new HomeListItem();

        homeListItem.setCarModel("Nissan Sunny");
        //String[] book = {"150 Km/period", "Min 1 Days", "No Extra Fees", "Insurance Coverage"};
        //homeListItem.setBookDetails(book);
        homeListItem.setCompanyName("Rent Me");
        homeListItem.setPrice(600);
        homeListItem.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
        String[] spec={"Silver","4","5","Automatic"};
        homeListItem.setSpecs(spec);
        homeListItem.setCarImg(R.drawable.img_logo_test);
        homeListItem.setCompanyAddress("Cairo,Egypt");

        homeListItem2.setCarModel("Peugeot 3008");
        //String[] book2 = {"150 Km/period", "Min 2 Days", "No Extra Fees", "Insurance Coverage"};
        //homeListItem2.setBookDetails(book2);
        homeListItem2.setCompanyName("1st for Rent");
        homeListItem2.setPrice(1200);
        String[] spec2={"Blue","4","5","Automatic"};
        homeListItem2.setSpecs(spec2);
        homeListItem2.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
        homeListItem2.setCarImg(R.drawable.ic_car_default_black);
        homeListItem2.setCompanyAddress("Alex,Egypt");

        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem2);
        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem2);
        //======================================DUMMY DATA======================================

    }

}