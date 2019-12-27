package com.example.carremote_carside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button logInButton;
    private Button registrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AuthCar.setCar(new Car());
        logInButton = (Button) findViewById(R.id.button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(LogInActivity.class);
            }
        });
        registrationButton = (Button) findViewById(R.id.button2);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(RegistrationActivity.class);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AuthCar.setCar(null);
    }

    

    private void changeActivity(Class c){
        Intent intent = new Intent(MainActivity.this, c);
        startActivity(intent);
    }
}
