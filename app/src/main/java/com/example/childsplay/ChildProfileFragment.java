package com.example.childsplay;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

    // array lists to hold stats
    private ArrayList<String> strengthTopicList;
    private ArrayList<Long> strengthAccuracyList;
    private ArrayList<Long> strengthPlayCountList;
    private ArrayList<String> weakTopicList;
    private ArrayList<Long> weakAccuracyList;
    private ArrayList<Long> weakPlayCountList;
    private ArrayList<String> faveList;
    private ArrayList<Long> faveTotalPlayList;

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewWeak;
    private RecyclerView recyclerViewFave;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerViewWeak = (RecyclerView) view.findViewById(R.id.recyclerViewWeakness);
        recyclerViewFave = (RecyclerView) view.findViewById(R.id.recyclerViewFaves);

        // create array lists
        strengthTopicList = new ArrayList<>();
        strengthAccuracyList = new ArrayList<>();
        strengthPlayCountList = new ArrayList<>();
        weakTopicList = new ArrayList<>();
        weakAccuracyList = new ArrayList<>();
        weakPlayCountList = new ArrayList<>();
        faveList = new ArrayList<>();
        faveTotalPlayList = new ArrayList<>();

        // get an instance of user reference from database
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // find the account user is signed in and update correct information for admin profile
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    if (ds.child("email").getValue().equals(userEmail))
                    {


                        fullName.setText(ds.child("childFirst").getValue(String.class) + " " + ds.child("childLast").getValue(String.class));
                        ageRange.setText(ds.child("ageRange").getValue(String.class));
                        alphabetLevelOneTotalPlay = ds.child("alphabetLevelOneTotalPlay").getValue(Long.class);
                        alphabetLevelTwoTotalPlay = ds.child("alphabetLevelTwoTotalPlay").getValue(Long.class);
                        alphabetLevelThreeTotalPlay = ds.child("alphabetLevelThreeTotalPlay").getValue(Long.class);

                        countingLevelOneTotalPlay = ds.child("countingLevelOneTotalPlay").getValue(Long.class);
                        countingLevelTwoTotalPlay = ds.child("countingLevelTwoTotalPlay").getValue(Long.class);
                        countingLevelThreeTotalPlay = ds.child("countingLevelThreeTotalPlay").getValue(Long.class);

                        additionLevelOneTotalPlay = ds.child("additionLevelOneTotalPlay").getValue(Long.class);
                        additionLevelTwoTotalPlay = ds.child("additionLevelTwoTotalPlay").getValue(Long.class);
                        additionLevelThreeTotalPlay = ds.child("additionLevelThreeTotalPlay").getValue(Long.class);

                        alphabetLevelOneCorrect = ds.child("alphabetLevelOneCorrect").getValue(Long.class);
                        alphabetLevelTwoCorrect = ds.child("alphabetLevelTwoCorrect").getValue(Long.class);
                        alphabetLevelThreeCorrect = ds.child("alphabetLevelThreeCorrect").getValue(Long.class);

                        countingLevelOneCorrect = ds.child("countingLevelOneCorrect").getValue(Long.class);
                        countingLevelTwoCorrect = ds.child("countingLevelTwoCorrect").getValue(Long.class);
                        countingLevelThreeCorrect = ds.child("countingLevelThreeCorrect").getValue(Long.class);

                        additionLevelOneCorrect = ds.child("additionLevelOneCorrect").getValue(Long.class);
                        additionLevelTwoCorrect = ds.child("additionLevelTwoCorrect").getValue(Long.class);
                        additionLevelThreeCorrect = ds.child("additionLevelThreeCorrect").getValue(Long.class);

                        alphabetLevelOneTotal = ds.child("alphabetLevelOneTotal").getValue(Long.class);
                        alphabetLevelTwoTotal = ds.child("alphabetLevelTwoTotal").getValue(Long.class);
                        alphabetLevelThreeTotal = ds.child("alphabetLevelThreeTotal").getValue(Long.class);

                        countingLevelOneTotal = ds.child("countingLevelOneTotal").getValue(Long.class);
                        countingLevelTwoTotal = ds.child("countingLevelTwoTotal").getValue(Long.class);
                        countingLevelThreeTotal = ds.child("countingLevelThreeTotal").getValue(Long.class);

                        additionLevelOneTotal = ds.child("additionLevelOneTotal").getValue(Long.class);
                        additionLevelTwoTotal = ds.child("additionLevelTwoTotal").getValue(Long.class);
                        additionLevelThreeTotal = ds.child("additionLevelThreeTotal").getValue(Long.class);

                        // update alphabet progress bar
                        long alphabetTotalCorrect = alphabetLevelOneCorrect + alphabetLevelTwoCorrect + alphabetLevelThreeCorrect;
                        long alphabetTotalQues = alphabetLevelOneTotal + alphabetLevelTwoTotal + alphabetLevelThreeTotal;
                        if (alphabetTotalCorrect == 0 || alphabetTotalQues == 0) {
                            LessonOnePerc.setText(" 0%");
                        }
                        // division is valid
                        else {
                            long percentage = (long)((float)100*alphabetTotalCorrect/alphabetTotalQues);
                            // add space in for single digits to center it
                            if (percentage<10) {
                                LessonOnePerc.setText(" " + String.valueOf(percentage) + "%");
                            }
                            else {
                                LessonOnePerc.setText(String.valueOf(percentage) + "%");
                            }
                            alphabet.setProgress((int)percentage);
                        }

                        // update counting progress bar
                        long countingTotalCorrect = countingLevelOneCorrect + countingLevelTwoCorrect + countingLevelThreeCorrect;
                        long countingTotalQues = countingLevelOneTotal + countingLevelTwoTotal + countingLevelThreeTotal;

                        if (countingTotalCorrect == 0 || countingTotalQues == 0) {
                            LessonTwoPerc.setText(" 0%");
                        }
                        // division is valid
                        else {
                            long percentage = (long)((float)100*countingTotalCorrect/countingTotalQues);
                            // add space in for single digits to center it
                            if (percentage<10) {
                                LessonTwoPerc.setText(" " + String.valueOf(percentage) + "%");
                            }
                            else {
                                LessonTwoPerc.setText(String.valueOf(percentage) + "%");
                            }
                            counting.setProgress((int)percentage);
                        }

                        // update addition progress bar
                        long additionTotalCorrect = additionLevelOneCorrect + additionLevelTwoCorrect + additionLevelThreeCorrect;
                        long additionTotalQues = additionLevelOneTotal + additionLevelTwoTotal + additionLevelThreeTotal;
                        if (additionTotalCorrect == 0 || additionTotalQues == 0) {
                            LessonThreePerc.setText(" 0%");
                        }
                        // division is valid
                        else {
                            long percentage = (long)((float)100*additionTotalCorrect/additionTotalQues);
                            // add space in for single digits to center it
                            if (percentage<10) {
                                LessonThreePerc.setText(" " + String.valueOf(percentage) + "%");
                            }
                            else {
                                LessonThreePerc.setText(String.valueOf(percentage) + "%");
                            }
                            addition.setProgress((int)percentage);
                        }

                        // calculate child's grade for each level
                        long alphLvlOne = (long)((float)100*alphabetLevelOneCorrect/alphabetLevelOneTotal);
                        long alphLvlTwo = (long)((float)100*alphabetLevelTwoCorrect/alphabetLevelTwoTotal);
                        long alphLvlThree = (long)((float)100*alphabetLevelThreeCorrect/alphabetLevelThreeTotal);

                        long countLvlOne = (long)((float)100*countingLevelOneCorrect/countingLevelOneTotal);
                        long countLvlTwo = (long)((float)100*countingLevelTwoCorrect/countingLevelTwoTotal);
                        long countLvlThree = (long)((float)100*countingLevelThreeCorrect/countingLevelThreeTotal);

                        long addLvlOne = (long)((float)100*additionLevelOneCorrect/additionLevelOneTotal);
                        long addLvlTwo = (long)((float)100*additionLevelTwoCorrect/additionLevelTwoTotal);
                        long addLvlThree = (long)((float)100*additionLevelThreeCorrect/additionLevelThreeTotal);

                        // determine whether a level is the child's strength or weakness
                        // passing grade
                        if (alphLvlOne>=90) {
                            strengthTopicList.add("Letters A-H");
                            strengthAccuracyList.add(alphLvlOne);
                            strengthPlayCountList.add(alphabetLevelOneTotalPlay);
                        }
                        // failing grade
                        else {
                            weakTopicList.add("Letters A-H");
                            weakAccuracyList.add(alphLvlOne);
                            weakPlayCountList.add(alphabetLevelOneTotalPlay);
                        }
                        // passing grade
                        if (alphLvlTwo>=90) {
                            strengthTopicList.add("Letters A-P");
                            strengthAccuracyList.add(alphLvlTwo);
                            strengthPlayCountList.add(alphabetLevelTwoTotalPlay);
                        }
                        // failing grade
                        else {
                            weakTopicList.add("Letters A-P");
                            weakAccuracyList.add(alphLvlTwo);
                            weakPlayCountList.add(alphabetLevelTwoTotalPlay);
                        }
                        // passing grade
                        if (alphLvlThree>=90) {
                            strengthTopicList.add("Letters A-Z");
                            strengthAccuracyList.add(alphLvlThree);
                            strengthPlayCountList.add(alphabetLevelThreeTotalPlay);
                        }
                        // failing grade
                        else {
                            weakTopicList.add("Letters A-Z");
                            weakAccuracyList.add(alphLvlThree);
                            weakPlayCountList.add(alphabetLevelThreeTotalPlay);
                        }
                        // passing grade
                        if (countLvlOne>=90) {
                            strengthTopicList.add("Count Numbers 1-5");
                            strengthAccuracyList.add(countLvlOne);
                            strengthPlayCountList.add(countingLevelOneTotalPlay);
                        }
                        // failing grade
                        else {
                            weakTopicList.add("Count Numbers 1-5");
                            weakAccuracyList.add(countLvlOne);
                            weakPlayCountList.add(countingLevelOneTotalPlay);
                        }
                        // passing grade
                        if (countLvlTwo>=90) {
                            strengthTopicList.add("Count Numbers 1-10");
                            strengthAccuracyList.add(countLvlTwo);
                            strengthPlayCountList.add(countingLevelTwoTotalPlay);
                        }
                        // failing grade
                        else {
                            weakTopicList.add("Count Numbers 1-10");
                            weakAccuracyList.add(countLvlTwo);
                            weakPlayCountList.add(countingLevelTwoTotalPlay);
                        }
                        // passing grade
                        if (countLvlThree>=90) {
                            strengthTopicList.add("Count Numbers 1-20");
                            strengthAccuracyList.add(countLvlThree);
                            strengthPlayCountList.add(countingLevelThreeTotalPlay);
                        }
                        // failing grade
                        else {
                            weakTopicList.add("Count Numbers 1-20");
                            weakAccuracyList.add(countLvlThree);
                            weakPlayCountList.add(countingLevelThreeTotalPlay);
                        }
                        // passing grade
                        if (addLvlOne>=90) {
                            strengthTopicList.add("Add with Single Digits");
                            strengthAccuracyList.add(addLvlOne);
                            strengthPlayCountList.add(additionLevelOneTotalPlay);
                        }
                        // failing grade
                        else {
                            weakTopicList.add("Add with Single Digits");
                            weakAccuracyList.add(addLvlOne);
                            weakPlayCountList.add(additionLevelOneTotalPlay);
                        }
                        // passing grade
                        if (addLvlTwo>=90) {
                            strengthTopicList.add("Add with Double Digits");
                            strengthAccuracyList.add(addLvlTwo);
                            strengthPlayCountList.add(additionLevelTwoTotalPlay);
                        }
                        // failing grade
                        else {
                            weakTopicList.add("Add with Double Digits");
                            weakAccuracyList.add(addLvlTwo);
                            weakPlayCountList.add(additionLevelTwoTotalPlay);
                        }
                        // passing grade
                        if (addLvlThree>=90) {
                            strengthTopicList.add("Add with Triple Digits");
                            strengthAccuracyList.add(addLvlThree);
                            strengthPlayCountList.add(additionLevelThreeTotalPlay);
                        }
                        // failing grade
                        else {
                            weakTopicList.add("Add with Triple Digits");
                            weakAccuracyList.add(addLvlThree);
                            weakPlayCountList.add(additionLevelThreeTotalPlay);
                        }

                        // make sure strengths list isn't empty
                        if (strengthTopicList.isEmpty()) {
                            strengthTopicList.add("-");
                            strengthAccuracyList.add(Long.valueOf(-1));
                            strengthPlayCountList.add(Long.valueOf(-1));
                        }

                        // make sure weaknesses list isn't empty
                        if (weakTopicList.isEmpty()) {
                            weakTopicList.add("-");
                            weakAccuracyList.add(Long.valueOf(-1));
                            weakPlayCountList.add(Long.valueOf(-1));
                        }

                        // only update recycler if on child profile
                        if (getActivity()!=null) {
                            // set up the adapter for recycler view to display strengths
                            recyclerAdapter adapter = new recyclerAdapter(strengthTopicList, strengthAccuracyList, strengthPlayCountList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);

                            // set up the adapter for recycler view to display weaknesses
                            recyclerAdapter adapterWeak = new recyclerAdapter(weakTopicList, weakAccuracyList, weakPlayCountList);
                            RecyclerView.LayoutManager layoutManagerWeak = new LinearLayoutManager(getActivity().getApplicationContext());
                            recyclerViewWeak.setLayoutManager(layoutManagerWeak);
                            recyclerViewWeak.setItemAnimator(new DefaultItemAnimator());
                            recyclerViewWeak.setAdapter(adapterWeak);
                        }

                        // populate list of lessons
                        faveList.add("Alphabet");
                        faveList.add("Counting");
                        faveList.add("Addition");

                        // compute total play count for each lesson
                        faveTotalPlayList.add(alphabetLevelOneTotalPlay+alphabetLevelTwoTotalPlay+alphabetLevelThreeTotalPlay);
                        faveTotalPlayList.add(countingLevelOneTotalPlay+countingLevelTwoTotalPlay+countingLevelThreeTotalPlay);
                        faveTotalPlayList.add(additionLevelOneTotalPlay+additionLevelTwoTotalPlay+additionLevelThreeTotalPlay);

                        // determine the max total play count among the lessons
                        long max = faveTotalPlayList.get(0); // make the first lesson the max
                        for (int i = 1; i<faveTotalPlayList.size(); i++) {
                            if (faveTotalPlayList.get(i) > max) {
                                max = faveTotalPlayList.get(i);
                            }
                        }

                        // ensure the max is not 0
                        if (max == 0) {
                            faveList.clear();
                            faveList.add("None");
                        }
                        else {
                            // remove all lessons that do not have max total play count;
                            for (int i = 0; i<faveTotalPlayList.size(); i++) {
                                if (faveTotalPlayList.get(i) != max) {
                                    faveTotalPlayList.remove(i);
                                    faveList.remove(i);
                                    i--;
                                }
                            }
                        }

                        // to handle different recyclerAdapter xml in recyclerAdapter.java
                        faveTotalPlayList.clear();
                        for (int i = 0; i<faveList.size(); i++) {
                            faveTotalPlayList.add(Long.valueOf(-2));
                        }

                        // only update recycler if on child profile
                        if (getActivity()!=null) {
                            // set up the adapter for recycler view to display favorite lesson
                            recyclerAdapter adapterFave = new recyclerAdapter(faveList, faveTotalPlayList, faveTotalPlayList);
                            RecyclerView.LayoutManager layoutManagerFave = new LinearLayoutManager(getActivity().getApplicationContext());
                            recyclerViewFave.setLayoutManager(layoutManagerFave);
                            recyclerViewFave.setItemAnimator(new DefaultItemAnimator());
                            recyclerViewFave.setAdapter(adapterFave);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}