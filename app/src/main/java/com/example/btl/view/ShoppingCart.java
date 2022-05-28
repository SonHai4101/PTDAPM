package com.example.btl.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.R;
import com.example.btl.model.Product;
import com.example.btl.sqlite.Sqlite;
import com.example.btl.view.product.ListProduct;

public class ShoppingCart extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    TextView name, price;
    ImageView cartproduct;
    Button btnmuahang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Product product = sqlite.getProduct(Product.takeid);
        name = findViewById(R.id.cart_nameProduct);
        name.setText(product.getName());
        price = findViewById(R.id.cart_price);
        price.setText("Price: " + product.getPrice() + " VND");
        cartproduct = (ImageView) findViewById(R.id.cart_image);
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        cartproduct.setImageBitmap(bitmap);
        btnmuahang = (Button) findViewById(R.id.btnmuahang);
        ClickMuaHang();
    }

    public void dialogDeleteProduct(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete this product ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            sqlite.queryData("DELETE FROM PRODUCT WHERE P_ID LIKE '" + id + "'");
            sqlite.getAllProduct();
            startActivity(new Intent(ShoppingCart.this, ShoppingCart.class));
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });
        builder.show();
    }

    private void ClickMuaHang() {
        btnmuahang.setOnClickListener(v -> startActivity(new Intent(ShoppingCart.this, payment.class)));// chuyen trang Cart
    }


}