package com.example.carremote_carside;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private Button button;
    private EditText nameEditText;
    private EditText plateNumberEditText;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        nameEditText = (EditText) findViewById(R.id.editText);
        plateNumberEditText = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRegistrationData();
            }
        });
    }

    private void sendRegistrationData(){
        final CarInfo regCar = new CarInfo();
        regCar.setName(nameEditText.getText().toString());
        regCar.setPlateNumber(plateNumberEditText.getText().toString());
        NetworkService.getInstance()
                .getInterface(CarApi.class)
                .registerCar(regCar)
                .enqueue(new Callback<CarInfo>() {
                    @Override
                    public void onResponse(Call<CarInfo> call, Response<CarInfo> response) {
                        if(response.body() != null){
                            AuthCar.getCar().setCarInfo(response.body());
                            AuthCar.getCar().setPhoneNumbers(Collections.EMPTY_LIST);
                            Intent intent = new Intent(RegistrationActivity.this, StateActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(context, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<CarInfo> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }


}
