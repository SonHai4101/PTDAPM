package view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.R;

import java.util.List;

import view.ItemMenu;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemMenu> list;

    public MenuAdapter(Context context, int layout, List<ItemMenu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            // chuyển layout file (xml) thành dạng view
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_rows, null);

            viewHolder.tv = (TextView) view.findViewById(R.id.tvItem);
            viewHolder.img = view.findViewById(R.id.imgicon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.tv.setText(list.get(i).ItemName);
        viewHolder.img.setImageResource(list.get(i).icon);
        return view;
    }
}
