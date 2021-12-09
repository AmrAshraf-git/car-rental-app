package com.example.carrental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomeListAdapter homeListAdapter;
    private ArrayList<HomeListItem> homeListItemArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initialization();

        //listView.setItemsCanFocus(true);
        recyclerView.setAdapter(homeListAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //customAdapterRecycleView.setHasStableIds(true);
        //listView.setItemViewCacheSize(20);
        //listView.setNestedScrollingEnabled(false);


        /*===========================================TEST CODE===========================================*/
        HomeListItem homeListItem = new HomeListItem();
        HomeListItem homeListItem2 = new HomeListItem();


        homeListItem.setCarModel("Nissan Sunny");

        String[] book = {"150 Km/period", "Min 1 Days", "No Extra Fees", "Insurance Coverage"};
        homeListItem.setBookDetails(book);

        homeListItem.setCompanyName("CR Company");

        homeListItem.setPrice(600);

        homeListItem.setPriceLabel(PriceLabel.EGYPTIAN_POUND);

        String[] spec={"Automatic","5 Passengers","Power Steering","4 Doors"};
        homeListItem.setSpecs(spec);



        homeListItem2.setCarModel("Peugeot 3008");

        String[] book2 = {"150 Km/period", "Min 2 Days", "No Extra Fees", "Insurance Coverage"};
        homeListItem2.setBookDetails(book2);

        homeListItem2.setCompanyName("Cars For Rent");
        homeListItem2.setPrice(1200);

        String[] spec2={"Automatic","5 Passengers","Power Steering","4 Doors"};
        homeListItem2.setSpecs(spec2);

        homeListItem.setPriceLabel(PriceLabel.EGYPTIAN_POUND);

        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem2);
        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem2);
        /*===========================================TEST CODE===========================================*/


    }
    public void initialization(){
        recyclerView = findViewById(R.id.Item_list);
        homeListItemArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        homeListAdapter = new HomeListAdapter(homeListItemArrayList);
    }
}