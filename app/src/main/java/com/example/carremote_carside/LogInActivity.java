package com.example.carremote_carside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    private Button button;
    private EditText plateNumberEditText;
    private Context context = this;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Button button = (Button) findViewById(R.id.button);
        plateNumberEditText = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLogInData();

            }
        });
    }

    private void sendLogInData(){
        CarInfo car = new CarInfo();
        car.setPlateNumber(plateNumberEditText.getText().toString());
        NetworkService.getInstance()
                .getInterface(CarApi.class)
                .loginCar(car)
                .enqueue(new Callback<Car>() {
                    @Override
                    public void onResponse(Call<Car> call, Response<Car> response) {
                        if(response.body() != null){
                            AuthCar.setCar(response.body());
                            Intent intent = new Intent(LogInActivity.this, StateActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(context, "Log In failed!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Car> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
