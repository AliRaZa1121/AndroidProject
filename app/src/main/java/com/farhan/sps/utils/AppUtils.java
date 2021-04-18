package com.farhan.sps.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;


public class AppUtils {

    public static String baseURL = "https://aptechproject09.000webhostapp.com/";

    public static class APIMethods{
        public static String Login = baseURL+"login.php";
        public static String tpo_insert = baseURL+"tpo_insert.php";
        public static String student_insert = baseURL+"student_insert.php";
        public static String company_insert = baseURL+"company_insert.php";
        public static String vacancy_insert = baseURL+"vacancy_insert.php";
        public static String student_fetch = baseURL+"student_fetch.php";
        public static String tpo_fetch = baseURL+"tpo_fetch.php";
        public static String company_fetch = baseURL+"company_fetch.php";
        public static String vacancy_fetch = baseURL+"vacancy_fetch.php";


    }

    public static Object parseJSONObject(String response, Class<?> className) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonObject.toString(), className);
    }
}
