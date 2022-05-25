package view.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl.MainActivity;
import com.example.btl.R;
import com.example.btl.UserRegister;

import model.Account;
import model.Product;
import security.SessionManager;
import sqlite.Sqlite;
import view.HomeScreen;
import view.user.UserIndex;

public class LoginActivity extends AppCompatActivity {

    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);

    EditText username, password;
    Button btnlogin, btnregister;
    Account account = new Account();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        btnlogin = (Button) findViewById(R.id.btn_sign_in);
        btnregister = (Button) findViewById(R.id.btn_sign_up1);
        btnregister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, UserRegister.class)));
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checker = sqlite.checker(user, pass);
                    if (checker == true) {
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        account = sqlite.getAccount(user, pass);
                        if (account.getRole().equals("ADMIN")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else if (account.getRole().equals("USER")){
                            Intent intent = new Intent(LoginActivity.this, UserIndex.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}