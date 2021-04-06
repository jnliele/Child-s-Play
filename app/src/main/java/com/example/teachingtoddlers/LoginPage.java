package com.example.teachingtoddlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class LoginPage extends AppCompatActivity {
    TextView forgotPass;
    TextView createAccount;
    Button SignInButton;
    ProgressDialog progressDialog;
    private TextView register;
    private EditText emailUsername, firstName, lastName, UTAID, password, profession;
    private FirebaseAuth auth;
    public Uri imageUri;
    private StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        // get id for forgot password link

        auth=FirebaseAuth.getInstance();
        emailUsername=findViewById(R.id.username);
        password=findViewById(R.id.password);
        progressDialog=new ProgressDialog(this);
        SignInButton=findViewById(R.id.login);


        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });



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
                startActivity(new Intent(LoginPage.this, accountCreation.class));

            }
        });
    }


        private void Login(){
            String email = emailUsername.getText().toString().trim();
            String pw = password.getText().toString().trim();

            if(email.isEmpty()){
                emailUsername.setError("Email is required!");
                emailUsername.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailUsername.setError("Please provide a valid email!");
                emailUsername.requestFocus();
                return;
            }

            if(pw.isEmpty()){
                password.setError("A Password is required!");
                password.requestFocus();
                return;
            }
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);


            auth.signInWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginPage.this, "You have successfully Logged in!", Toast.LENGTH_LONG).show();
                                 Intent home= new Intent(LoginPage.this, homePage.class);
                                 startActivity(home);
                            }else{
                                Toast.makeText(LoginPage.this, "Sign in Fail!", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }

}