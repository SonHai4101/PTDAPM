
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
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl.R;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import model.Product;
import sqlite.Sqlite;

public class UpdateProduct extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    Button upload;
    ImageView imageView, updateview;
    Product product = new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        EditText id = (EditText) findViewById(R.id.update_id);
        id.setText(Product.takeid);
        EditText name = (EditText) findViewById(R.id.update_name);
        EditText quantity = (EditText) findViewById(R.id.update_quantity);
        EditText price = (EditText) findViewById(R.id.update_price);
        updateview = (ImageView) findViewById(R.id.update_img_product);
        upload = (Button) findViewById(R.id.btn_update_upload_image);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lấy ảnh từ thư viện của điện thoại
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mActivityResultLauncher.launch(intent);
            }
        });
        Button update = (Button) findViewById(R.id.btn_update_product);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product updateproduct = new Product(id.getText().toString(), name.getText().toString()
                        , Integer.parseInt(quantity.getText().toString()), price.getText().toString(), product.getImage());
                if (sqlite.updateProduct(updateproduct, id.getText().toString())) {
                    Toast toast = Toast.makeText(UpdateProduct.this, "Update success", Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent(UpdateProduct.this, ListProduct.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(UpdateProduct.this, "Update false", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectImage = data.getData();
        if (resultCode == RESULT_OK && data != null) {
            ImageView imageView = (ImageView) findViewById(R.id.img_product);
            try {
                InputStream inputStream = getContentResolver().openInputStream(selectImage);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                updateview.setImageBitmap(bitmap);
                product.setImage(bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

}