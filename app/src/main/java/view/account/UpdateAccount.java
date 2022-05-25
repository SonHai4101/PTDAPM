package view.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl.ListAccount;
import com.example.btl.R;

import model.Account;
import model.Product;
import sqlite.Sqlite;
import view.product.ListProduct;
import view.product.UpdateProduct;

public class UpdateAccount extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        EditText phone = (EditText) findViewById(R.id.editText_updatephone);
        phone.setText(String.valueOf(Account.takeid));
        EditText username = (EditText) findViewById(R.id.editText_updateusername);
        EditText password = (EditText) findViewById(R.id.editText_updatepassword);
        EditText confirmpassword = (EditText) findViewById(R.id.editText_confirm_updatepassword);
        EditText role = (EditText) findViewById(R.id.editText_updaterole);
        Button updateaccount = (Button) findViewById(R.id.btn_updateaccount);
        updateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(password.getText().toString().isEmpty() || username.getText().toString().isEmpty() || role.getText().toString().isEmpty())) {
                    if (password.getText().toString().equals(confirmpassword.getText().toString())) {
                        Account account = new Account(Account.takeid, username.getText().toString(), password.getText().toString(), role.getText().toString());
                        if (sqlite.updateAccount(account, Integer.parseInt(phone.getText().toString()))) {
                            Toast toast = Toast.makeText(UpdateAccount.this, "Update success", Toast.LENGTH_LONG);
                            toast.show();
                            Intent intent = new Intent(UpdateAccount.this, ListAccount.class);
                            startActivity(intent);
                        } else {
                            Toast toast = Toast.makeText(UpdateAccount.this, "Update fail", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(UpdateAccount.this, "Password do not match", Toast.LENGTH_LONG);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(UpdateAccount.this, "Please enter full information", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

        });
    }
}