package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import com.example.btl.MainActivity;
import com.example.btl.R;
import view.payment;

import sqlite.Sqlite;
import view.product.AddProduct;

public class payment extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale", null, 1);
    Button dathang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        SQLiteDatabase sqLiteDatabase = sqlite.getReadableDatabase();
        sqlite.onCreate(sqLiteDatabase);
        dathang = (Button) findViewById(R.id.btn_dathang);


        Button btn_dathang = findViewById(R.id.btn_dathang);// anh xa
        btn_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(payment.this, "Đặt hàng thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(payment.this, HomeScreen.class));
            }
        });
    }




}