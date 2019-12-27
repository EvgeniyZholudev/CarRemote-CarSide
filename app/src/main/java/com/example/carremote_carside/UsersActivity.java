package com.example.carremote_carside;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private ListView userList;
    private ArrayAdapter<String> adapter;
    private Button addUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        userList = (ListView) findViewById(R.id.userList);
        adapter = new ArrayAdapter<String>(UsersActivity.this,
                android.R.layout.simple_list_item_1);
        userList.setAdapter(adapter);
        adapter.addAll(AuthCar.getCar().getPhoneNumbers());
        addUserButton = (Button) findViewById(R.id.button);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddUserDialog();
            }
        });
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phoneNumber = (String) userList.getItemAtPosition(position);
                showDeleteUserDialog(phoneNumber);
            }
        });

    }

    private void showDeleteUserDialog(final String phoneNumber){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to remove user with phone number " + phoneNumber)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUser(phoneNumber);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private void deleteUser(final String phoneNumber){
        UserForCar userForCar = new UserForCar();
        userForCar.setPhoneNumbers(phoneNumber);
        NetworkService.getInstance()
                .getInterface(CarApi.class)
                .deleteUser(userForCar, AuthCar.getCar().getCarInfo().getId())
                .enqueue(new Callback<Car>() {
                    @Override
                    public void onResponse(Call<Car> call, Response<Car> response) {
                        if(response.body() != null){
                            adapter.remove(phoneNumber);
                        }
                        else{
                            Toast.makeText(UsersActivity.this, "Deleting failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Car> call, Throwable t) {
                        Toast.makeText(UsersActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void addUser(String phoneNumber){
        UserForCar userForCar = new UserForCar();
        userForCar.setPhoneNumbers(phoneNumber);
        NetworkService.getInstance()
                .getInterface(CarApi.class)
                .addUser(userForCar, AuthCar.getCar().getCarInfo().getId())
                .enqueue(new Callback<Car>() {
                    @Override
                    public void onResponse(Call<Car> call, Response<Car> response) {
                        if(response.body() != null){
                            AuthCar.setCar(response.body());
                            adapter.clear();
                            adapter.addAll(AuthCar.getCar().getPhoneNumbers());
                        }
                        else{
                            Toast.makeText(UsersActivity.this, "Adding failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Car> call, Throwable t) {
                        Toast.makeText(UsersActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void showAddUserDialog(){
        final EditText editText = new EditText(this);
        editText.setHint("Phone Number");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input phone number which you want to add")
                .setView(editText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addUser(editText.getText().toString());
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }



}
