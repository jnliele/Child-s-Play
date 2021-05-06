package com.example.childsplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        emailOfUser = findViewById(R.id.Email);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get email inputted
                String email=emailOfUser.getText().toString().trim();

                // ensure field is not empty
                if(email.isEmpty()){
                    emailOfUser.setError("Please Enter Your Email!");
                    emailOfUser.requestFocus();
                    return;
                }

                // validate email input
                auth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                            emailOfUser.setError("Please Enter Valid Email");
                            emailOfUser.requestFocus();
                            return;
                        }

                        if(task.isSuccessful()){

                            Toast.makeText(forgotPassword.this, "Please check your email", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(forgotPassword.this, "Error: Your email is not in our database", Toast.LENGTH_LONG).show();

                        }

                    }
                });

            }

        });
    }
}