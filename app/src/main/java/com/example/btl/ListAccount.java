package com.example.btl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.btl.model.Account;
import com.example.btl.sqlite.Sqlite;
import com.example.btl.view.account.RegisterActivity;
import com.example.btl.view.account.UpdateAccount;
import com.example.btl.view.adapter.AccountAdapter;

public class ListAccount extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    ListView lvaccount;
    ArrayList<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_account);
        lvaccount = findViewById(R.id.lv_account);
        accounts = sqlite.getAllAccount();
        lvaccount.setAdapter(new AccountAdapter(ListAccount.this, R.layout.acount_row, accounts));
        ImageView addnewaccount = (ImageView) findViewById(R.id.img_add_accound);
        addnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListAccount.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void editAccount(int id) {
        Account.takeid = id;
        Intent intent = new Intent(ListAccount.this, UpdateAccount.class);
        startActivity(intent);
    }

    public void dialogDeleteAccount(int id) {
        Account.takeid = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete this account ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            sqlite.queryData("DELETE FROM ACCOUNT WHERE U_ID LIKE '" + id + "'");
            sqlite.getAllProduct();
            startActivity(new Intent(ListAccount.this, ListAccount.class));
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });
        builder.show();
    }
}