package com.example.carrental.ui.main.fragment.navigation;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.carrental.model.Vehicle;
import com.example.carrental.R;
import com.example.carrental.model.VehicleResponse;
import com.example.carrental.utility.adapter.HomeListAdapter;
import com.example.carrental.ui.main.fragment.BookingFragment;
import com.example.carrental.ui.main.VehicleViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeListAdapter.OnRecyclerViewClickListener {

    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private VehicleViewModel vehicleViewModel;
    //private RecyclerView.LayoutManager layoutManager;
    private HomeListAdapter homeListAdapter;
    private List<Vehicle> homeItemList = new ArrayList<>();
    private VehicleResponse mVehicleResponse;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private boolean mAlreadyLoaded;
    //private ProgressDialog progressDialog;
    Handler handler;

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
        if(savedInstanceState==null)
            mAlreadyLoaded=false;


        //if (getArguments() != null) {
        //mParam1 = getArguments().getString(ARG_PARAM1);
        //mParam2 = getArguments().getString(ARG_PARAM2);
        //}
        //homeListItemArrayList = new ArrayList<>();


        /**
         * Volley
         *
         //        //RequestQueue queue = Volley.newRequestQueue(getActivity());
         //        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(
         //                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
         //            @Override
         //            public void onResponse(JSONObject response) {
         //                try {
         //                    //Log.d("JSON",response.getJSONArray("data").get(0).toString());
         //                    JSONArray dataArray=response.getJSONArray("data");
         //                    String[] vehicleImg;
         //                    Vehicle vehicle;
         //                    for (int i=0;i<dataArray.length();i++) {
         //                        JSONObject data = dataArray.getJSONObject(i);
         //                        vehicle= new Vehicle();
         //                        vehicle.setVehicleModel(data.getString("model"));
         //                        vehicle.setCompanyName(data.getJSONObject("companyID").getString("CompanyName"));
         //                        vehicle.setPrice(Float.parseFloat(data.getString("pricePerDay")));
         //                        vehicle.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
         //
         //                        vehicle.setVehicleColor(data.getString("color"));
         //                        vehicle.setDoorsNum(data.getInt("doorsNumber"));
         //                        vehicle.setSeatingCapacity(data.getInt("chairsNumber"));
         //                        vehicle.setVehicleRate(Float.parseFloat(data.getString("VehicleRate")));
         //                        vehicle.setCompRate(Float.parseFloat(data.getJSONObject("companyID").getString("companyRate")));
         //
         //                        VehicleSpecs vehicleSpecs=new VehicleSpecs();
         //                        vehicleSpecs.addSafetySpecs(data.getBoolean("airbag"),data.getBoolean("seatbelts"),data.getBoolean("ABS"));
         //                        vehicleSpecs.addFeatures(data.getBoolean("sunroof"),data.getBoolean("Parking_Sensors"),data.getBoolean("Radio"),data.getBoolean("Bluetooth"),data.getBoolean("Smoking_Preferences"),data.getBoolean("Navigation_System"),data.getBoolean("Remote_Start"),data.getBoolean("AC"),data.getBoolean("Music_Player"));
         //                        vehicleSpecs.Accessories(data.getBoolean("Extra_Tyre"),data.getBoolean("Charger"),data.getBoolean("Fire_Extinguisher"),data.getBoolean("First_Aid_Kit"),true);
         //                        vehicleSpecs.addEngineSpecs(data.getBoolean("transmissionType"),data.getInt("CC"));
         //                        vehicle.setVehicleSpecs(vehicleSpecs);
         //
         //                        vehicleImg =new String[data.getJSONArray("imageURL").length()];
         //                        for(int j=0;j<data.getJSONArray("imageURL").length();j++)
         //                        {
         //                            vehicleImg[j]=data.getJSONArray("imageURL").get(j).toString();
         //                        }
         //                        /*
         //                        vehicleImg[0]=data.getJSONArray("imageURL").get(0).toString();
         //                        vehicleImg[1]=data.getJSONArray("imageURL").get(1).toString();
         //                        vehicleImg[2]=data.getJSONArray("imageURL").get(3).toString();
         //
         //                        vehicle.setVehicleImgURL(vehicleImg);
         //
         //                        vehicle.setCompanyAddress(data.getJSONObject("companyID").getString("Street"));
         //                        homeListItemArrayList.add(vehicle);
         //                    }
         //                    homeListAdapter.notifyDataSetChanged();
         //
         //                } catch (JSONException e) {
         //                    Toast.makeText(getContext(), "An error occur, try again", Toast.LENGTH_SHORT).show();
         //                    e.printStackTrace();
         //                }
         //            }
         //        }, new Response.ErrorListener() {
         //            @Override
         //            public void onErrorResponse(VolleyError error) {
         //                error.printStackTrace();
         //                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
         //            }
         //        }
         //        );
         //        //queue.add(jsonObjectRequest);
         //        Singleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
         //
         //        handler.post(new Runnable() {
         //            @Override
         //            public void run() {
         //             if (progressDialog.isShowing())
         //                 progressDialog.dismiss();
         //            }
         //        });*/
        /**
         * Dummy Data
         *
         //======================================DUMMY DATA======================================
         //        //1st object
         //        //Vehicle vehicle = new Vehicle();
         //        //vehicle.setVehicleModel("Nissan Sunny");
         //        //String[] book = {"150 Km/period", "Min 1 Days", "No Extra Fees", "Insurance Coverage"};
         //        //homeListItem.setBookDetails(book);
         //        vehicle.setCompanyName("Rent Me");
         //        vehicle.setPrice(600);
         //        vehicle.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
         //        //String[] spec={"Silver","4","5","Automatic"};
         //        //homeListDataModel.setSpecs(spec);
         //        vehicle.setVehicleColor("Silver");
         //        vehicle.setDoorsNum(4);
         //        vehicle.setSeatingCapacity(5);
         //        VehicleSpecs vehicleSpecs=new VehicleSpecs();
         //        vehicleSpecs.addEngineSpecs(false,1498);
         //        vehicle.setVehicleSpecs(vehicleSpecs);
         //        int[] vehicleImg =new int[3];
         //        vehicleImg[0]=R.drawable.img_logo_test;
         //        vehicle.setVehicleImg(vehicleImg);
         //        vehicle.setCompanyAddress("Cairo,Egypt");
         //
         //        //2nd object
         //        Vehicle vehicle2 = new Vehicle();
         //        vehicle2.setVehicleModel("Peugeot 3008");
         //        //String[] book2 = {"150 Km/period", "Min 2 Days", "No Extra Fees", "Insurance Coverage"};
         //        //homeListItem2.setBookDetails(book2);
         //        vehicle2.setCompanyName("1st for Rent");
         //        vehicle2.setPrice(1200);
         //        //String[] spec2={"Blue","4","5","Automatic"};
         //        //homeListDataModel2.setSpecs(spec2);
         //        vehicle2.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
         //        vehicle2.setVehicleColor("blue");
         //        vehicle2.setDoorsNum(4);
         //        vehicle2.setSeatingCapacity(5);
         //        VehicleSpecs vehicleSpecs2=new VehicleSpecs();
         //        vehicleSpecs2.addEngineSpecs(true,1598);
         //        vehicle2.setVehicleSpecs(vehicleSpecs2);
         //        int[] vehicleImg2 =new int[3];
         //        vehicleImg2[0]=R.drawable.ic_car_default_black;
         //        vehicle2.setVehicleImg(vehicleImg2);
         //        vehicle2.setCompanyAddress("Alex,Egypt");
         //
         //        homeListItemArrayList.add(vehicle);
         //        homeListItemArrayList.add(vehicle);
         //        homeListItemArrayList.add(vehicle2);
         //        homeListItemArrayList.add(vehicle);
         //        homeListItemArrayList.add(vehicle);
         //        homeListItemArrayList.add(vehicle2);
         //======================================DUMMY DATA======================================
         */

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState==null && !mAlreadyLoaded) {
            mAlreadyLoaded=true;
            Log.e("test","view");
            view = inflater.inflate(R.layout.fragment_home, container, false);
            recyclerView = view.findViewById(R.id.homePageF_recyclerView_main);
            //handler=new Handler();
            //handler.post(new Runnable() {
            //   @Override
            // public void run() {
            swipeRefreshLayout = view.findViewById(R.id.homePageF_swipeRf);
            progressBar = view.findViewById(R.id.homePageF_prgrsBar);
            progressBar.setVisibility(View.VISIBLE);
            // }
            // });
            //homeListAdapter = new HomeListAdapter(homeItemList,this);
            linearLayoutManager=new LinearLayoutManager(getContext());
            vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);

            /**
             * Retrofit MVC
             Retrofit retrofit = new Retrofit.Builder().baseUrl("https://car-rental-eg.herokuapp.com/")
             .addConverterFactory(GsonConverterFactory.create()).build();
             ApiService apiService = retrofit.create(ApiService.class);
             Call<JsonBridgeGetAllVehicle> call = apiService.getJsonModel();

             call.enqueue(new Callback<JsonBridgeGetAllVehicle>() {
            @Override public void onResponse(Call<JsonBridgeGetAllVehicle> call, Response<JsonBridgeGetAllVehicle> response) {
            if (!response.isSuccessful() || response.body()==null) {
            Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
            return;
            }
            //Log.d("Jdata", String.valueOf(response.body().getData().get(10).getCompRate()));
            //homeListItemArrayList=new ArrayList<>(Arrays.asList(response.body().getData()));
            //homeListItemArrayList.clear();
            //homeListItemArrayList=response.body().getData();
            //homeListAdapter.notifyDataSetChanged();
            homeListAdapter.updateStatus(response.body().getData());

            if(progressBar.isShown())
            progressBar.setVisibility(View.GONE);
            //Log.d("Jdata", String.valueOf(homeListItemArrayList.get(10).getCompanyAddress()));
            //for(int i=0;i<response.body().getData().size();i++){
            //   homeListItemArrayList.add(response.body().getData().get(i));
            //}
            //homeListAdapter.notifyDataSetChanged();
            }

            @Override public void onFailure(Call<JsonBridgeGetAllVehicle> call, Throwable t) {
            t.printStackTrace();
            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
            });*/

            /**
             * Volley
             SingletonClient.getSingletonClient().getJsonModel().enqueue(new Callback<JsonBridgeGetAllVehicle>() {
            @Override public void onResponse(Call<JsonBridgeGetAllVehicle> call, Response<JsonBridgeGetAllVehicle> response) {
            if (!response.isSuccessful() || response.body()==null) {
            Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
            return;
            }
            //Log.d("Jdata", String.valueOf(response.body().getData().get(10).getCompRate()));
            //homeListItemArrayList=new ArrayList<>(Arrays.asList(response.body().getData()));
            //homeListItemArrayList.clear();
            //homeListItemArrayList=response.body().getData();
            //homeListAdapter.notifyDataSetChanged();
            homeListAdapter.updateStatus(response.body().getData());

            if(progressBar.isShown())
            progressBar.setVisibility(View.GONE);
            //Log.d("Jdata", String.valueOf(homeListItemArrayList.get(10).getCompanyAddress()));
            //for(int i=0;i<response.body().getData().size();i++){
            //   homeListItemArrayList.add(response.body().getData().get(i));
            //}
            //homeListAdapter.notifyDataSetChanged();
            }

            @Override public void onFailure(Call<JsonBridgeGetAllVehicle> call, Throwable t) {
            t.printStackTrace();
            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
            });
             */

            //vehicleViewModel.getVehicles(view);
            observeViewModel();
            setUpRecyclerView();


            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    progressBar.setVisibility(View.VISIBLE);
                    observeViewModel();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
        return view;
    }

    private void observeViewModel() {
        //vehicleViewModel.getVehicle().removeObservers(getViewLifecycleOwner());
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getVehicle().observe(getViewLifecycleOwner(), new Observer<VehicleResponse>() {
            @Override
            public void onChanged(VehicleResponse vehicleResponse) {
                Log.e("resume1","onChanged");
                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && vehicleResponse !=mVehicleResponse) {
                    Log.e("resume2","Lifecycle_RESUMED(if1)");
                    if (vehicleResponse.getMessage()!=null && vehicleResponse.getMessage().equals("done")) {
                        Log.e("resume3","if2");
                        if (vehicleResponse.getData() != null) {
                            Log.e("resume4","if3");
                            homeListAdapter.updateStatus(vehicleResponse.getData());
                            //homeItemList.addAll(vehicleResponse.getData());
                            //homeListAdapter.notifyDataSetChanged();
                            //handler.post(new Runnable() {
                            // @Override
                            //public void run() {
                            //}
                            //});
                        }
                        else
                            Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "No responding data"), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "Unknown response, please try again"), Toast.LENGTH_SHORT).show();
                        Log.e("resume5","else1");
                    }
                }
                getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                mVehicleResponse=vehicleResponse;
                progressBar.setVisibility(View.GONE);
                vehicleViewModel.getVehicle().removeObservers(getViewLifecycleOwner());
                Log.e("resume6","end of onChanged");
            }
        });
    }

    private void setUpRecyclerView() {
        //if (homeListAdapter == null) {
            homeListAdapter = new HomeListAdapter(homeItemList, this);
            recyclerView.setLayoutManager(linearLayoutManager);
            //performance
            //homeListAdapter.setHasStableIds(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(homeListAdapter);
        //} //else {
            //recyclerView.setAdapter(homeListAdapter);
            //homeListAdapter.updateStatus(homeItemList);
            //homeListAdapter.notifyDataSetChanged();
            //Log.e("test", "data");

        //}

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tool_bar_menu, menu);
        //MenuItem menuItem = menu.findItem(R.id.search_bar);
        //SearchView searchView=(SearchView) menuItem.getActionView();
    }

    @Override
    public void onItemClick(int position) {
        //====================================SEND DATA=====================================
        Fragment fragment = BookingFragment.newInstance(homeItemList.get(position));
        getParentFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.translate_enter, R.anim.translate_exit)
                .addToBackStack(null)
                .replace(R.id.navContent_frameLayout_container, fragment).commit();
        //====================================SEND DATA=====================================
    }
}