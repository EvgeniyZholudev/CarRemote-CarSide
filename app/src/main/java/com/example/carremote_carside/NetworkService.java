package com.example.carremote_carside;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static final String BASE_URL = "http://car-assistant.us-east-2.elasticbeanstalk.com";
    private static NetworkService networkService;
    private Retrofit retrofit;
    private NetworkService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static NetworkService getInstance(){
        if(networkService == null){
            networkService = new NetworkService();
        }
        return networkService;
    }

    public <T> T getInterface(Class<T> t){
        return retrofit.create(t);
    }
}
