package com.example.carrental.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpResponse {

    //private String err;
    private String message;

    @SerializedName("ValidateError")
    @Expose
    private String[] ValidateError;

    private List<NewUser> newUser;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NewUser> getNewUser() {
        return newUser;
    }

    public void setNewUser(List<NewUser> newUser) {
        this.newUser = newUser;
    }

    public String[] getValidateError() {
        return ValidateError;
    }

    public void setValidateError(String[] validateError) {
        ValidateError = validateError;
    }
}
