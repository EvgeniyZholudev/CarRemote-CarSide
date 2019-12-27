package com.example.carremote_carside;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserForCar {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumbers;

    public UserForCar() {
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
