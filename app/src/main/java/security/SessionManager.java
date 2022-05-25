package security;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import view.account.LoginActivity;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int Private_mode = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String Is_Login = "IsLoggedIn";
    public static final String Key_Username = "username";
    public static final String Key_Password = "password";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Private_mode);
        editor = sharedPreferences.edit();
    }

    public void createLoginSessison(String username, String password) {
        editor.putBoolean(Is_Login, true);
        editor.putString(Key_Username, username);
        editor.putString(Key_Password, password);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(Is_Login, false);
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(Key_Username, sharedPreferences.getString(Key_Password, null));
        user.put(Key_Password, sharedPreferences.getString(Key_Password, null));
        return user;
    }
    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
