package com.example.carrental;

public class HomeListItem {
    private String companyName;
    private String carModel;
    private int carImg;
    private double price;
    private PriceLabel priceLabel;
    private String[] specs;
    private String[] bookDetails;

    public HomeListItem() {
        carImg= R.drawable.img_logo_test;
        priceLabel=PriceLabel.DOLLAR;

    }

    public PriceLabel getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(PriceLabel priceLabel) {
        this.priceLabel = priceLabel;
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
}
