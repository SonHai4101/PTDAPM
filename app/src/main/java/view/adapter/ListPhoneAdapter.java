package view.adapter;


import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.R;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Product;
import view.HomeScreen;
import view.PhoneList;
import view.product.ListProduct;

public class ListPhoneAdapter extends BaseAdapter {
    PhoneList phoneList;
    int layout;
    ArrayList<Product> products;


    public ListPhoneAdapter(PhoneList phoneList, int layout, ArrayList<Product> products) {
        this.phoneList = phoneList;
        this.layout = layout;
        this.products = products;
    }




    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Integer.parseInt(products.get(i).getId());
    }

    private class ViewHolder {
        TextView txt_name,  txt_price;
        ImageView iv_product;
        Button view;
        Button addcart;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            // chuyển layout file (xml) thành dạng view
            LayoutInflater inflater = (LayoutInflater) phoneList.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt_name = (TextView) view.findViewById(R.id.p_name1);
            holder.txt_price = (TextView) view.findViewById(R.id.p_price);
            holder.iv_product = (ImageView) view.findViewById(R.id.p_img);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        holder.txt_name.setText("Name: " + product.getName());
        holder.txt_price.setText("Price: " + product.getPrice() + "₫");
        // chuyển ảnh từ dạng byte sang bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        // lấy ảnh dưới dạng bitmap gán vào imageview
        holder.iv_product.setImageBitmap(bitmap);
        holder.view = (Button) view.findViewById(R.id.btn_view);
        holder.view.setOnClickListener(view12 -> phoneList.viewProduct(product.getId()));
        holder.addcart = (Button) view.findViewById(R.id.btn_add);
        holder.addcart.setOnClickListener(view1 -> phoneList.addToCart(product.getId()));
        return view;
    }

}
