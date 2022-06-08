package com.example.btl.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.btl.R;
import com.example.btl.model.Cart;
import com.example.btl.model.Product;
import com.example.btl.sqlite.Sqlite;
import com.example.btl.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

import okhttp3.internal.Util;

public class ProductDetail extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    Button btnadd;
    Spinner spinner;
    Product product;
    TextView pname, pprice, pdes;
    ImageView imghinhanh;
//    Toolbar toolbar;
    NotificationBadge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
//        Product product = new Product();
        product = sqlite.getProduct(Product.takeid);
        TextView name = (TextView) findViewById(R.id.txt_product_name);
        name.setText(product.getName());
        TextView price = (TextView) findViewById(R.id.txtprice);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        price.setText(decimalFormat.format(Double.parseDouble(product.getPrice())) + "đ");
        TextView des = (TextView) findViewById(R.id.txtmotachitiet);
        des.setText(product.getDes());
        ImageView pimg = (ImageView) findViewById(R.id.imgchitiet);
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        pimg.setImageBitmap(bitmap);



        innitView();
        initControl();

        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, so);
        spinner.setAdapter(adapterspin);
    }

    private void innitView() {
        pname = findViewById(R.id.txt_product_name);
//        pprice = findViewById(R.id.txtprice);
        pdes = findViewById(R.id.txtmotachitiet);
        btnadd = findViewById(R.id.btn_add_to_cart);
        spinner = findViewById(R.id.spinner);
        imghinhanh = findViewById(R.id.imgchitiet);
//        toolbar = findViewById(R.id.toobar);
//        product = new Product();
        badge = findViewById(R.id.menu_sl);
        if (Utils.manggiohang != null){
            badge.setText(String.valueOf(Utils.manggiohang.size()));
        }
    }

    private void initControl() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addtoCart();
            }
        });
    }

    private void addtoCart() {
        if (Utils.manggiohang.size() > 0){
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0; i < Utils.manggiohang.size(); i++) {
                if (Utils.manggiohang.get(i).getIdsp() == product.getId()){
                    Utils.manggiohang.get(i).setSoluong(soluong + Utils.manggiohang.get(i).getSoluong());
                    double gia = Double.parseDouble(product.getPrice()) * Utils.manggiohang.get(i).getSoluong();
                    Utils.manggiohang.get(i).setGiasp(gia);
                    flag = true;
                }
            }
            if (flag == false){
                double gia = Double.parseDouble(product.getPrice()) * soluong;
                Cart cart = new Cart();
                cart.setGiasp(gia);
                cart.setSoluong(soluong);
                cart.setIdsp(product.getId());
                cart.setTensp(product.getName());
                cart.setHinhsp(product.getImage());
                Utils.manggiohang.add(cart);
            }
        } else {
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            double gia = Double.parseDouble(product.getPrice()) * soluong;
            Cart cart = new Cart();
            cart.setGiasp(gia);
            cart.setSoluong(soluong);
            cart.setIdsp(product.getId());
            cart.setTensp(product.getName());
            cart.setHinhsp(product.getImage());
            Utils.manggiohang.add(cart);
        }
        badge.setText(String.valueOf(Utils.manggiohang.size()));
    }
}