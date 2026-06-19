package com.example.stylenest_20904_a;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String PREF_NAME = "StyleNestPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_ORDERS = "userOrders";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static UserManager instance;
    private List<Order> userOrders;
    private Gson gson;

    private UserManager(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
        gson = new Gson();
        loadOrders();
    }

    public statzic synchronized UserManager getInstance(Context context) {
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
        loadOrders(); // Load orders for this session
    }

    public void register(String email, String name, String password) {
        login(email, name);
    }

    public void logout() {
        editor.clear();
        editor.apply();
        userOrders = new ArrayList<>();
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
        if (userOrders == null) userOrders = new ArrayList<>();
        userOrders.add(0, order);
        saveOrders();
    }

    public List<Order> getUserOrders() {
        if (userOrders == null) loadOrders();
        return userOrders;
    }

    private void saveOrders() {
        String json = gson.toJson(userOrders);
        editor.putString(KEY_USER_ORDERS, json);
        editor.apply();
    }

    private void loadOrders() {
        String json = prefs.getString(KEY_USER_ORDERS, null);
        if (json == null) {
            userOrders = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<Order>>() {}.getType();
            userOrders = gson.fromJson(json, type);
        }
    }
}
