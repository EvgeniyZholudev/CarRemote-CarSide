package com.example.carremote_carside;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarInfo {

    @SerializedName("carId")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("plateNumber")
    @Expose
    private String plateNumber;
    @SerializedName("doorsState")
    @Expose
    private String doorState;

    public CarInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDoorState() {
        return doorState;
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState;
    }
}
