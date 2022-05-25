package view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.btl.MainActivity;
import com.example.btl.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import security.SessionManager;
import view.account.LoginActivity;
import view.adapter.ListProductAdapter;
import view.adapter.MenuAdapter;
import model.Product;
import sqlite.Sqlite;

public class HomeScreen extends AppCompatActivity {
    SessionManager sessionManager;
    Sqlite sqlite = new Sqlite(this, "AppElectronicsDevicesSale.sqlite", null, 1);
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listViewTrangChu;
    DrawerLayout drawerLayout;
    ArrayList<ItemMenu> arrayList;
    MenuAdapter adapter;
    List<Product> products;
    ListView listView;
    Product product = new Product();
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Anhxa();
        ActionBar();
        ActionViewFlipper();
        ActionMenu();
        getEventClick();
        listView = findViewById(R.id.listview_item);
        products = sqlite.getAllProduct();
        listView.setAdapter(new ListProductAdapter(HomeScreen.this, R.layout.single_item, products));
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

    // bat su kien vao list
    private void getEventClick() {
        listViewTrangChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(), view.HomeScreen.class);
                        startActivity(trangchu);
                        break;
                    case 1:
                        Intent dienthoai = new Intent(getApplicationContext(), PhoneList.class);
                        startActivity(dienthoai);
                        break;
                    case 2:
                        Intent laptop = new Intent(getApplicationContext(), LaptopList.class);
                        startActivity(laptop);
                        break;
                    case 3:
                        Intent lienhe = new Intent(getApplicationContext(), Contact.class);
                        startActivity(lienhe);
                        break;
                    case 4:
                        Intent thongtin = new Intent(getApplicationContext(), Infor.class);
                        startActivity(thongtin);
                        break;
                    case 5:
                        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(login);
                        break;
                }
            }
        });
    }

    private void ActionMenu() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemMenu("Home", R.drawable.home));
        arrayList.add(new ItemMenu("SmartPhone", R.drawable.smartphone));
        arrayList.add(new ItemMenu("Laptop", R.drawable.laptop));
        arrayList.add(new ItemMenu("Contact", R.drawable.support));
        arrayList.add(new ItemMenu("Information", R.drawable.info));
        arrayList.add(new ItemMenu("Login", R.drawable.login));
        adapter = new MenuAdapter(this, R.layout.item_rows, arrayList);
        listViewTrangChu.setAdapter(adapter);
    }

    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn.cellphones.com.vn/media/ltsoft/promotion/iPhone_11.png");
        mangquangcao.add("https://cdn.cellphones.com.vn/media/ltsoft/promotion/S22_uLTRA.png");
        mangquangcao.add("https://cdn.cellphones.com.vn/media/ltsoft/promotion/Note_11_pro_plus.png");
        mangquangcao.add("https://cdn.cellphones.com.vn/media/ltsoft/promotion/TV.png");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbartrangchu);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewTrangChu = findViewById(R.id.listviewtrangchu);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
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