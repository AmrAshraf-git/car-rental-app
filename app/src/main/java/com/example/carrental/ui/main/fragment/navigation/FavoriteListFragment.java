package com.example.carrental.ui.main.fragment.navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.R;
import com.example.carrental.model.Vehicle;
import com.example.carrental.model.VehicleResponse;
import com.example.carrental.ui.main.VehicleViewModel;
import com.example.carrental.utility.SessionManager;
import com.example.carrental.utility.adapter.HomeListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoriteListFragment extends Fragment implements HomeListAdapter.OnRecyclerViewClickListener {

    private VehicleViewModel vehicleViewModel;
    private View view;
    private VehicleResponse mVehicleResponse;
    private HomeListAdapter homeListAdapter;
    private List<Vehicle> homeItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private TextView searchResult;

    public FavoriteListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_favorite_list, container, false);

        recyclerView = view.findViewById(R.id.favoriteF_recyclerView_main);
        searchResult = view.findViewById(R.id.favoriteF_txtView_favoriteResult);
        progressBar = view.findViewById(R.id.favoriteF_prgrsBar);
        searchResult.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        linearLayoutManager = new LinearLayoutManager(getContext());
        vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);

        setUpRecyclerView();
        observeFavorite();


        return view;
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


    private void observeFavorite() {
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.favoriteListRequest(SessionManager.getInstance(getContext()).getLoginSession().getId());
        vehicleViewModel.getFavoriteListResponse().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getFavoriteListResponse().observe(getViewLifecycleOwner(), new Observer<VehicleResponse>() {
            @Override
            public void onChanged(VehicleResponse vehicleResponse) {
                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && vehicleResponse != mVehicleResponse) {

                    if (vehicleResponse.getMessage() != null && vehicleResponse.getMessage().equals("done")) {
                        if (vehicleResponse.getData() != null) {
                            Log.e("resume2","else1");
                            homeListAdapter.updateStatus(vehicleResponse.getData());
                        }
                        else
                            Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "No responding data"), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), (vehicleResponse.getMessage() != null ? vehicleResponse.getMessage() : "Unknown response, please try again"), Toast.LENGTH_SHORT).show();
                        //Log.e("resume5","else1");
                    }
                }
                getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                mVehicleResponse=vehicleResponse;
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }










/*
    @Override
    public void onResume() {
        super.onResume();
        ((HomePageActivity)getActivity()).getSupportActionBar().setTitle("Favorite");
    }

 */
}