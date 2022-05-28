package com.example.btl.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.btl.R;
import com.example.btl.model.Product;
import com.example.btl.sqlite.Sqlite;
import com.example.btl.view.adapter.ListPhoneAdapter;

import java.util.ArrayList;

public class PhoneList extends AppCompatActivity {

    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    ListView lvphone;
    ArrayList<Product> products;
    ImageView imageView;
    Product product = new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);

        lvphone = findViewById(R.id.lv_phone);
        products = sqlite.getAllProduct();
        lvphone.setAdapter(new ListPhoneAdapter(PhoneList.this, R.layout.single_item, products));
   }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            products = sqlite.getAllProduct();
            Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
            imageView.setImageBitmap(bitmap);
        }
    }

    public void viewProduct(String id) {
        Product.takeid = id;
        Intent intent = new Intent(this, ProductDetail.class);
        startActivity(intent);
    }
    public void addToCart(String id){
        Product.takeid = id;
        Intent intent = new Intent(this, ShoppingCart.class);
        startActivity(intent);
    }
}