package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.btl.R;

import java.util.ArrayList;

import model.Product;
import sqlite.Sqlite;

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

    private void ClickMuaHang() {
        btnmuahang.setOnClickListener(v -> startActivity(new Intent(ShoppingCart.this, payment.class)));// chuyen trang Cart
    }


}