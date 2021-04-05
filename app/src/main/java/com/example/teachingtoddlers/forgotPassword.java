package com.example.teachingtoddlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


public class forgotPassword extends AppCompatActivity {
    Button submit;
    FirebaseAuth auth;
    EditText emailOfUser;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        submit = (Button) (findViewById(R.id.enterButton));
        emailOfUser = (EditText)(findViewById(R.id.Email));
        //auth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



              //  restPassword();
                String email=emailOfUser.getText().toString().trim();


               auth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if(email.isEmpty()){

                            emailOfUser.setError("Please Enter Your Email!");
                            emailOfUser.requestFocus();
                            return;
                        }
                        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                            emailOfUser.setError("Please Enter Valid Email");
                            emailOfUser.requestFocus();
                            return;
                        }

                        if(task.isSuccessful()){

                            Toast.makeText(forgotPassword.this, "Please Check Your Email", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(forgotPassword.this, "Error: Your email is not in our database", Toast.LENGTH_LONG).show();

                        }

                    }
                });

            }

        });
    }
}