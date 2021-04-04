package com.example.teachingtoddlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    TextView forgotPass;
    TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        // get id for forgot password link
        forgotPass = (TextView) (findViewById(R.id.forgotPassword));

        // redirect to forgot password page
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, forgotPassword.class));
            }
        });

        // get id for create an account link
        createAccount = (TextView) (findViewById(R.id.createAnAccount));

        // redirect to create an account page
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, childSetUp.class));
            }
        });
    }
}