package com.example.btl.view.product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.btl.R;
import com.example.btl.model.Product;
import com.example.btl.sqlite.Sqlite;
import com.example.btl.view.adapter.ProductAdapter;

import java.util.ArrayList;

public class ListProduct extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    ListView lvproduct;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        lvproduct = findViewById(R.id.lv_product);
        products = sqlite.getAllProduct();
        lvproduct.setAdapter(new ProductAdapter(ListProduct.this, R.layout.item_contact, products));
        ImageView addnewproduct = (ImageView) findViewById(R.id.img_add);
        addnewproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProduct.this, AddProduct.class);
                startActivity(intent);
            }
        });
    }


    public void dialogDeleteProduct(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete this product ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            sqlite.queryData("DELETE FROM PRODUCT WHERE P_ID LIKE '" + id + "'");
            sqlite.getAllProduct();
            startActivity(new Intent(ListProduct.this, ListProduct.class));
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });
        builder.show();
    }


    public void editProduct(String id) {
        Product.takeid = id;
        Intent intent = new Intent(this, UpdateProduct.class);
        startActivity(intent);
    }

}
