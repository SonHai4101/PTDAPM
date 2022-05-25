package view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.R;

import java.util.List;

import model.Product;
import view.HomeScreen;
import view.user.UserIndex;

public class UserAdapter extends BaseAdapter {
    private UserIndex userIndex;
    private int layout;
    private List<Product> products;

    public UserAdapter(UserIndex userIndex, int layout, List<Product> products) {
        this.userIndex = userIndex;
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
        TextView txtName, txtPrice;
        ImageView iv_product;
        Button view;
        Button addcart;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        UserAdapter.ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            // chuyển layout file (xml) thành dạng view
            LayoutInflater inflater = (LayoutInflater) userIndex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtName = (TextView) view.findViewById(R.id.p_name1);
            holder.txtPrice = (TextView) view.findViewById(R.id.p_price);
            holder.iv_product = (ImageView) view.findViewById(R.id.p_img);
            view.setTag(holder);
        } else {
            holder = (UserAdapter.ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        holder.txtName.setText("Name: " + product.getName());
        holder.txtPrice.setText("Price: " + product.getPrice());
        // chuyển ảnh từ dạng byte sang bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        // lấy ảnh dưới dạng bitmap gán vào imageview
        holder.iv_product.setImageBitmap(bitmap);
        holder.view = (Button) view.findViewById(R.id.btn_view);
        holder.view.setOnClickListener(view12 -> userIndex.viewProduct(product.getId()));
        holder.addcart = (Button) view.findViewById(R.id.btn_add);
        holder.addcart.setOnClickListener(view1 -> userIndex.addToCart(product.getId()));

        return view;
    }
}
