package com.example.carremote_carside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StateActivity extends AppCompatActivity {

    private Button usersListButton;
    private boolean isRunning;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        startUdate();
        usersListButton = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.imageView);
        usersListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });
    }

    private void changeActivity(){
        Intent intent = new Intent(StateActivity.this, UsersActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopUpdate();
    }

    private void startUdate(){
        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isRunning) {
                        NetworkService.getInstance()
                                .getInterface(CarApi.class)
                                .checkState(AuthCar.getCar().getCarInfo().getId())
                                .enqueue(new Callback<CarInfo>() {
                                    @Override
                                    public void onResponse(Call<CarInfo> call, Response<CarInfo> response) {
                                        if (response.body() != null) {
                                            AuthCar.getCar().setCarInfo(response.body());
                                            changeImage();
                                        } else {
                                            Toast.makeText(StateActivity.this, "Status update failed!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CarInfo> call, Throwable t) {
                                        Toast.makeText(StateActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                        Thread.sleep(500);
                    }
                } catch (Exception exception) {
                    Toast.makeText(StateActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    public void stopUpdate(){
        isRunning = false;
    }

    private void changeImage(){
        if(AuthCar.getCar().getCarInfo().getDoorState().equals("unlocked")){
            image.setImageResource(R.drawable.unlock);
        }
        else {
            image.setImageResource(R.drawable.lock);
        }
    }

}
