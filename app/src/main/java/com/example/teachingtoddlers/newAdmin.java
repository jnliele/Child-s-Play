package com.example.teachingtoddlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class newAdmin extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText emailUsername, firstName, lastName, UTAID, password, profession;
    private FirebaseAuth auth;
    private ImageView avatar;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    public String image;
    additionLevelOne n = new additionLevelOne();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_admin);

        image = "empty";
        auth = FirebaseAuth.getInstance();

        // get id for register button and enable an action
        register = (Button) findViewById(R.id.button2);
        register.setOnClickListener(this);

        // get id from xml
        emailUsername = (EditText) findViewById(R.id.editTextTextEmailAddress);
        firstName = (EditText) findViewById(R.id.editTextTextPersonName2);
        lastName = (EditText) findViewById(R.id.editTextTextPersonName3);
        UTAID = (EditText) findViewById(R.id.editTextTextPersonName4);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        profession = (EditText) findViewById(R.id.editTextTextPersonName5);
        avatar = findViewById(R.id.avatar);

        // set up firebase database storage for image upload
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        // allow user to tap on image and upload a profile picture
        avatar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
               choosePicture();
           }
        });
    }

    private void choosePicture(){
        // function to open up phone gallery and allow user to choose picture
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // function to get image data that user chooses
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            avatar.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture(){
        // uploads the picture to database
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading image...");
        pd.show();

        // create a unique name for photo to be uploaded in database
        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageRef.child(randomKey);

        // attempt to upload the picture to database
        // two different cases: success and failure
        // also includes a progress bar
        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_SHORT).show();
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUrl = uri;
                                image = downloadUrl.toString();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Percentage: " + (int) progressPercent + "%");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        // pass in information from child setup
        Intent intent = getIntent();
        String childFirst = intent.getStringExtra(accountCreation.EXTRA_FIRST);
        String childLast = intent.getStringExtra(accountCreation.EXTRA_LAST);
        String ageRange = intent.getStringExtra(accountCreation.EXTRA_AGE);

        // get the string value of admin setup input
        String email = emailUsername.getText().toString().trim();
        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String utaID = UTAID.getText().toString().trim();
        String prof = profession.getText().toString().trim();
        String pw = password.getText().toString().trim();

        // ensure that no fields are empty
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

        if(fname.isEmpty()){
            firstName.setError("First name is required!");
            firstName.requestFocus();
            return;
        }

        if(lname.isEmpty()){
            lastName.setError("Last name is required!");
            lastName.requestFocus();
            return;
        }

        if(pw.isEmpty()){
            password.setError("A password is required!");
            password.requestFocus();
            return;
        }

        if(pw.length() < 6){
            password.setError("Password must be at least 6 characters!");
            password.requestFocus();
            return;
        }

        if(pw.length() > 20){
            password.setError("Password must not exceed 20 characters!");
            password.requestFocus();
            return;
        }

        if(utaID.isEmpty()){
            UTAID.setError("A UTA ID is required!");
            UTAID.requestFocus();
            return;
        }

        if(prof.isEmpty()){
            profession.setError("A profession is required!");
            profession.requestFocus();
        }

        // functionality for when user hits register button
        auth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(email, fname, lname, utaID, prof, pw, childFirst, childLast, ageRange, image);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(newAdmin.this, "You have been registered successfully!", Toast.LENGTH_LONG).show();
                                        Intent home= new Intent(newAdmin.this, homePage.class);
                                        startActivity(home);
                                    }else{
                                        Toast.makeText(newAdmin.this, "Failed to register!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(newAdmin.this, "Email is already registered!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}