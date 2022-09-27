package com.example.carrental.model;

public interface OnApiResponse {

    void onResponseListener(Object object);
    void onFailureListener(Throwable throwable);
}
