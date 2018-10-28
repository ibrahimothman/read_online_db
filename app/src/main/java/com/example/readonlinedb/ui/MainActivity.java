package com.example.readonlinedb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.readonlinedb.R;
import com.example.readonlinedb.preference.PrefManager;

public class MainActivity extends AppCompatActivity {

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefManager = new PrefManager(this);

        // if user hasn't logged in yet , redirect him to login
        if(!prefManager.isLoggedInBefore()){
            Intent loginIntent = new Intent(this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }



    }
}
