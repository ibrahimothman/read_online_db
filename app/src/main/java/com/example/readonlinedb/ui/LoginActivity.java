package com.example.readonlinedb.ui;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.readonlinedb.R;
import com.example.readonlinedb.preference.PrefManager;
import com.example.readonlinedb.retrofit.ApiClinet;
import com.example.readonlinedb.retrofit.ApiInterface;
import com.example.readonlinedb.retrofit.Response;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;


public class LoginActivity extends AppCompatActivity{

    public static final String BASE_URL = "http://192.168.1.105/admin";
    public static final String LOGIN_URL =BASE_URL+"/includes/login.php";

    Toolbar mToolbar;
    AppCompatEditText mEmialEditTxt;
    AppCompatEditText mPasswordEditText;
    Button mLoginBtn;
    TextView mCreateAccountTxt;

    String mUserEmail,mUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEmialEditTxt = (AppCompatEditText) findViewById(R.id.email_edittext);
        mPasswordEditText = (AppCompatEditText) findViewById(R.id.password_edittext);
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mCreateAccountTxt = (TextView) findViewById(R.id.create_account);



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
                    Toast.makeText(LoginActivity.this, "invalid emial or password", Toast.LENGTH_SHORT).show();
            }
        });



        mCreateAccountTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

    }



    public void login(){
        HashMap<String,String> map = new HashMap<>();
        map.put("email",mUserEmail);
        map.put("password",mUserPassword);

        Call<Response> call = ApiClinet.getApiClient().create(ApiInterface.class)
                .login(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response mainResponse = response.body();
                Log.d("mainActivity","message is "+mainResponse.getMessage());
                if (!mainResponse.isError()){
                    new PrefManager(LoginActivity.this).changePref("login");
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    showDialog(mainResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("mainActivity","onFailure "+t.getMessage());
            }
        });
    }


    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .show();
    }
}
