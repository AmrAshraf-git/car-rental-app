package com.example.carrental;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class HomeListItem implements Parcelable {
    private String companyName;
    private String companyAddress;

    private String carModel;
    private int carImg;
    private int id;
    private double price;
    private PriceLabel priceLabel;
    private String[] specs;
    private String[] bookDetails;

    public HomeListItem() {
        carImg= R.drawable.img_logo_test;
        priceLabel=PriceLabel.DOLLAR;
        id= new Random().nextInt(30);
    }


    protected HomeListItem(Parcel in) {
        companyName = in.readString();
        companyAddress = in.readString();
        carModel = in.readString();
        carImg = in.readInt();
        id = in.readInt();
        price = in.readDouble();
        specs = in.createStringArray();
        bookDetails = in.createStringArray();
    }

    public static final Creator<HomeListItem> CREATOR = new Creator<HomeListItem>() {
        @Override
        public HomeListItem createFromParcel(Parcel in) {
            return new HomeListItem(in);
        }

        @Override
        public HomeListItem[] newArray(int size) {
            return new HomeListItem[size];
        }
    };

    public PriceLabel getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(PriceLabel priceLabel) {
        this.priceLabel = priceLabel;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarImg() {
        return carImg;
    }

    public void setCarImg(int carImg) {
        this.carImg = carImg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getSpecs() {
        return specs;
    }

    public void setSpecs(String[] specs) {
        this.specs = specs;
    }

    public String[] getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(String[] bookDetails) {
        this.bookDetails = bookDetails;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(companyName);
        parcel.writeString(companyAddress);
        parcel.writeString(carModel);
        parcel.writeInt(carImg);
        parcel.writeInt(id);
        parcel.writeDouble(price);
        parcel.writeStringArray(specs);
        parcel.writeStringArray(bookDetails);
    }
}
