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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.readonlinedb.R;

import java.util.HashMap;
import java.util.Map;


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
        Log.d("mainactivity","login");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("mainactivity","response is "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mainactivity","error is "+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("email",mUserEmail);
                map.put("password",mUserPassword);
                return map;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
