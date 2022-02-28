package com.example.carrental.fragments.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.HomeListDataModel;
import com.example.carrental.PriceLabel;
import com.example.carrental.R;
import com.example.carrental.adapters.HomeListAdapter;
import com.example.carrental.fragments.BookingFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    //private RecyclerView.LayoutManager layoutManager;
    private HomeListAdapter homeListAdapter;
    private ArrayList<HomeListDataModel> homeListItemArrayList;

    //private String mParam1;
    //private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

/*
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        //}

        homeListItemArrayList = new ArrayList<>();
        homeListAdapter = new HomeListAdapter(homeListItemArrayList, (id, homeListItem) -> {

            //======================================DEBUG=======================================
            //Log.e("click","HomePage onItemClick");
            //Log.e("ReceivedData",homeListItem.getCarModel());
            //======================================DEBUG=======================================

            //====================================SEND DATA=====================================
            //Intent intent=new Intent(getContext(), BookingActivity.class);
            //intent.putExtra("listItemObject",homeListItem);
            //intent.putExtra("id",id);
            //startActivity(intent);

            //Bundle bundle=new Bundle();
            //bundle.putParcelable("listItemObject",homeListItem);
            //fragment.setArguments(bundle);
            Fragment fragment= BookingFragment.newInstance(homeListItem);
            getParentFragmentManager().beginTransaction().setCustomAnimations(R.anim.translate_enter, R.anim.nav_default_exit_anim)
                    .addToBackStack(null).replace(R.id.navContent_frameLayout_container,fragment).commit();
            //====================================SEND DATA=====================================
        });

        //======================================DUMMY DATA======================================
        HomeListDataModel homeListDataModel = new HomeListDataModel();
        HomeListDataModel homeListDataModel2 = new HomeListDataModel();

        homeListDataModel.setCarModel("Nissan Sunny");
        //String[] book = {"150 Km/period", "Min 1 Days", "No Extra Fees", "Insurance Coverage"};
        //homeListItem.setBookDetails(book);
        homeListDataModel.setCompanyName("Rent Me");
        homeListDataModel.setPrice(600);
        homeListDataModel.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
        String[] spec={"Silver","4","5","Automatic"};
        homeListDataModel.setSpecs(spec);
        homeListDataModel.setCarImg(R.drawable.img_logo_test);
        homeListDataModel.setCompanyAddress("Cairo,Egypt");

        homeListDataModel2.setCarModel("Peugeot 3008");
        //String[] book2 = {"150 Km/period", "Min 2 Days", "No Extra Fees", "Insurance Coverage"};
        //homeListItem2.setBookDetails(book2);
        homeListDataModel2.setCompanyName("1st for Rent");
        homeListDataModel2.setPrice(1200);
        String[] spec2={"Blue","4","5","Automatic"};
        homeListDataModel2.setSpecs(spec2);
        homeListDataModel2.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
        homeListDataModel2.setCarImg(R.drawable.ic_car_default_black);
        homeListDataModel2.setCompanyAddress("Alex,Egypt");

        homeListItemArrayList.add(homeListDataModel);
        homeListItemArrayList.add(homeListDataModel);
        homeListItemArrayList.add(homeListDataModel2);
        homeListItemArrayList.add(homeListDataModel);
        homeListItemArrayList.add(homeListDataModel);
        homeListItemArrayList.add(homeListDataModel2);
        //======================================DUMMY DATA======================================


    }
/*
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tool_bar_menu,menu);
        //MenuItem menuItem = menu.findItem(R.id.search_bar);
        //SearchView searchView=(SearchView) menuItem.getActionView();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        //=======================================RV SETUP=======================================
        recyclerView = view.findViewById(R.id.homePageF_recyclerView_main);
        recyclerView.setAdapter(homeListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //performance
        //homeListAdapter.setHasStableIds(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setNestedScrollingEnabled(false);
        //=======================================RV SETUP=======================================


        return view;
    }
/*
    @Override
    public void onResume() {
        super.onResume();
        ((HomePageActivity)getActivity()).getSupportActionBar().setTitle("Choose your car");
    }

 */

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tool_bar_menu,menu);
        //MenuItem menuItem = menu.findItem(R.id.search_bar);
        //SearchView searchView=(SearchView) menuItem.getActionView();
    }
}