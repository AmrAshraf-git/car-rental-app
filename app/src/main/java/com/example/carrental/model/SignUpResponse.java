package com.example.carrental.model;

public class SignUpResponse {

    private String err;
    private String message;
    private String[] ValidateError;
    private Object[] newUser;

    public Object[] getNewUser() {
        return newUser;
    }

    public void setNewUser(Object[] newUser) {
        this.newUser = newUser;
    }

    public void setNewUser(String[] newUser) {
        this.newUser = newUser;
    }


    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getValidateError() {
        return ValidateError;
    }

    public void setValidateError(String[] validateError) {
        ValidateError = validateError;
    }
}
