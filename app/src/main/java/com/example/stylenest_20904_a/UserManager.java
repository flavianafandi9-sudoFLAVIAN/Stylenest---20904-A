package com.example.stylenest_20904_a;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String PREF_NAME = "StyleNestPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_USER_NAME = "userName";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static UserManager instance;
    private List<Order> userOrders = new ArrayList<>();

    private UserManager(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static synchronized UserManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserManager(context);
        }
        return instance;
    }

    public void login(String email, String name) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USER_NAME, name);
        editor.apply();
    }

    public void register(String email, String name, String password) {
        // In a real app, you'd save to a DB. For now, we just log them in.
        login(email, name);
    }

    public void logout() {
        editor.clear();
        editor.apply();
        userOrders.clear();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getUserEmail() {
        return prefs.getString(KEY_USER_EMAIL, "");
    }

    public String getUserName() {
        return prefs.getString(KEY_USER_NAME, "");
    }

    public void addOrder(Order order) {
        userOrders.add(0, order); // Add new orders to the top
    }

    public List<Order> getUserOrders() {
        return userOrders;
    }
}
