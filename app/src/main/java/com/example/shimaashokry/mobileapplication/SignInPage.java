package com.example.shimaashokry.mobileapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;

public class SignInPage extends AppCompatActivity {
    private static int TIME_DELAY_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent SecondPage = new Intent(SignInPage.this,SearchPage.class);
                startActivity(SecondPage);
                finish();
            }
        }, TIME_DELAY_OUT);
    }


}
