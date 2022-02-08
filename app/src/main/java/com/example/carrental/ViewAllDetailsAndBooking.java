package com.example.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewAllDetailsAndBooking extends AppCompatActivity {

    private TextView carModel;
    private TextView companyName;
    private ImageView carImage;
    private ArrayList<HomeListItem> homeListItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_details_and_booking);

        initialization();


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {

            //test
            int pos = bundle.getInt("position");
            Log.e("position", String.valueOf(pos));
            //Log.e("model",homeListItemArrayList.get(pos).getCarModel());
            carModel.setText(bundle.getString("carModel"));
        }

    }
    public void initialization() {
        carModel=findViewById(R.id.viewAllDetails_txtView_carModel);
        companyName=findViewById(R.id.viewAllDetails_txtView_companyName);
        carImage=findViewById(R.id.viewAllDetails_imgView_carImage);
    }
}