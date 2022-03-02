package com.example.carrental.dataModels;

public class VehicleSpecs {
    private Boolean airBag,seatBelts,ABS,sunRoof
            ,parkingSensors,radio, bluetooth
            ,navSystem,remoteStart,AC,musicPlayer,automaticTransmission
            ,extraTyre, charger,fireExtinguisher,firstAidKit,carSeat,smokingPreferences;
    private int CC;

    public void addSafetySpecs(Boolean airBag, Boolean seatBelts, Boolean ABS)
    {
        this.airBag = airBag;
        this.seatBelts = seatBelts;
        this.ABS = ABS;
    }

    public void addFeatures(Boolean sunRoof, Boolean parkingSensors, Boolean radio
            , Boolean bluetooth, Boolean smokingPreferences
            , Boolean navSystem, Boolean remoteStart, Boolean AC, Boolean musicPlayer)
    {
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
    public void addEngineSpecs(Boolean automaticTransmission, int CC)
    {
        this.automaticTransmission = automaticTransmission;
        this.CC=CC;
    }
    public void Accessories(Boolean extraTyre, Boolean charger, Boolean fireExtinguisher
            , Boolean firstAidKit, Boolean carSeat)
    {
        this.charger = charger;
        this.extraTyre=extraTyre;
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
