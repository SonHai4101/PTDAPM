package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.Account;
import sqlite.Sqlite;
import view.account.LoginActivity;
import view.account.RegisterActivity;

public class UserRegister extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        Button addAccount;
        addAccount = (Button) findViewById(R.id.btn_register);
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText id = (EditText) findViewById(R.id.phone);
                EditText username = (EditText) findViewById(R.id.editText_username1);
                EditText password = (EditText) findViewById(R.id.editText_password1);
                EditText confirmpassword = (EditText) findViewById(R.id.editText_confirmpassword1);
                if (!(id.getText().toString().isEmpty() && username.getText().toString().isEmpty() && password.getText().toString().isEmpty())) {
                    if (password.getText().toString().equals(confirmpassword.getText().toString())) {
                        Account newaccound = new Account(Integer.parseInt(id.getText().toString()), username.getText().toString(),
                                password.getText().toString(), "USER");
                        if (newaccound.getPassword().length() >= 6) {
                            sqlite.insertAccount(newaccound);
                            if (sqlite.insertAccount(newaccound) == true) {
                                Toast toast = Toast.makeText(UserRegister.this, "Register successful", Toast.LENGTH_LONG);
                                toast.show();
                                Intent intent = new Intent(UserRegister.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast toast = Toast.makeText(UserRegister.this, "Register failed", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        } else {
                            Toast toast = Toast.makeText(UserRegister.this, "Passwords must be at least 6 characters", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(UserRegister.this, "Passwords do not match", Toast.LENGTH_LONG);
                        toast.show();

                    }
                } else {
                    Toast toast = Toast.makeText(UserRegister.this, "Please enter full information", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}