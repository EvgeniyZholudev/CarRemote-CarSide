package com.example.carremote_carside;

import java.util.Collection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CarApi {
    @POST("/api/cars/register")
    Call<CarInfo> registerCar(@Body CarInfo car);

    @POST("/api/cars/login")
    Call<Car> loginCar(@Body CarInfo car);

    @POST("/api/cars/{carId}/addUser")
    Call<Car> addUser(@Body UserForCar userForCar, @Path("carId") String id);

    @GET("api/cars/{carId}/state")
    Call<CarInfo> checkState(@Path("carId") String id);

    @POST("/api/cars/{carId}/removeUser")
    Call<Car> deleteUser(@Body UserForCar userForCar, @Path("carId") String id);
}
