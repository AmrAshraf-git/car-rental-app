package com.example.carrental.utility.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carrental.model.Vehicle;
import com.example.carrental.R;
import com.example.carrental.ui.main.VehicleViewModel;
import com.example.carrental.utility.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private final List<Vehicle> arrayList;
    private final OnRecyclerViewClickListener onRecyclerViewClickListener;

    public HomeListAdapter(List<Vehicle> arrayList, OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.arrayList = arrayList;
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
        setHasStableIds(true);
    }


    @NonNull
    @Override
    //Same as "getView method" but, this method includes if(view==null){Save the ViewHolder object in cached view} and else{get the ViewHolder object from cached view}.
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        //final View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_list_row, parent, false);
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_list_row, parent, false), onRecyclerViewClickListener,arrayList);
    }

    @Override
    public int getItemCount() {
        if(arrayList!=null)
            return arrayList.size();
        return 0;
    }

    public Vehicle getItem(final int pos) {
        if(arrayList.get(pos).getVehicleImgURL().length==0) {
            String[] tmp={"R.drawable.img_logo_test"};
            arrayList.get(pos).setVehicleImgURL(tmp);
        }
        return arrayList.get(pos);
    }

    @Override
    public long getItemId(int position) {
        //return (getItem(position).getId()+position);
        return position;
    }

    public void updateStatus(List<Vehicle> vehicles )
    {
        this.arrayList.clear();
        this.arrayList.addAll(vehicles);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(final int position) {
        return position;
        //return R.layout.activity_home_list_row;
    }

    @Override
    //What should do in each object of the ViewHolder
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //holder.bind(getItem(position));

        holder.priceLabel.setText(getItem(position).getPriceLabel().toString());
        //vehicleImage.setImageResource(vehicle.getVehicleImgURL()[0]);
        //Picasso.get().load(getItem(position).getVehicleImgURL()[0]).fit().centerInside().error(R.drawable.img_logo_test).into(holder.vehicleImage);
        Glide.with(holder.itemView.getContext()).load(getItem(position).getVehicleImgURL()[0]).fitCenter()
                .error(R.drawable.img_logo_test).into(holder.vehicleImage);
        //new DownloadImageTask(vehicleImage).execute(vehicle.getVehicleImgURL()[0]);
        holder.companyName.setText(getItem(position).getCompanyName());
        holder.vehicleModel.setText(getItem(position).getVehicleModel());
        holder.vehicleColor.setText(getItem(position).getVehicleColor());
        holder.carRate.setRating(getItem(position).getVehicleRate());
        holder.compRate.setRating(getItem(position).getCompRate());
        holder.doorsNum.setText(String.valueOf(getItem(position).getDoorsNum()));
        holder.seatingCapacity.setText(String.valueOf(getItem(position).getSeatingCapacity()));
        holder.transmission.setText(getItem(position).getAutomaticTransmission()?"Automatic":"Manual");
        holder.price.setText(String.valueOf(getItem(position).getPrice()));
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        //Vehicle vehicle;
        ImageView vehicleImage;
        RatingBar compRate, carRate;

        TextView compLocation,companyName, vehicleModel, vehicleColor, doorsNum, seatingCapacity, transmission,priceLabel,price;



        public ViewHolder(final View view,final OnRecyclerViewClickListener onRecyclerViewClickListener,List<Vehicle> arrayList)
        {
            super(view);

            //Inflating
            vehicleImage =view.findViewById(R.id.homeListRow_imgView_car);
            compRate=view.findViewById(R.id.homeListRow_ratingBar_compRate);
            carRate=view.findViewById(R.id.homeListRow_ratingBar_carRate);
            companyName=view.findViewById(R.id.homeListRow_txtView_compName);
            vehicleModel =view.findViewById(R.id.homeListRow_txtView_carModel);
            vehicleColor =view.findViewById(R.id.homeListRow_txtView_spec1);
            doorsNum =view.findViewById(R.id.homeListRow_txtView_spec2);
            seatingCapacity =view.findViewById(R.id.homeListRow_txtView_spec3);
            transmission =view.findViewById(R.id.homeListRow_txtView_spec4);
            price=view.findViewById(R.id.homeListRow_txtView_price);
            priceLabel=view.findViewById(R.id.homeListRow_txtView_lbl);

            itemView.setOnClickListener(v -> {
                //Log.d("click","Adapter_onItemClick");
                if(onRecyclerViewClickListener!=null){
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                        onRecyclerViewClickListener.onItemClick(position);
                }

            });

        }

        /*void bind(final Vehicle vehicle){
            this.vehicle = vehicle;

            priceLabel.setText(vehicle.getPriceLabel().toString());
            //vehicleImage.setImageResource(vehicle.getVehicleImgURL()[0]);
            //Picasso.get().load(vehicle.getVehicleImgURL()[0]).fit().centerInside().error(R.drawable.img_logo_test).into(vehicleImage);
            Glide.with(itemView.getContext()).load(vehicle.getVehicleImgURL()[0]).fitCenter().error(R.drawable.img_logo_test).into(vehicleImage);
            //new DownloadImageTask(vehicleImage).execute(vehicle.getVehicleImgURL()[0]);
            companyName.setText(vehicle.getCompanyName());
            vehicleModel.setText(vehicle.getVehicleModel());
            vehicleColor.setText(vehicle.getVehicleColor());
            carRate.setRating(vehicle.getVehicleRate());
            compRate.setRating(vehicle.getCompRate());
            doorsNum.setText(String.valueOf(vehicle.getDoorsNum()));
            seatingCapacity.setText(String.valueOf(vehicle.getSeatingCapacity()));
            transmission.setText(vehicle.getAutomaticTransmission()?"Automatic":"Manual");
            price.setText(String.valueOf(vehicle.getPrice()));
        }*/
    }

    public interface OnRecyclerViewClickListener {
        void onItemClick(int position);
    }

}
