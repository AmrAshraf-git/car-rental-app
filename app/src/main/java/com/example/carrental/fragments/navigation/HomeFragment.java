package com.example.carrental.fragments.navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrental.dataModels.Vehicle;
import com.example.carrental.PriceLabel;
import com.example.carrental.R;
import com.example.carrental.dataModels.VehicleSpecs;
import com.example.carrental.adapters.HomeListAdapter;
import com.example.carrental.fragments.BookingFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    //private RecyclerView.LayoutManager layoutManager;
    private HomeListAdapter homeListAdapter;
    private ArrayList<Vehicle> homeListItemArrayList;
    private static final String URL="https://car-rental-eg.herokuapp.com/getAllVehicle";

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


        Vehicle vehicle = new Vehicle();
       /* RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //for(int i=0;i<response.length();i++){
                    try {
                        JSONObject data=response.getJSONObject(0);
                        vehicle.setVehicleModel(data.getString("model"));
                        Log.d("jSon",response.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                //}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        });*/

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("JSON",response.getJSONArray("data").get(0).toString());
                    JSONArray dataArray=response.getJSONArray("data");
                    for (int i=0;i<dataArray.length();i++) {
                        JSONObject data = dataArray.getJSONObject(i);
                        Vehicle vehicle = new Vehicle();
                        vehicle.setVehicleModel(data.getString("model"));
                        vehicle.setCompanyName("Example CMP");
                        vehicle.setPrice(Float.parseFloat(data.getString("pricePerDay")));
                        vehicle.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
                        //String[] spec={"Silver","4","5","Automatic"};
                        //homeListDataModel.setSpecs(spec);
                        vehicle.setVehicleColor(data.getString("color"));
                        vehicle.setDoorsNum(4);
                        vehicle.setSeatingCapacity(5);
                        VehicleSpecs vehicleSpecs=new VehicleSpecs();
                        vehicleSpecs.addEngineSpecs(data.getBoolean("transmissionType"),data.getInt("CC"));
                        vehicle.setVehicleSpecs(vehicleSpecs);
                        int[] vehicleImg =new int[3];
                        vehicleImg[0]=R.drawable.img_logo_test;
                        vehicle.setVehicleImg(vehicleImg);
                        vehicle.setCompanyAddress("Cairo,Egypt");
                        homeListItemArrayList.add(vehicle);
                    }
                    homeListAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(jsonObjectRequest);


        //======================================DUMMY DATA======================================
        //1st object
/*
        //vehicle.setVehicleModel("Nissan Sunny");
        //String[] book = {"150 Km/period", "Min 1 Days", "No Extra Fees", "Insurance Coverage"};
        //homeListItem.setBookDetails(book);
        vehicle.setCompanyName("Rent Me");
        vehicle.setPrice(600);
        vehicle.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
        //String[] spec={"Silver","4","5","Automatic"};
        //homeListDataModel.setSpecs(spec);
        vehicle.setVehicleColor("Silver");
        vehicle.setDoorsNum(4);
        vehicle.setSeatingCapacity(5);
        VehicleSpecs vehicleSpecs=new VehicleSpecs();
        vehicleSpecs.addEngineSpecs(false,1498);
        vehicle.setVehicleSpecs(vehicleSpecs);
        int[] vehicleImg =new int[3];
        vehicleImg[0]=R.drawable.img_logo_test;
        vehicle.setVehicleImg(vehicleImg);
        vehicle.setCompanyAddress("Cairo,Egypt");*/
/*
        //2nd object
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleModel("Peugeot 3008");
        //String[] book2 = {"150 Km/period", "Min 2 Days", "No Extra Fees", "Insurance Coverage"};
        //homeListItem2.setBookDetails(book2);
        vehicle2.setCompanyName("1st for Rent");
        vehicle2.setPrice(1200);
        //String[] spec2={"Blue","4","5","Automatic"};
        //homeListDataModel2.setSpecs(spec2);
        vehicle2.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
        vehicle2.setVehicleColor("blue");
        vehicle2.setDoorsNum(4);
        vehicle2.setSeatingCapacity(5);
        VehicleSpecs vehicleSpecs2=new VehicleSpecs();
        vehicleSpecs2.addEngineSpecs(true,1598);
        vehicle2.setVehicleSpecs(vehicleSpecs2);
        int[] vehicleImg2 =new int[3];
        vehicleImg2[0]=R.drawable.ic_car_default_black;
        vehicle2.setVehicleImg(vehicleImg2);
        vehicle2.setCompanyAddress("Alex,Egypt");

        homeListItemArrayList.add(vehicle);
        homeListItemArrayList.add(vehicle);
        homeListItemArrayList.add(vehicle2);
        homeListItemArrayList.add(vehicle);
        homeListItemArrayList.add(vehicle);
        homeListItemArrayList.add(vehicle2);*/
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