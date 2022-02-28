package com.example.carrental.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrental.HomeListDataModel;
import com.example.carrental.R;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private final ArrayList<HomeListDataModel> arrayList;
    private final OnRecyclerViewClickListener onRecyclerViewClickListener;

    public HomeListAdapter(ArrayList<HomeListDataModel> arrayList, OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.arrayList = arrayList;
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
        setHasStableIds(true);
    }


    @NonNull
    @Override
    //Same as "getView method" but, this method includes if(view==null){Save the ViewHolder object in cached view} and else{get the ViewHolder object from cached view}.
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public HomeListDataModel getItem(final int pos) {
        return arrayList.get(pos);
    }

    @Override
    public long getItemId(final int position) {
        return (getItem(position).getId()+position);
    }

    @Override
    public int getItemViewType(final int position) {
        return position;
        //return R.layout.activity_home_list_row;
    }

    @Override
    //What should do in each object of the ViewHolder
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.bind(getItem(position));
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        HomeListDataModel homeListDataModel;
        ImageView carImage;
        RatingBar compRate;
        TextView compLocation,companyName,carModel,spec1,spec2,spec3,spec4,priceLabel,price;

        public ViewHolder(final View view)
        {
            super(view);

            //Inflating
            carImage=view.findViewById(R.id.homeListRow_imgView_car);
            compRate=view.findViewById(R.id.homeListRow_ratingBar_compRate);
            compLocation=view.findViewById(R.id.homeListRow_txtView_compLocation);
            companyName=view.findViewById(R.id.homeListRow_txtView_compName);
            carModel=view.findViewById(R.id.homeListRow_txtView_carModel);
            spec1=view.findViewById(R.id.homeListRow_txtView_spec1);
            spec2=view.findViewById(R.id.homeListRow_txtView_spec2);
            spec3=view.findViewById(R.id.homeListRow_txtView_spec3);
            spec4=view.findViewById(R.id.homeListRow_txtView_spec4);
            price=view.findViewById(R.id.homeListRow_txtView_price);
            priceLabel=view.findViewById(R.id.homeListRow_txtView_lbl);


            itemView.setOnClickListener(v -> {
                //Log.d("click","Adapter_onItemClick");

                onRecyclerViewClickListener.onItemClick(HomeListAdapter.this.getItemId(getAdapterPosition()),getItem(getAdapterPosition()));
            });
        }

        void bind(final HomeListDataModel homeListDataModel){
            this.homeListDataModel = homeListDataModel;

            priceLabel.setText(homeListDataModel.getPriceLabel().toString());
            carImage.setImageResource(homeListDataModel.getCarImg());
            companyName.setText(homeListDataModel.getCompanyName());
            carModel.setText(homeListDataModel.getCarModel());
            spec1.setText(homeListDataModel.getSpecs()[0]);
            spec2.setText(homeListDataModel.getSpecs()[1]);
            spec3.setText(homeListDataModel.getSpecs()[2]);
            spec4.setText(homeListDataModel.getSpecs()[3]);
            price.setText(String.valueOf(homeListDataModel.getPrice()));
        }
    }

    public interface OnRecyclerViewClickListener {
        void onItemClick(long id, HomeListDataModel homeListDataModel);
    }

}
