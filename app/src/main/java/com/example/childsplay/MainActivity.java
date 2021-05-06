package com.example.childsplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity  {
    private static int LOGO_TIME = 3000; // show logo for 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // redirects to login page after showing logo for 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(loginIntent);
            }
        }, LOGO_TIME);



    }



}
