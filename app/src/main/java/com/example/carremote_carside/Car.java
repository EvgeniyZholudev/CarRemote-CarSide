package com.example.carremote_carside;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Car {

    @SerializedName("car")
    @Expose
    private CarInfo carInfo;
    @SerializedName("phoneNumbers")
    @Expose
    private List<String> phoneNumbers;

    public Car(){
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }


    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

}
