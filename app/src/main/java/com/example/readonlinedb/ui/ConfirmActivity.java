package com.example.readonlinedb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.readonlinedb.R;

public class ConfirmActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Button mContinueShoppingBtn = (Button)findViewById(R.id.continue_shopping_btn);
        mContinueShoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
