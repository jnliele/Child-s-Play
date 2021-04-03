package com.example.teachingtoddlers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    private static int LOGO_TIME = 3000; // show logo for 3 seconds
    TextView forgotPass;
    TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the id for forgot password link
        forgotPass = (findViewById(R.id.forgotPassword));

        // get the id for create an account link
        createAccount = (findViewById(R.id.createAnAccount));

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
