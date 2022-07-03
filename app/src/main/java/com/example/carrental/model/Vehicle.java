package com.example.carrental.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bumptech.glide.Glide;
import com.example.carrental.R;
import com.example.carrental.constant.PriceLabel;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

public class Vehicle extends VehicleSpecs implements Parcelable {

    private int id; //temp
    boolean isFavorite=false;

    private String _id;
    @SerializedName("model")
    @Expose
    private String vehicleModel; //2

    @SerializedName("brand")
    @Expose
    private String vehicleBrand;

    @SerializedName("Vehicletype")
    @Expose
    private String vehicleType;

    @SerializedName("imageURL")
    @Expose
    private String[] vehicleImgURL; //6

    @SerializedName("color")
    @Expose
    private String vehicleColor; //7

    @SerializedName("doorsNumber")
    @Expose
    private int doorsNum; //8

    @SerializedName("chairsNumber")
    @Expose
    private int seatingCapacity; //9

    @SerializedName("VehicleRate")
    @Expose
    private float vehicleRate; //10

    @SerializedName("pricePerDay")
    @Expose
    private float price; //11

    private PriceLabel priceLabel; //12

    @SerializedName("companyID")
    @Expose
    private JsonObject compDetails;

    private String companyName; //3
    private String companyCity;
    private String companyAddress; //4
    private int compHotline;
    private float CompRate; //5

    private VehicleSpecs vehicleSpecs;





    public Vehicle() {
        //vehicleImgURL =new String[4];
        //vehicleImgURL[0]= "R.drawable.img_logo_test";
        priceLabel=PriceLabel.DOLLAR;
        //id= new Random().nextInt(30);
    }


    protected Vehicle(Parcel in) {
        id = in.readInt();
        isFavorite = in.readByte() != 0;
        _id = in.readString();
        vehicleModel = in.readString();
        vehicleBrand = in.readString();
        vehicleType = in.readString();
        vehicleImgURL = in.createStringArray();
        vehicleColor = in.readString();
        doorsNum = in.readInt();
        seatingCapacity = in.readInt();
        vehicleRate = in.readFloat();
        price = in.readFloat();
        companyName = in.readString();
        companyCity = in.readString();
        companyAddress = in.readString();
        compHotline = in.readInt();
        CompRate = in.readFloat();
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCompanyName() {
        return compDetails.get("CompanyName").getAsString();
    }

    public String getCompanyLongitude() {
        //return compDetails.get("Longitude").getAsString();
        return "30.071275";
    }

    public String getCompanyLatitude() {
        //return compDetails.get("Latitude").getAsString();
        return "31.239217";
    }

    public int getCompHotline() {
        return compDetails.get("Hotline").getAsInt();
    }

    public void setCompHotline(int compHotline) {
        this.compHotline = compHotline;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCity() {
        return compDetails.get("City").getAsString();
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyAddress() {
        return compDetails.get("Street").getAsString();
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

    public String[] getVehicleImgURL() {
        return vehicleImgURL;
    }

    public void setVehicleImgURL(String[] vehicleImgURL) {
        this.vehicleImgURL = vehicleImgURL;
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
        return compDetails.get("companyRate").getAsFloat();
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

    public JsonObject getCompDetails() {
        return compDetails;
    }

    public void setCompDetails(JsonObject compDetails) {
        this.compDetails = compDetails;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeString(_id);
        dest.writeString(vehicleModel);
        dest.writeString(vehicleBrand);
        dest.writeString(vehicleType);
        dest.writeStringArray(vehicleImgURL);
        dest.writeString(vehicleColor);
        dest.writeInt(doorsNum);
        dest.writeInt(seatingCapacity);
        dest.writeFloat(vehicleRate);
        dest.writeFloat(price);
        dest.writeString(companyName);
        dest.writeString(companyCity);
        dest.writeString(companyAddress);
        dest.writeInt(compHotline);
        dest.writeFloat(CompRate);
    }
}
