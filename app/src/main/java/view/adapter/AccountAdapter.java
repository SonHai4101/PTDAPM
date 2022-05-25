package view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.ListAccount;
import com.example.btl.R;

import java.util.ArrayList;

import model.Account;
public class AccountAdapter extends BaseAdapter {
    ListAccount context;
    int layout;
    ArrayList<Account> accounts;

    public AccountAdapter(ListAccount context, int layout, ArrayList<Account> accounts) {
        this.context = context;
        this.layout = layout;
        this.accounts = accounts;
    }

    @Override
    public int getCount() {
        return accounts.size();
    }

    @Override
    public Object getItem(int i) {
        return accounts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return accounts.get(i).getId();
    }

    private class ViewHolder {
        TextView txt_phone, txt_username, txt_password, txt_role;
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
            holder.txt_phone = (TextView) view.findViewById(R.id.tv_phone);
            holder.txt_username = (TextView) view.findViewById(R.id.tv_username);
            holder.txt_password = (TextView) view.findViewById(R.id.tv_password);
            holder.txt_role = (TextView) view.findViewById(R.id.tv_role);
            holder.delete = (Button) view.findViewById(R.id.bt_deleteaccount);
            holder.edit = (Button) view.findViewById(R.id.bt_updateaccount);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Account account = (Account) getItem(i);
        holder.txt_phone.setText("Phone: " + account.getId());
        holder.txt_username.setText("Username: " + account.getUsername());
        holder.txt_password.setText("Password: " + account.getPassword());
        holder.txt_role.setText("Role: " + account.getRole());
        holder.edit.setOnClickListener(view12 -> {
            context.editAccount(account.getId());
            notifyDataSetChanged();
        });
        holder.delete.setOnClickListener(view1 -> {
            context.dialogDeleteAccount(account.getId());
            notifyDataSetChanged();
        });
        return view;
    }
}
