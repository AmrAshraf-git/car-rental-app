package com.example.carrental.ui.main.fragment.navigation;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
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

    private static final String CATEGORY_NAME = "categoryName";
    private String mCategoryName;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private VehicleViewModel vehicleViewModel;
    //private RecyclerView.LayoutManager layoutManager;
    private HomeListAdapter homeListAdapter;
    private List<Vehicle> homeItemList = new ArrayList<>();
    private VehicleResponse mVehicleResponse;
    private VehicleResponse mSearchVehicleResponse;
    private VehicleResponse mSearchByCategoryVehicleResponse;
    private ProgressBar progressBar;
    private TextView searchResult;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private boolean mAlreadyLoaded;
    private String previousQuery;
    MenuItem mMenuItem;
    Menu mMenu;
    MenuInflater mInflater;
    SearchView searchView;
    //private ProgressDialog progressDialog;
    Handler handler;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String categoryName) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_NAME, categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState == null)
            mAlreadyLoaded = false;
        if (getArguments() != null)
            mCategoryName = getArguments().getString(CATEGORY_NAME);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!mAlreadyLoaded) {
            mAlreadyLoaded = true;

            view = inflater.inflate(R.layout.fragment_home, container, false);

            recyclerView = view.findViewById(R.id.homePageF_recyclerView_main);
            searchResult = view.findViewById(R.id.homePageF_txtView_searchResult);
            swipeRefreshLayout = view.findViewById(R.id.homePageF_swipeRf);
            progressBar = view.findViewById(R.id.homePageF_prgrsBar);

            searchResult.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            linearLayoutManager = new LinearLayoutManager(getContext());
            vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);

            setUpRecyclerView();
            getByCategory();

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    progressBar.setVisibility(View.VISIBLE);
                    //observeViewModel();
                    getByCategory();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
        return view;
    }


    private void getByCategory() {
        switch (mCategoryName) {
            case "car":
                vehicleViewModel.searchedVehicleRequest("car");
                observeSearchViewModel();
                break;
            case "bus":
                vehicleViewModel.searchedVehicleRequest("bus");
                observeSearchViewModel();
                break;
            case "motorcycle":
                vehicleViewModel.searchedVehicleRequest("motorcycle");
                observeSearchViewModel();
                break;
            default:
                vehicleViewModel.allVehicleRequest();
                observeViewModel();
                break;
        }
        previousQuery = null;
    }


    private void observeViewModel() {
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getAllVehicleResponse().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getAllVehicleResponse().observe(getViewLifecycleOwner(), new Observer<VehicleResponse>() {
            @Override
            public void onChanged(VehicleResponse vehicleResponse) {
                //Log.e("resume1","onChanged");
                //Log.e("response",String.valueOf(vehicleResponse));
                //Log.e("mResponse",String.valueOf(mVehicleResponse));
                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && vehicleResponse != mVehicleResponse) {
                    //Log.e("resume2","Lifecycle_RESUMED(if1)");
                    if (vehicleResponse.getMessage() != null && vehicleResponse.getMessage().equals("success")) {
                        //Log.e("resume3","if2");
                        if (vehicleResponse.getData() != null) {
                            homeListAdapter.updateStatus(vehicleResponse.getData());
                            if (vehicleResponse.getData().isEmpty()) {
                                searchResult.setText("There is no data to show!\n");
                                searchResult.setVisibility(View.VISIBLE);
                            } else
                                searchResult.setVisibility(View.GONE);

                            //Log.e("resume4","if3");
                            //homeItemList.addAll(vehicleResponse.getData());
                            //homeListAdapter.notifyDataSetChanged();
                            //handler.post(new Runnable() {
                            // @Override
                            //public void run() {
                            //}
                            //});
                        } else
                            Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "Invalid response, please try again"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "Unknown response, please try again"), Toast.LENGTH_SHORT).show();
                        //Log.e("resume5","else1");
                    }
                }
                mVehicleResponse = vehicleResponse;
                getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                vehicleViewModel.getAllVehicleResponse().removeObservers(getViewLifecycleOwner());
                progressBar.setVisibility(View.GONE);
                //Log.e("resume6","end of onChanged");
            }
        });
    }


    private void observeSearchViewModel() {
        vehicleViewModel.getSearchedVehicleResponse().removeObservers(getViewLifecycleOwner());
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getSearchedVehicleResponse().observe(getViewLifecycleOwner(), new Observer<VehicleResponse>() {
            @Override
            public void onChanged(VehicleResponse vehicleResponse) {
                Log.e("resume1", "(Search)onChanged");
                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && vehicleResponse != mSearchVehicleResponse) {
                    Log.e("resume2", "(Search)Lifecycle_RESUMED(if1)");
                    if (vehicleResponse.getMessage() != null) {
                        //Log.e("resume3","(Search)if2");
                        if (vehicleResponse.getData() != null) {
                            homeListAdapter.updateStatus(vehicleResponse.getData());
                            if (vehicleResponse.getData().isEmpty()) {
                                if (previousQuery != null) {
                                    searchResult.setText("There is no result to show!\n try to type a specific model");
                                    searchResult.setVisibility(View.VISIBLE);
                                } else {
                                    searchResult.setText("There is no data to show!\n");
                                    searchResult.setVisibility(View.VISIBLE);
                                }
                            } else {
                                searchResult.setVisibility(View.GONE);
                                //Log.e("resume4","(Search)if3");
                                //homeItemList.addAll(vehicleResponse.getData());
                                //homeListAdapter.notifyDataSetChanged();
                                //handler.post(new Runnable() {
                                // @Override
                                //public void run() {
                                //}
                                //});
                            }

                        } else
                            Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "Invalid response, please try again"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "Unknown response, please try again"), Toast.LENGTH_SHORT).show();
                        //Log.e("resume5","(Search)else1");
                    }
                }
                //getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                mSearchVehicleResponse = vehicleResponse;
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setEnabled(false);
                searchView.clearFocus();
                //vehicleViewModel.getVehicle().removeObservers(getViewLifecycleOwner());
                //Log.e("resume6","(Search)end of onChanged");
            }
        });
    }





    /*private void observeSearchByCategoryViewModel() {
        vehicleViewModel.getSearchedVehicle().removeObservers(getViewLifecycleOwner());
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getSearchedVehicle().observe(getViewLifecycleOwner(), new Observer<VehicleResponse>() {
            @Override
            public void onChanged(VehicleResponse vehicleResponse) {
                Log.e("resume1", "(Search)onChanged");
                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && vehicleResponse != mSearchByCategoryVehicleResponse) {
                    Log.e("resume2", "(Search)Lifecycle_RESUMED(if1)");
                    if (vehicleResponse.getMessage() != null) {
                        //Log.e("resume3","(Search)if2");
                        if (vehicleResponse.getData() != null) {
                            if (vehicleResponse.getData().isEmpty()) {
                                homeListAdapter.updateStatus(vehicleResponse.getData());
                                searchResult.setVisibility(View.VISIBLE);
                            } else {
                                searchResult.setVisibility(View.GONE);
                                //Log.e("resume4","(Search)if3");
                                homeListAdapter.updateStatus(vehicleResponse.getData());
                                //homeItemList.addAll(vehicleResponse.getData());
                                //homeListAdapter.notifyDataSetChanged();
                                //handler.post(new Runnable() {
                                // @Override
                                //public void run() {
                                //}
                                //});
                            }

                        } else
                            Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "No responding data"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "Unknown response, please try again"), Toast.LENGTH_SHORT).show();
                        //Log.e("resume5","(Search)else1");
                    }
                }
                //getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                mSearchByCategoryVehicleResponse = vehicleResponse;
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setEnabled(false);
                searchView.clearFocus();
                //vehicleViewModel.getVehicle().removeObservers(getViewLifecycleOwner());
                //Log.e("resume6","(Search)end of onChanged");
            }
        });
    }*/


    private void setUpRecyclerView() {
        //if (homeListAdapter == null) {
        homeListAdapter = new HomeListAdapter(homeItemList, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //performance
        //homeListAdapter.setHasStableIds(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
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
        menu.clear();
        //Log.e("onCreateOptionsMenu","called");
        inflater.inflate(R.menu.tool_bar_menu, menu);
        mMenuItem = menu.findItem(R.id.menu_item_search);
        searchView = (SearchView) mMenuItem.getActionView();
        //searchView.setFocusableInTouchMode(false);
        searchView.setTouchscreenBlocksFocus(true);
        searchView.setFocusable(false);

        //searchView.setIconified(true);
        mMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                //Log.e("MenuItem","Expand");
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                searchResult.setVisibility(View.GONE);
                //observeViewModel();
                getByCategory();
                swipeRefreshLayout.setEnabled(true);
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                vehicleViewModel.searchedVehicleRequest(query);
                previousQuery = query;
                observeSearchViewModel();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        if (vehicleViewModel.getSearchedVehicleResponse() != null) {
            if (vehicleViewModel.getSearchedVehicleResponse().getValue() != null && previousQuery != null) {
                previousQuery = vehicleViewModel.getSearchedVehicleResponse().getValue().getSearch();

                if (previousQuery != null && !previousQuery.isEmpty()) {
                    //mMenuItem.setVisible(false);
                    //searchView.setVisibility(View.INVISIBLE);
                    mMenuItem.expandActionView();
                    //searchView.setFocusable(false);
                    //searchView.setIconified(false);
                    searchView.clearFocus();
                    searchView.setQuery(previousQuery, false);
                    //mMenuItem.setVisible(true);
                    //mMenuItem.setVisible(true);
                    //searchView.setVisibility(View.VISIBLE);
                }

                //Log.e("MenuItem",previousQuery);
                //mMenuItem.collapseActionView();
            }
            super.onCreateOptionsMenu(menu, inflater);
        }
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


    @Override
    public void onStop() {
        super.onStop();
        //vehicleViewModel.getAllVehicleResponse().removeObservers(getViewLifecycleOwner());
        //vehicleViewModel.getSearchedVehicleResponse().removeObservers(getViewLifecycleOwner());
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (searchView != null)
            searchView.clearFocus();
    }


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