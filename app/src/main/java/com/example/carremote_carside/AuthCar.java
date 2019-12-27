package com.example.carremote_carside;

public class AuthCar {

    private static Car car;

    public static void setCar(Car car){
        AuthCar.car = car;
    }

    public static Car getCar(){
        return AuthCar.car;
    }
}
