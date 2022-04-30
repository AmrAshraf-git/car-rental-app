package com.example.carrental.utility;

import android.widget.EditText;

public class TextValidation {



    public static boolean isFirstnameValid(EditText editText) {
        String regVal = "^[a-zA-Z]{3,}[\\s]?[a-zA-Z]*[\\s]?[a-zA-Z]*$";               // Ends with at least one char
        if (editText.getText().toString().isEmpty()) {
            editText.requestFocus();
            editText.setError("First name is required");
            return false;
        }
        else if (!editText.getText().toString().matches(regVal)) {
            editText.setError("Invalid name");
            editText.requestFocus();
            return false;
        }
        else
            return true;
    }

    public static boolean isLastName(EditText editText) {
        String regVal = "^[a-zA-Z]{3,}[\\s]?[a-zA-Z]*[\\s]?[a-zA-Z]*$";
                /*
                "[a-zA-Z]*" +       // Begin with chars
                "[\\s]{1}" +        // Only one space
                "[a-zA-Z].*";       // Ends with at least one char
                */

        if (editText.getText().toString().isEmpty()) {
            editText.requestFocus();
            editText.setError("Last name is required");
            return false;
        }
        else if (!editText.getText().toString().matches(regVal)) {
            editText.setError("Invalid name");
            editText.requestFocus();
            return false;
        }
        else
            return true;
    }

    public static boolean isEmailValid(EditText editText) {
        String regVal = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (editText.getText().toString().isEmpty()) {
            editText.requestFocus();
            editText.setError("Email is required");
            return false;
        }
        else if (!editText.getText().toString().matches(regVal)) {
            editText.setError("Invalid email");
            editText.requestFocus();
            return false;
        }
        else
            return true;
    }

    public static boolean isPhoneValid(EditText phone){
        if (phone.getText().toString().isEmpty()) {
            phone.requestFocus();
            phone.setError("Phone name is required");
            return false;
        } else
            return true;
    }

    public static boolean isPasswordValid(EditText editText) {
        String regVal = "^" +
                "(?=.*[0-9])" +             // At least one digit
                "(?=.*[a-z])" +             // At least one lowe case
                "(?=.*[A-Z])" +             // At least one upper case
                "(?=.*[@#$%^&+=])" +        // At least one special character
                //"(?=\\S+$)" +               // No white space
                ".{8,}" +                   //  At least eight places
                "$";
        if (editText.getText().toString().isEmpty()) {
            editText.requestFocus();
            editText.setError("password name is required");
            return false;
        }

        else if (!editText.getText().toString().matches(regVal)) {
            editText.setError("Password is too weak");
            editText.requestFocus();
            return false;
        }
        else
            return true;
    }

    public static boolean isConfPasswordSimulate(EditText editText, EditText confPassword) {
        if (editText.getText().toString().isEmpty()) {
            editText.requestFocus();
            editText.setError("password name is required");
            return false;
        }
        else if (confPassword.getText().toString().isEmpty()) {
            confPassword.requestFocus();
            confPassword.setError("Confirm password name is required");
            return false;
        }

        else if (!confPassword.getText().toString().equals(editText.getText().toString())) {
            confPassword.setError("Password doesn't match");
            confPassword.requestFocus();
            return false;
        }
        else
            return true;
    }
}
