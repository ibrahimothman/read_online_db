package com.example.readonlinedb.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    public static final String LOGGED_IN_BEFORE_PREF = "is this user logged in before pref";
    public static final String LOGGED_IN_BEFORE_EDITOR = "is this user logged in before editor";
    private SharedPreferences pref;
    private Context context;

    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(LOGGED_IN_BEFORE_PREF, Context.MODE_PRIVATE);
    }

    public void changePref(String status) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LOGGED_IN_BEFORE_EDITOR, status);
        editor.apply();

    }

    public boolean isLoggedInBefore(){
        boolean status = false;

        if (pref.getString(LOGGED_IN_BEFORE_EDITOR,"null").equals("null")) {

            status = false;
        }else {
            status = true;
        }
        return status;
    }

    public void clearpref(){
        if(isLoggedInBefore()){
            changePref("null");
        }
    }

}
