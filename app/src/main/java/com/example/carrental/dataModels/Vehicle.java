package com.example.carrental.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.carrental.PriceLabel;
import com.example.carrental.R;

import java.util.Random;

public class Vehicle implements Parcelable {
    /*private String companyName;
    private String companyAddress;
    private String carModel;
    private int carImg;
    private int id;
    private double price;
    private PriceLabel priceLabel;
    private String[] specs;
    private String[] bookDetails;*/

    private int id;
    //1 ->  Brand Image
    private String vehicleModel; //2
    private String companyName; //3
    private String companyAddress; //4
    private float CompRate; //5
    private int[] vehicleImg; //6
    private String vehicleColor; //7
    private int doorsNum; //8
    private int seatingCapacity; //9
    private float vehicleRate; //10
    private float price; //11
    private PriceLabel priceLabel; //12

    //private String[] specs;
    //private String[] bookDetails;
    private VehicleSpecs vehicleSpecs;

    public Vehicle() {
        vehicleImg =new int[3];
        vehicleImg[0]= R.drawable.img_logo_test;
        priceLabel=PriceLabel.DOLLAR;
        id= new Random().nextInt(30);
    }


    protected Vehicle(Parcel in) {
        id = in.readInt();
        companyName = in.readString();
        companyAddress = in.readString();
        vehicleModel = in.readString();
        vehicleImg = in.createIntArray();
        vehicleColor = in.readString();
        doorsNum = in.readInt();
        seatingCapacity = in.readInt();
        vehicleRate = in.readFloat();
        CompRate = in.readFloat();
        price = in.readFloat();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int[] getVehicleImg() {
        return vehicleImg;
    }

    public void setVehicleImg(int[] vehicleImg) {
        this.vehicleImg = vehicleImg;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public int getDoorsNum() {
        return doorsNum;
    }

    public void setDoorsNum(int doorsNum) {
        this.doorsNum = doorsNum;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public float getVehicleRate() {
        return vehicleRate;
    }

    public void setVehicleRate(float vehicleRate) {
        this.vehicleRate = vehicleRate;
    }

    public float getCompRate() {
        return CompRate;
    }

    public void setCompRate(float compRate) {
        CompRate = compRate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public PriceLabel getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(PriceLabel priceLabel) {
        this.priceLabel = priceLabel;
    }

    public VehicleSpecs getVehicleSpecs() {
        return vehicleSpecs;
    }

    public void setVehicleSpecs(VehicleSpecs vehicleSpecs) {
        this.vehicleSpecs = vehicleSpecs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(companyName);
        dest.writeString(companyAddress);
        dest.writeString(vehicleModel);
        dest.writeIntArray(vehicleImg);
        dest.writeString(vehicleColor);
        dest.writeInt(doorsNum);
        dest.writeInt(seatingCapacity);
        dest.writeFloat(vehicleRate);
        dest.writeFloat(CompRate);
        dest.writeFloat(price);
    }

}
