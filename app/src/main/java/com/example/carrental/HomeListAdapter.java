package com.example.carrental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private ArrayList<HomeListItem> arrayList;

    public HomeListAdapter(ArrayList<HomeListItem> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    //Same as "getView method" but, this method includes if(view==null){Save the ViewHolder object in cached view} and else{get the ViewHolder object from cached view}.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_list_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    //What should do in each object of the ViewHolder
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        HomeListItem homeListItem=arrayList.get(position);
        holder.bind(homeListItem);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public HomeListItem getItem(int pos) {
        return arrayList.get(pos);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        HomeListItem homeListItem;
        ImageView carImage;
        Button continuBtn;
        TextView companyName,carModel,spec1,spec2,spec3,spec4,bookingTitle,bookingDetails1,bookingDetails2,bookingDetails3,bookingDetails4,priceLabel,price;

        public ViewHolder(View view)
        {
            super(view);
            carImage=view.findViewById(R.id.homeListRow_imgView_car);
            continuBtn=view.findViewById(R.id.homeListRow_btn_continue);

            companyName=view.findViewById(R.id.homeListRow_txtView_compName);
            carModel=view.findViewById(R.id.homeListRow_txtView_carModel);
            spec1=view.findViewById(R.id.homeListRow_txtView_spec1);
            spec2=view.findViewById(R.id.homeListRow_txtView_spec2);
            spec3=view.findViewById(R.id.homeListRow_txtView_spec3);
            spec4=view.findViewById(R.id.homeListRow_txtView_spec4);
            price=view.findViewById(R.id.homeListRow_txtView_price);
            bookingTitle=view.findViewById(R.id.homeListRow_txtView_bookTitle);
            bookingDetails1=view.findViewById(R.id.homeListRow_txtView_bookDtls1);
            bookingDetails2=view.findViewById(R.id.homeListRow_txtView_bookDtls2);
            bookingDetails3=view.findViewById(R.id.homeListRow_txtView_bookDtls3);
            bookingDetails4=view.findViewById(R.id.homeListRow_txtView_bookDtls4);
            priceLabel=view.findViewById(R.id.homeListRow_txtView_lbl);

        }

        void bind(HomeListItem homeListItem){
            this.homeListItem=homeListItem;

            priceLabel.setText(homeListItem.getPriceLabel().toString());
            carImage.setImageResource(homeListItem.getCarImg());
            companyName.setText(homeListItem.getCompanyName());
            carModel.setText(homeListItem.getCarModel());
            spec1.setText(homeListItem.getSpecs()[0]);
            spec2.setText(homeListItem.getSpecs()[1]);
            spec3.setText(homeListItem.getSpecs()[2]);
            spec4.setText(homeListItem.getSpecs()[3]);
            price.setText(String.valueOf(homeListItem.getPrice()));
            bookingDetails1.setText(homeListItem.getBookDetails()[0]);
            bookingDetails2.setText(homeListItem.getBookDetails()[1]);
            bookingDetails3.setText(homeListItem.getBookDetails()[2]);
            bookingDetails4.setText(homeListItem.getBookDetails()[3]);

        }
    }

}
