package view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.R;

import org.w3c.dom.Text;

import model.Product;
import sqlite.Sqlite;

public class ProductDetail extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Product product = new Product();
        product = sqlite.getProduct(Product.takeid);
        TextView name = (TextView) findViewById(R.id.txt_product_name);
        name.setText(product.getName());
        TextView price = (TextView) findViewById(R.id.txtprice);
        price.setText(product.getPrice());
        ImageView pimg = (ImageView) findViewById(R.id.imgchitiet);
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        pimg.setImageBitmap(bitmap);
    }
}