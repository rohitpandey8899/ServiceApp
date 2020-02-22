package com.manish.obaa.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

public class Preference {

    private static final String PREF_NAME = "MyPreference";
    private static final String IS_LOGIN = "isLogin";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String CONTACT_NO = "contact";
    private static final String USER_ID = "user_id";
    private static final String CITY = "city";
    private static final String GENDER = "gender";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public Preference(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    public boolean isLogin() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setLogin(boolean isLogin) {
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }

    public String getName()
    {
        return pref.getString(NAME, "");
    }

    public void setName(String name) {
        editor.putString(NAME, name);
        editor.commit();
    }


    public String getEmail() {
        return pref.getString(EMAIL, "");
    }

    public void setEmail(String email) {
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public String getContactNo() {
        return pref.getString(CONTACT_NO, "");
    }

    public void setContactNo(String contactNo) {
        editor.putString(CONTACT_NO, contactNo);
        editor.commit();
    }

    public String getUserId() {
        return pref.getString(USER_ID, "");
    }

    public void setUserId(String userId) {
        editor.putString(USER_ID, userId);
        editor.commit();
    }

    public String getCity() {
        return pref.getString(CITY, "Select City");
    }

    public void setCity(String city) {
        editor.putString(CITY, city);
        editor.commit();
    }

    public void clearAll()
    {
        editor.clear().apply();
    }

    public String getGender() {
        return pref.getString(GENDER, "Male");
    }

    public void setGender(String gender) {
        editor.putString(GENDER, gender);
        editor.commit();
    }

}