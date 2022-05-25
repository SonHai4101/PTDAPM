package view.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl.R;

import model.Account;
import sqlite.Sqlite;
import view.HomeScreen;

public class RegisterActivity extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button addAccount;
        addAccount = (Button) findViewById(R.id.btn_add_new_account);
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText id = (EditText) findViewById(R.id.editText_id);
                EditText username = (EditText) findViewById(R.id.editText_username);
                EditText password = (EditText) findViewById(R.id.editText_password);
                EditText confirmpassword = (EditText) findViewById(R.id.editText_confirm_password);
                EditText role = (EditText) findViewById(R.id.editText_role);
                if (!(id.getText().toString().isEmpty() && username.getText().toString().isEmpty() && id.getText().toString().isEmpty())) {
                    if (password.getText().equals(confirmpassword.getText())) {
                        Account newaccound = new Account(Integer.parseInt(id.getText().toString()), username.getText().toString(),
                                password.getText().toString(), role.getText().toString());
                        if (newaccound.getPassword().length() >= 6) {
                            sqlite.insertAccount(newaccound);
                            if (sqlite.insertAccount(newaccound) == true) {
                                Toast toast = Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_LONG);
                                toast.show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast toast = Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        } else {
                            Toast toast = Toast.makeText(RegisterActivity.this, "Passwords must be at least 6 characters", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }
                else {
                    Toast toast = Toast.makeText(RegisterActivity.this, "Please enter full information", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

}