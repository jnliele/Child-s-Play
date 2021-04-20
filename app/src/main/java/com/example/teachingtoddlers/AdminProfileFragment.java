package com.example.teachingtoddlers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminProfileFragment extends Fragment {

    private TextView fullName, profession, utaID, email;
    private ImageView imageview;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USERS = "Users";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminProfileFragment newInstance(String param1, String param2) {
        AdminProfileFragment fragment = new AdminProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);

        // get id from XML
        fullName = (TextView) view.findViewById(R.id.fullName);
        profession = (TextView) view.findViewById(R.id.professionProfile);
        utaID = (TextView) view.findViewById(R.id.utaIDProfile);
        email = (TextView) view.findViewById(R.id.emailProfile);
        imageview = (ImageView) view.findViewById(R.id.profilePicAdmin);

        // get an instance of user reference from database
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // find the account user is signed in and update correct information for admin profile
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    if (ds.child("email").getValue().equals(userEmail)){
                        fullName.setText(ds.child("fname").getValue(String.class) + " " + ds.child("lname").getValue(String.class));
                        profession.setText(ds.child("prof").getValue(String.class));
                        utaID.setText(ds.child("utaID").getValue(String.class));
                        email.setText(ds.child("email").getValue(String.class));
                        String image = ds.child("image").getValue(String.class);
                        if (!image.isEmpty())
                        {
                            Picasso.with(getActivity()).load(image).into(imageview);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // functionality for log out button
        Button logout = (Button) (view.findViewById(R.id.UserLogsOut));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginPage.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}