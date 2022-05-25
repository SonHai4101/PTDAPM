package view.product;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl.R;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;

import sqlite.Sqlite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Product;
import view.adapter.ProductAdapter;

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
