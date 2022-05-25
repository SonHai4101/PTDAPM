package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Product;

public class Sqlite extends SQLiteOpenHelper {
    String allColumns[] = {"p_id, p_name, p_quantity, p_price, p_img"};
    String allColumnsA[] = {"u_id, u_username, u_password, u_role"};

    public Sqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "AppElectronicsDevicesSale.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT(p_id VARCHAR(50) NOT NULL PRIMARY KEY, p_name VARCHAR(50)," +
                "p_quantity INTEGER, p_price VARCHAR(50), p_img BLOB)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ACCOUNT(u_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, u_username VARCHAR(50), " +
                "u_password VARCHAR(50), u_role VARCHAR(50))");
//        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CART(C_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, C_P_ID VARCHAR(50), C_U_ID INTEGER, FOREIGN KEY (C_P_ID) REFERENCES PRODUCT(P_ID), C_");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCT");
    }

    // ko tra ket qua
    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // tra ket qua
    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public boolean insertProduct(Product product) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_id", product.getId());
        contentValues.put("p_name", product.getName());
        contentValues.put("p_quantity", product.getQuantity());
        contentValues.put("p_price", product.getPrice());
        contentValues.put("p_img", product.getImage());
        database.insert("PRODUCT", null, contentValues);
        database.close();
        return true;
    }

    public boolean updateProduct(Product product, String id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_id", product.getId());
        contentValues.put("p_name", product.getName());
        contentValues.put("p_quantity", product.getQuantity());
        contentValues.put("p_price", product.getPrice());
        contentValues.put("p_img", product.getImage());
        database.update("product", contentValues,
                "p_id = ?", new String[]{id});
        database.close();
        return true;
    }

    public int deleteProduct(String id) {
        SQLiteDatabase database = getReadableDatabase();
        database.delete("PRODUCT", "p_id = ?", new String[]{id});
        return 1;
    }

    public Product getProduct(String id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM PRODUCT WHERE P_ID = ?", new String[]{id});
        if (cursor != null)
            cursor.moveToFirst();
        Product product = cursorToProduct(cursor);
        database.close();
        return product;
    }

    public ArrayList<Product> getAllProduct() {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<Product> products = new ArrayList<>();
        products.clear();
        Cursor cursor = database.query("product", allColumns, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = cursorToProduct(cursor);
            products.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return products;
    }

    private Product cursorToProduct(Cursor cursor) {
        Product product = new Product();
        product.setId(cursor.getString(0));
        product.setName(cursor.getString(1));
        product.setQuantity(cursor.getInt(2));
        product.setPrice(cursor.getString(3));
        product.setImage(cursor.getBlob(4));
        return product;
    }

    public boolean insertAccount(Account account) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("u_id", account.getId());
        contentValues.put("u_username", account.getUsername());
        contentValues.put("u_password", account.getPassword());
        contentValues.put("u_role", account.getRole());
        database.insert("ACCOUNT", null, contentValues);
        database.close();
        return true;
    }

    public boolean updateAccount(Account account, int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("u_username", account.getUsername());
        contentValues.put("u_password", account.getPassword());
        contentValues.put("u_role", account.getRole());
        database.update("account", contentValues,
                "u_id = ?", new String[]{String.valueOf(id)});
        database.close();
        return true;
    }

    public ArrayList<Account> getAllAccount() {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.clear();
        Cursor cursor = database.query("account", allColumnsA, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Account account = cursorToAccount(cursor);
            accounts.add(account);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return accounts;
    }

    private boolean deleteAccount(Account account) {
        SQLiteDatabase database = getReadableDatabase();
        database.delete("ACCOUNT", String.valueOf(account.getId()), null);
        database.close();
        return true;
    }

    private Account cursorToAccount(Cursor cursor) {
        Account account = new Account();
        account.setId(cursor.getInt(0));
        account.setUsername(cursor.getString(1));
        account.setPassword(cursor.getString(2));
        account.setRole(cursor.getString(3));
        return account;
    }

    public Boolean checker(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from ACCOUNT where u_username like ? and u_password like ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            sqLiteDatabase.close();
            return true;
        } else {
            sqLiteDatabase.close();
            return false;
        }
    }

    public Account getAccount(String username, String password) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM ACCOUNT WHERE U_USERNAME = ? AND U_PASSWORD = ?", new String[]{username, password});
        if (cursor != null)
            cursor.moveToFirst();
        Account account = cursorToAccount(cursor);
        database.close();
        return account;
    }

    public Account getAccoundByPhone(int phone) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM PRODUCT WHERE P_ID = ?", new String[]{String.valueOf(phone)});
        if (cursor != null)
            cursor.moveToFirst();
        Account account = cursorToAccount(cursor);
        database.close();
        return account;
    }
}
