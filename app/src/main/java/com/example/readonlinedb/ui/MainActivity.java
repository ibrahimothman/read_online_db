package com.example.readonlinedb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.readonlinedb.R;
import com.example.readonlinedb.retrofit.ApiClinet;
import com.example.readonlinedb.retrofit.ApiInterface;
import com.example.readonlinedb.retrofit.Error;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity{

    public static final String BASE_URL = "http://192.168.1.105/admin";
    public static final String LOGIN_URL =BASE_URL+"/includes/login.php";

    Toolbar mToolbar;
    AppCompatEditText mEmialEditTxt;
    AppCompatEditText mPasswordEditText;
    Button mLoginBtn;

    String mUserEmail,mUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEmialEditTxt = (AppCompatEditText) findViewById(R.id.email_edittext);
        mPasswordEditText = (AppCompatEditText) findViewById(R.id.password_edittext);
        mLoginBtn = (Button) findViewById(R.id.login_btn);



        // setup toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save user info

                mUserEmail = mEmialEditTxt.getText().toString();
                mUserPassword = mPasswordEditText.getText().toString();

                if(!TextUtils.isEmpty(mUserEmail) && !TextUtils.isEmpty(mUserPassword)){
                    login();
                }else
                    Toast.makeText(MainActivity.this, "invalid emial or password", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void login(){
        HashMap<String,String> map = new HashMap<>();
        map.put("email",mUserEmail);
        map.put("password",mUserPassword);

        Call<Error> call = ApiClinet.getApiClient().create(ApiInterface.class)
                .login(map);
        call.enqueue(new Callback<Error>() {
            @Override
            public void onResponse(Call<Error> call, retrofit2.Response<Error> response) {
                Error error = response.body();
                Log.d("mainActivity","message is "+error.getMessage());
            }

            @Override
            public void onFailure(Call<Error> call, Throwable t) {
                Log.d("mainActivity","onFailure "+t.getMessage());
            }
        });
    }
}
