package view.product;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl.R;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;

import sqlite.Sqlite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import model.Product;

public class AddProduct extends AppCompatActivity {
    Button add, gallery;
    Product product = new Product();
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        gallery = (Button) findViewById(R.id.btn_add_upload_image);
        add = (Button) findViewById(R.id.btn_add_new_product);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertProduct();
            }
        });
        insertProduct();
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lấy ảnh từ thư viện ảnh của điện thoại
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mActivityResultLauncher.launch(intent);
            }
        });

    }

    // thay thế cho hàm startActivityForResult()
    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                    }
                }
            });

    //  lấy byte của ảnh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectImage = data.getData();
        if (resultCode == RESULT_OK && data != null) {
            ImageView imageView = (ImageView) findViewById(R.id.img_product);
            try {
                InputStream inputStream = getContentResolver().openInputStream(selectImage);
                // chuyển đổi inputeSream thành dạng byte
                byte[] bytes = IOUtils.toByteArray(inputStream);
                // chuyển dạng byte thành bitmap
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bitmap);
                product.setImage(bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void insertProduct() {
        add.setOnClickListener(view -> {
            EditText id = (EditText) findViewById(R.id.edit_txt_id);
            EditText name = (EditText) findViewById(R.id.edit_txt_name);
            EditText quantity = (EditText) findViewById(R.id.edit_txt_quantity);
            EditText price = (EditText) findViewById(R.id.edit_txt_price);
            Product newproduct = new Product(id.getText().toString(),
                    name.getText().toString(), Integer.parseInt(quantity.getText().toString()),
                    price.getText().toString(), product.getImage());
            sqlite.insertProduct(newproduct);
            if (sqlite.insertProduct(newproduct) == true) {
                Toast toast = Toast.makeText(AddProduct.this, "Thêm thành công", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(AddProduct.this, ListProduct.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(AddProduct.this, "Thêm thất bại", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}