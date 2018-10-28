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
import com.example.readonlinedb.retrofit.ApiClinet;
import com.example.readonlinedb.retrofit.ApiInterface;
import com.example.readonlinedb.retrofit.Response;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

public class SignupActivity extends AppCompatActivity {

    Toolbar mToolbar;
    AppCompatEditText mEmialEditTxt;
    AppCompatEditText mPasswordEditText;
    Button mSignUpBtn;
    TextView mLoginTxt;

    String mUserEmail,mUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEmialEditTxt = (AppCompatEditText) findViewById(R.id.email_edittext);
        mPasswordEditText = (AppCompatEditText) findViewById(R.id.password_edittext);
        mSignUpBtn = (Button) findViewById(R.id.signup_btn);
        mLoginTxt = (TextView) findViewById(R.id.login);



        // setup toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create an account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save user info

                mUserEmail = mEmialEditTxt.getText().toString();
                mUserPassword = mPasswordEditText.getText().toString();

                if(!TextUtils.isEmpty(mUserEmail) && !TextUtils.isEmpty(mUserPassword)){
                    signup();
                }else
                    Toast.makeText(SignupActivity.this, "invalid emial or password", Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void signup(){
        HashMap<String,String> map = new HashMap<>();
        map.put("email",mUserEmail);
        map.put("password",mUserPassword);

        Call<Response> call = ApiClinet.getApiClient().create(ApiInterface.class)
                .signup(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response mainResponse = response.body();
                Log.d("mainActivity","message is "+mainResponse.getMessage());
                if (!mainResponse.isError()){

                    Intent intent = new Intent(SignupActivity.this,ConfirmActivity.class);
                    startActivity(intent);
                    finish();
                }else showDialog(mainResponse.getMessage());
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
