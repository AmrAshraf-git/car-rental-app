package com.example.carrental.ui.main.fragment.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrental.R;
import com.example.carrental.model.BookingHistoryResponse;
import com.example.carrental.model.Vehicle;
import com.example.carrental.ui.main.VehicleViewModel;
import com.example.carrental.ui.main.fragment.HistoryDetailsFragment;
import com.example.carrental.utility.SessionManager;
import com.example.carrental.utility.adapter.HomeListAdapter;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment implements HomeListAdapter.OnRecyclerViewClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private VehicleViewModel vehicleViewModel;
    private HomeListAdapter homeListAdapter;
    private List<Vehicle> historyItemList = new ArrayList<>();
    private List<Vehicle> tempHistoryItemList = new ArrayList<>();
    private BookingHistoryResponse mBookingHistoryResponse;
    private ProgressBar progressBar;
    private TextView historyResult;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private boolean mAlreadyLoaded;

    public HistoryFragment() {
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
            //Log.e("test","view");
            view = inflater.inflate(R.layout.fragment_history, container, false);
            recyclerView = view.findViewById(R.id.historyPageF_recyclerView_main);
            swipeRefreshLayout = view.findViewById(R.id.historyPageF_swipeRf);
            progressBar = view.findViewById(R.id.historyPageF_prgrsBar);
            historyResult=view.findViewById(R.id.historyPageF_txtView_historyResult);
            progressBar.setVisibility(View.VISIBLE);
            historyResult.setVisibility(View.GONE);

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
        homeListAdapter = new HomeListAdapter(historyItemList, this);
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
        vehicleViewModel.historyRequest(SessionManager.getInstance(getContext()).getLoginSession().getId());
        getViewLifecycleOwnerLiveData().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getBookingHistoryResponse().removeObservers(getViewLifecycleOwner());
        vehicleViewModel.getBookingHistoryResponse().observe(getViewLifecycleOwner(), new Observer<BookingHistoryResponse>() {
            @Override
            public void onChanged(BookingHistoryResponse bookingHistoryResponse) {
                //Log.e("resume1","onChanged");
                //Log.e("response",String.valueOf(vehicleResponse));
                //Log.e("mResponse",String.valueOf(mVehicleResponse));
                if (getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && bookingHistoryResponse != mBookingHistoryResponse) {
                    //Log.e("resume2","Lifecycle_RESUMED(if1)");
                    historyResult.setVisibility(View.GONE);
                    if (bookingHistoryResponse.getMessage() != null && bookingHistoryResponse.getMessage().equals("success")) {
                        //Log.e("resume3","if2");
                        if (bookingHistoryResponse.getData() != null) {
                            //Log.e("resume4","if3");
                            tempHistoryItemList.clear();
                            for(int i =0; i<bookingHistoryResponse.getData().size();i++){
                                tempHistoryItemList.add(bookingHistoryResponse.getData().get(i).getVehicleID());
                            }
                            //historyItemList.addAll(tempHistoryItemList);
                            homeListAdapter.updateStatus(tempHistoryItemList);
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
                    else if(bookingHistoryResponse.getMessage() != null && bookingHistoryResponse.getMessage().equals("NotFound")){
                        historyResult.setVisibility(View.VISIBLE);
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

        Fragment fragment=HistoryDetailsFragment.newInstance(mBookingHistoryResponse.getData().get(position));
        getParentFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.translate_enter, R.anim.translate_exit)
                .addToBackStack(null)
                .replace(R.id.navContent_frameLayout_container, fragment).commit();

        /*Fragment fragment= RatingFragment.newInstance(mBookingHistoryResponse.getData().get(position).getVehicleID().get_id(),mBookingHistoryResponse.getData().get(position).getCompanyID());
        getParentFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.translate_enter, R.anim.translate_exit)
                .addToBackStack(null)
                .replace(R.id.navContent_frameLayout_container, fragment).commit();*/
    }
}