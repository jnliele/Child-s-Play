package com.example.teachingtoddlers;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ImageView recLessonOne, recLessonTwo, recLessonThree;
    public Button launchAddGame,launchCountGame,launchABCGame;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USERS = "Users";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static homeScreen newInstance(String param1, String param2) {
        homeScreen fragment = new homeScreen();
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
        View view =  inflater.inflate(R.layout.fragment_home_screen, container, false);

        // get id from xml
        launchAddGame = (Button) (view.findViewById(R.id.additionButton));
        launchABCGame = (Button) (view.findViewById(R.id.AlphaButton));
        launchCountGame = (Button) (view.findViewById(R.id.countButton));
        recLessonOne = (ImageView) (view.findViewById(R.id.recLessonOne));
        recLessonTwo = (ImageView) (view.findViewById(R.id.recLessonTwo));
        recLessonThree = (ImageView) (view.findViewById(R.id.recLessonThree));

        // get an instance of user reference from database
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

        // get email that is logged in
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        final String[] userAge = new String[1];

        // find the account user is signed in and determine what lesson is recommended for their age
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    if (ds.child("email").getValue().equals(userEmail)){
                        userAge[0] = ds.child("ageRange").getValue(String.class);

                        // set recommended icon for lesson one
                        if (userAge[0].equals("1-3 years old")) {
                            recLessonOne.setVisibility(View.VISIBLE);
                        }
                        // set recommended icon for lesson two
                        else if (userAge[0].equals("3-5 years old")) {
                            recLessonTwo.setVisibility(View.VISIBLE);
                        }
                        // set recommended icon for lesson three
                        else {
                            recLessonThree.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // functionality for addition game
        launchAddGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              Intent addPage = new Intent(getActivity(), additionGame.class);
              startActivity(addPage);
              getActivity().finish();
            }
        }
        );

        // functionality for alphabet game
        launchABCGame.setOnClickListener(new View.OnClickListener()
        {
              @Override
              public void onClick(View v)
              {
                  Intent alphabetPage = new Intent(getActivity(), alphabetGame.class);
                  startActivity(alphabetPage);
                  getActivity().finish();
              }
        }
        );


        // functionality for counting game
        launchCountGame.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick(View v){

                  Intent countPage = new Intent(getActivity(), countGame.class);
                  startActivity(countPage);
                  getActivity().finish();
              }
          }
        );

        return view;

    }





}