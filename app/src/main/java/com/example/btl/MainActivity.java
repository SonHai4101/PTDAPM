package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import com.example.btl.sqlite.Sqlite;
import com.example.btl.view.HomeScreen;
import com.example.btl.view.admin.AdminIndex;
import com.example.btl.view.product.ListProduct;
import com.example.btl.view.ShoppingCart;

public class MainActivity extends AppCompatActivity {

    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale", null, 1);
    Button login, account, homescreen, addproduct, cart, listproduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        sqlite.onCreate(sqLiteDatabase);
//        login = (Button) findViewById(R.id.btn_sign_in);
        account = (Button) findViewById(R.id.btn_listaccount);
        homescreen = (Button) findViewById(R.id.btn_home_screen);
        listproduct = (Button) findViewById(R.id.btn_listproduct1);
        cart = (Button) findViewById(R.id.btn_cart);
        ClickHomescreen();
//        ClickLogin();
        ClickRegister();
        ClickCart();
        ClickList();
//        AddProduct();
    }



    //bắt sự kiện ShopingCart
    private void ClickCart() {
        cart.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ShoppingCart.class)));// chuyen trang Cart
    }

    private void ClickList() {
        listproduct.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListProduct.class)));

    }


//    private void AddProduct() {
//        addproduct.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddProduct.class)));
//    }

    private void ClickHomescreen() {
        homescreen.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AdminIndex.class)));// chuyen trang home
    }


    // bắt sự kiện onclick login
//    public void ClickLogin() {
//        login.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));// chuyen trang
//    }
//
//    // bắt sự kiện onclick register
    public void ClickRegister() {
        account.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListAccount.class)));
    }



}