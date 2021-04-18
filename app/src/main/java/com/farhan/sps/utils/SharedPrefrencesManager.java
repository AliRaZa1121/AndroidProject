package com.farhan.sps.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.farhan.sps.models.UserModel;
import com.google.gson.Gson;


public class SharedPrefrencesManager {
    private static SharedPrefrencesManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "mysharedpref";
    private static final String KEY_ISLOGGED = "is_logged";
    private static final String KEY_CUSTOMER = "customer";




    private SharedPrefrencesManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefrencesManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefrencesManager(context);
        }
        return mInstance;
    }
    public boolean login(UserModel.User user, int isLoggedIn) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(user);

        editor.putString(KEY_CUSTOMER, json);
        editor.putInt(KEY_ISLOGGED, isLoggedIn);
//        editor.commit();
        editor.apply();

        return true;
    }

    public UserModel.User customer() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        if (sharedPreferences.getString(KEY_CUSTOMER, null) != null) {

            Gson gson = new Gson();
            String json = sharedPreferences.getString(KEY_CUSTOMER, "");
            UserModel.User obj = gson.fromJson(json, UserModel.User.class);
            System.out.println("From SHared: " + json);
            return obj;
        }

        return null;

    }


    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getInt(KEY_ISLOGGED, 0) == 1) {
            return true;
        }
        return false;
    }


    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }







}
