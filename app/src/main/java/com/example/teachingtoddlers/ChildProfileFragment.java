package com.example.teachingtoddlers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChildProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChildProfileFragment extends Fragment {

    private TextView fullName, ageRange;
    private TextView LessonOnePerc, LessonTwoPerc, LessonThreePerc;
    private ProgressBar alphabet, counting, addition;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USERS = "Users";

    // the total number of times a level is played
    long alphabetLevelOneTotalPlay, alphabetLevelTwoTotalPlay, alphabetLevelThreeTotalPlay;
    long countingLevelOneTotalPlay, countingLevelTwoTotalPlay, countingLevelThreeTotalPlay;
    long additionLevelOneTotalPlay, additionLevelTwoTotalPlay, additionLevelThreeTotalPlay;

    // the total number of questions the user gets right in a level
    long alphabetLevelOneCorrect, alphabetLevelTwoCorrect, alphabetLevelThreeCorrect;
    long countingLevelOneCorrect, countingLevelTwoCorrect, countingLevelThreeCorrect;
    long additionLevelOneCorrect, additionLevelTwoCorrect, additionLevelThreeCorrect;

    // the total number of questions done/attempted in a level
    long alphabetLevelOneTotal, alphabetLevelTwoTotal, alphabetLevelThreeTotal;
    long countingLevelOneTotal, countingLevelTwoTotal, countingLevelThreeTotal;
    long additionLevelOneTotal, additionLevelTwoTotal, additionLevelThreeTotal;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChildProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChildProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChildProfileFragment newInstance(String param1, String param2) {
        ChildProfileFragment fragment = new ChildProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_child_profile, container, false);

        // get id from XML
        fullName = (TextView) view.findViewById(R.id.childFullName);
        ageRange = (TextView) view.findViewById(R.id.ageRangeProfile);
        LessonOnePerc = (TextView) view.findViewById(R.id.LessonOnePerc);
        alphabet = (ProgressBar) view.findViewById(R.id.LessonOneProg);
        LessonTwoPerc = (TextView) view.findViewById(R.id.LessonTwoPerc);
        counting = (ProgressBar) view.findViewById(R.id.LessonTwoProg);
        LessonThreePerc = (TextView) view.findViewById(R.id.LessonThreePerc);
        addition = (ProgressBar) view.findViewById(R.id.LessonThreeProg);

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
                        fullName.setText(ds.child("childFirst").getValue(String.class) + " " + ds.child("childLast").getValue(String.class));
                        ageRange.setText(ds.child("ageRange").getValue(String.class));
                        alphabetLevelOneTotalPlay = ds.child("alphabetLevelOneTotalPlay").getValue(Long.class);
                        alphabetLevelTwoTotalPlay = ds.child("alphabetLevelTwoTotalPlay").getValue(Long.class);
                        alphabetLevelThreeTotalPlay = ds.child("alphabetLevelThreeTotalPlay").getValue(Long.class);

                        countingLevelOneTotalPlay = ds.child("additionLevelOneTotalPlay").getValue(Long.class);
                        countingLevelTwoTotalPlay = ds.child("additionLevelTwoTotalPlay").getValue(Long.class);
                        countingLevelThreeTotalPlay = ds.child("additionLevelThreeTotalPlay").getValue(Long.class);

                        additionLevelOneTotalPlay = ds.child("additionLevelOneTotalPlay").getValue(Long.class);
                        additionLevelTwoTotalPlay = ds.child("additionLevelTwoTotalPlay").getValue(Long.class);
                        additionLevelThreeTotalPlay = ds.child("additionLevelThreeTotalPlay").getValue(Long.class);

                        alphabetLevelOneCorrect = ds.child("alphabetLevelOneCorrect").getValue(Long.class);
                        alphabetLevelTwoCorrect = ds.child("alphabetLevelTwoCorrect").getValue(Long.class);
                        alphabetLevelThreeCorrect = ds.child("alphabetLevelThreeCorrect").getValue(Long.class);

                        countingLevelOneCorrect = ds.child("additionLevelOneCorrect").getValue(Long.class);
                        countingLevelTwoCorrect = ds.child("additionLevelTwoCorrect").getValue(Long.class);
                        countingLevelThreeCorrect = ds.child("additionLevelThreeCorrect").getValue(Long.class);

                        additionLevelOneCorrect = ds.child("additionLevelOneCorrect").getValue(Long.class);
                        additionLevelTwoCorrect = ds.child("additionLevelTwoCorrect").getValue(Long.class);
                        additionLevelThreeCorrect = ds.child("additionLevelThreeCorrect").getValue(Long.class);

                        alphabetLevelOneTotal = ds.child("alphabetLevelOneTotal").getValue(Long.class);
                        alphabetLevelTwoTotal = ds.child("alphabetLevelTwoTotal").getValue(Long.class);
                        alphabetLevelThreeTotal = ds.child("alphabetLevelThreeTotal").getValue(Long.class);

                        countingLevelOneTotal = ds.child("additionLevelOneTotal").getValue(Long.class);
                        countingLevelTwoTotal = ds.child("additionLevelTwoTotal").getValue(Long.class);
                        countingLevelThreeTotal = ds.child("additionLevelThreeTotal").getValue(Long.class);

                        additionLevelOneTotal = ds.child("additionLevelOneTotal").getValue(Long.class);
                        additionLevelTwoTotal = ds.child("additionLevelTwoTotal").getValue(Long.class);
                        additionLevelThreeTotal = ds.child("additionLevelThreeTotal").getValue(Long.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // update alphabet progress bar
        long alphabetTotalCorrect = alphabetLevelOneCorrect + alphabetLevelTwoCorrect + alphabetLevelThreeCorrect;
        long alphabetTotalQues = alphabetLevelOneTotal + alphabetLevelTwoTotal + alphabetLevelThreeTotal;
        if (alphabetTotalCorrect == Long.valueOf(0) || alphabetTotalQues == Long.valueOf(0))
        {
            LessonOnePerc.setText(" 0%");
        }
        else // division is valid
        {
            long percentage = alphabetTotalCorrect/alphabetTotalQues;
            if (percentage<10) // add space in for single digits to center it
            {
                LessonOnePerc.setText(" " + String.valueOf(percentage) + "%");
            }
            else
            {
                LessonOnePerc.setText(String.valueOf(percentage) + "%");
            }
            alphabet.setProgress((int)percentage);
        }

        // update counting progress bar
        LessonOnePerc.setText(String.valueOf(countingLevelOneCorrect));
        LessonTwoPerc.setText(String.valueOf(countingLevelTwoCorrect));
        LessonThreePerc.setText(String.valueOf(countingLevelThreeCorrect));
        long countingTotalCorrect = countingLevelOneCorrect + countingLevelTwoCorrect + countingLevelThreeCorrect;
        long countingTotalQues = countingLevelOneTotal + countingLevelTwoTotal + countingLevelThreeTotal;
        /*
        if (countingTotalCorrect == Long.valueOf(0) || countingTotalQues == Long.valueOf(0))
        {
            LessonTwoPerc.setText(" 0%");
        }
        else // division is valid
        {
            long percentage = countingTotalCorrect/countingTotalQues;
            if (percentage<10) // add space in for single digits to center it
            {
                LessonTwoPerc.setText(" " + String.valueOf(percentage) + "%");
            }
            else
            {
                LessonTwoPerc.setText(String.valueOf(percentage) + "%");
            }
            counting.setProgress((int)percentage);
        }

        // update addition progress bar
        long additionTotalCorrect = additionLevelOneCorrect + additionLevelTwoCorrect + additionLevelThreeCorrect;
        long additionTotalQues = additionLevelOneTotal + additionLevelTwoTotal + additionLevelThreeTotal;
        if (additionTotalCorrect == Long.valueOf(0) || additionTotalQues == Long.valueOf(0))
        {
            LessonThreePerc.setText(" 0%");
        }
        else // division is valid
        {
            long percentage = additionTotalCorrect/additionTotalQues;
            if (percentage<10) // add space in for single digits to center it
            {
                LessonThreePerc.setText(" " + String.valueOf(percentage) + "%");
            }
            else
            {
                LessonThreePerc.setText(String.valueOf(percentage) + "%");
            }
            addition.setProgress((int)percentage);
        }*/

        return view;
    }
}