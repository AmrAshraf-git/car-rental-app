package com.example.carrental.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleSpecs {

    @SerializedName("airbag")
    @Expose
    private Boolean airBag; //13

    @SerializedName("seatbelts")
    @Expose
    private Boolean seatBelts; //14

    @SerializedName("ABS")
    @Expose
    private Boolean ABS; //15

    @SerializedName("sunroof")
    @Expose
    private Boolean sunRoof; //16

    @SerializedName("Parking_Sensors")
    @Expose
    private Boolean parkingSensors; //17

    @SerializedName("Radio")
    @Expose
    private Boolean radio; //18

    @SerializedName("Bluetooth")
    @Expose
    private Boolean bluetooth; //19

    @SerializedName("Navigation_System")
    @Expose
    private Boolean navSystem; //20

    @SerializedName("Remote_Start")
    @Expose
    private Boolean remoteStart; //21

    @SerializedName("AC")
    @Expose
    private Boolean AC; //22

    @SerializedName("Music_Player")
    @Expose
    private Boolean musicPlayer; //23

    @SerializedName("Automatic")
    @Expose
    private Boolean automaticTransmission; //24

    @SerializedName("Extra_Tyre")
    @Expose
    private Boolean extraTyre; //25

    @SerializedName("Charger")
    @Expose
    private Boolean charger; //26

    @SerializedName("Fire_Extinguisher")
    @Expose
    private Boolean fireExtinguisher; //27

    @SerializedName("First_Aid_Kit")
    @Expose
    private Boolean firstAidKit; //28


    private Boolean carSeat; //29

    @SerializedName("Smoking_Preferences")
    @Expose
    private Boolean smokingPreferences; //30

    @SerializedName("CC")
    @Expose
    private int CC; //31


    public void addSafetySpecs(Boolean airBag, Boolean seatBelts, Boolean ABS) {
        this.airBag = airBag;
        this.seatBelts = seatBelts;
        this.ABS = ABS;
    }

    public void addFeatures(Boolean sunRoof, Boolean parkingSensors, Boolean radio
            , Boolean bluetooth, Boolean smokingPreferences
            , Boolean navSystem, Boolean remoteStart, Boolean AC, Boolean musicPlayer) {
        this.sunRoof = sunRoof;
        this.parkingSensors = parkingSensors;
        this.radio = radio;
        this.bluetooth = bluetooth;
        this.smokingPreferences = smokingPreferences;
        this.navSystem = navSystem;
        this.remoteStart = remoteStart;
        this.AC = AC;
        this.musicPlayer = musicPlayer;
    }


    //setters
    public void addEngineSpecs(Boolean automaticTransmission, int CC) {
        this.automaticTransmission = automaticTransmission;
        this.CC = CC;
    }

    public void Accessories(Boolean extraTyre, Boolean charger, Boolean fireExtinguisher
            , Boolean firstAidKit, Boolean carSeat) {
        this.charger = charger;
        this.extraTyre = extraTyre;
        this.fireExtinguisher = fireExtinguisher;
        this.firstAidKit = firstAidKit;
        this.carSeat = carSeat;
    }


    //getters
    public Boolean getAirBag() {
        return airBag;
    }

    public Boolean getSeatBelts() {
        return seatBelts;
    }

    public Boolean getABS() {
        return ABS;
    }

    public Boolean getSunRoof() {
        return sunRoof;
    }

    public Boolean getParkingSensors() {
        return parkingSensors;
    }

    public Boolean getRadio() {
        return radio;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public Boolean getNavSystem() {
        return navSystem;
    }

    public Boolean getRemoteStart() {
        return remoteStart;
    }

    public Boolean getAC() {
        return AC;
    }

    public Boolean getMusicPlayer() {
        return musicPlayer;
    }

    public Boolean getAutomaticTransmission() {
        return automaticTransmission;
    }

    public Boolean getExtraTyre() {
        return extraTyre;
    }

    public Boolean getCharger() {
        return charger;
    }

    public Boolean getFireExtinguisher() {
        return fireExtinguisher;
    }

    public Boolean getFirstAidKit() {
        return firstAidKit;
    }

    public Boolean getCarSeat() {
        return carSeat;
    }

    public Boolean getSmokingPreferences() {
        return smokingPreferences;
    }

    public int getCC() {
        return CC;
    }
}
