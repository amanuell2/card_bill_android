package com.example.bill_card.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bill_card.Model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SharedPreferenceUtil {
    private static final String PREF_NAME = "ORDERS";


    public SharedPreferenceUtil() {
    }

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public static ArrayList<Product> getPreferenceArrayList(Context context, String Key) {
        Gson gson = new Gson();
        String json =  getPrefs(context).getString(Key, "");
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        ArrayList<Product> products = gson.fromJson(json, type);
        return products;
    }

    public static void setPreferenceArrayList(Context context, String Key, ArrayList<Product> value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        Gson gson = new Gson();
        String jsonValue = gson.toJson(value);
        editor.putString(Key, jsonValue);
        editor.apply();
    }

}
