package com.e.todolist;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@SuppressLint("Registered")
public class JSON extends Application {

    public static final String PREF_KEY_JSON = "jsonData";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}