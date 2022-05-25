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
import view.product.ListProduct;

public class ProductAdapter extends BaseAdapter {
    ListProduct context;
    int layout;
    ArrayList<Product> products;


    public ProductAdapter(ListProduct context, int layout, ArrayList<Product> products) {
        this.context = context;
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
        TextView txt_id, txt_name, txt_quantity, txt_price;
        ImageView iv_product;
        Button delete, edit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            // chuyển layout file (xml) thành dạng view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt_id = (TextView) view.findViewById(R.id.idproduct);
            holder.txt_name = (TextView) view.findViewById(R.id.nameproduct);
            holder.iv_product = (ImageView) view.findViewById(R.id.iv_product16);
            holder.txt_quantity = (TextView) view.findViewById(R.id.quantityproduct);
            holder.txt_price = (TextView) view.findViewById(R.id.priceproduct);
            holder.delete = (Button) view.findViewById(R.id.bt_delete);
            holder.edit = (Button) view.findViewById(R.id.bt_update);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        holder.txt_id.setText("ID: " + product.getId());
        holder.txt_name.setText("Name: " + product.getName());
        holder.txt_price.setText("Price" + product.getPrice() + "₫");
        holder.txt_quantity.setText("Quantity: " + String.valueOf(product.getQuantity()));
        // lấy ảnh từ cơ sở dữ liệu
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        holder.iv_product.setImageBitmap(bitmap);
        holder.edit.setOnClickListener(view12 -> {
            context.editProduct(product.getId());
            notifyDataSetChanged();
        });
        holder.delete.setOnClickListener(view1 -> {
            context.dialogDeleteProduct(product.getId());
            notifyDataSetChanged();
        });
        return view;
    }

}
