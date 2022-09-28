package com.example.carrental.ui.main.fragment.navigation;

import android.os.Bundle;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.carrental.R;
import com.example.carrental.model.BookingHistoryResponse;
import com.example.carrental.model.Vehicle;
import com.example.carrental.ui.main.VehicleViewModel;
import com.example.carrental.ui.main.fragment.RatingFragment;
import com.example.carrental.utility.SessionManager;
import com.example.carrental.utility.adapter.HomeListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyBookingFragment extends Fragment implements HomeListAdapter.OnRecyclerViewClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private VehicleViewModel vehicleViewModel;
    private HomeListAdapter homeListAdapter;
    private List<Vehicle> availableRateList = new ArrayList<>();
    private List<Vehicle> tempAvailableRateList = new ArrayList<>();
    private BookingHistoryResponse mBookingHistoryResponse;
    private ProgressBar progressBar;
    private TextView rateResult;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private boolean mAlreadyLoaded;



    public MyBookingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            mAlreadyLoaded = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!mAlreadyLoaded) {
            mAlreadyLoaded = true;
            view =inflater.inflate(R.layout.fragment_my_booking, container, false);

            recyclerView = view.findViewById(R.id.myBookingF_recyclerView);
            swipeRefreshLayout = view.findViewById(R.id.myBookingF_swipeRf);
            progressBar = view.findViewById(R.id.myBookingF_prgrsBar);
            rateResult=view.findViewById(R.id.myBookingF_txtView_rateListResult);
            progressBar.setVisibility(View.VISIBLE);
            rateResult.setVisibility(View.GONE);

            linearLayoutManager = new LinearLayoutManager(getContext());
            vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
            setUpRecyclerView();
            observeViewModel();

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


    private void setUpRecyclerView() {
        //if (homeListAdapter == null) {
        homeListAdapter = new HomeListAdapter(availableRateList, this);
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

    private void observeViewModel() {
        vehicleViewModel.availableRateRequest(SessionManager.getInstance(getContext()).getLoginSession().getId());
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getAvailableRateResponse().removeObservers(getViewLifecycleOwner());

        vehicleViewModel.getAvailableRateResponse().observe(getViewLifecycleOwner(), new Observer<BookingHistoryResponse>() {
            @Override
            public void onChanged(BookingHistoryResponse bookingHistoryResponse) {

                //Log.e("resume1","onChanged");
                //Log.e("response",String.valueOf(vehicleResponse));
                //Log.e("mResponse",String.valueOf(mVehicleResponse));
                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && bookingHistoryResponse != mBookingHistoryResponse) {
                    //Log.e("resume2","Lifecycle_RESUMED(if1)");
                    rateResult.setVisibility(View.GONE);
                    if (bookingHistoryResponse.getMessage() != null && bookingHistoryResponse.getMessage().equals("success")) {
                        //Log.e("resume3","if2");
                        if (bookingHistoryResponse.getData() != null) {
                            //Log.e("resume4","if3");
                            tempAvailableRateList.clear();
                            for(int i =0; i<bookingHistoryResponse.getData().size();i++){
                                tempAvailableRateList.add(bookingHistoryResponse.getData().get(i).getVehicleID());
                            }
                            //historyItemList.addAll(tempHistoryItemList);
                            homeListAdapter.updateStatus(tempAvailableRateList);
                            //Log.e("resume6",String.valueOf(historyItemList.get(0).getVehicleModel()));

                            //homeListAdapter.notifyDataSetChanged();
                            //homeItemList.addAll(vehicleResponse.getData());
                            //homeListAdapter.notifyDataSetChanged();
                            //handler.post(new Runnable() {
                            // @Override
                            //public void run() {
                            //}
                            //});
                        } else
                            Toast.makeText(getContext(), (bookingHistoryResponse.getMessage() != null ? bookingHistoryResponse.getMessage() : "No responding data"), Toast.LENGTH_SHORT).show();
                    }
                    else if(bookingHistoryResponse.getMessage() != null && bookingHistoryResponse.getMessage().equals("No_data")){
                        rateResult.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(getContext(), (bookingHistoryResponse.getMessage() != null ? bookingHistoryResponse.getMessage() : "Unknown response, please try again"), Toast.LENGTH_SHORT).show();
                        //Log.e("resume5","else1");
                    }
                }
                mBookingHistoryResponse = bookingHistoryResponse;
                getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
                progressBar.setVisibility(View.GONE);
                vehicleViewModel.getAllVehicleResponse().removeObservers(getViewLifecycleOwner());
                //Log.e("resume6","end of onChanged");
            }
        });

    }


    @Override
    public void onItemClick(int position) {
        Fragment fragment= RatingFragment.newInstance(mBookingHistoryResponse.getData().get(position).getVehicleID().get_id(),mBookingHistoryResponse.getData().get(position).getCompanyID());
        getParentFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.translate_enter, R.anim.translate_exit)
                .addToBackStack(null)
                .replace(R.id.navContent_frameLayout_container, fragment).commit();
    }
}