package com.example.teachingtoddlers;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teachingtoddlers.SectionPagerAdapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profilePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profilePage extends Fragment {

    // create fragments and tab for child and admin profile page
    View fragments;
    ViewPager viewPager;
    TabLayout tabLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profilePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profilePage.
     */
    // TODO: Rename and change types and number of parameters
    public static profilePage newInstance(String param1, String param2) {
        profilePage fragment = new profilePage();
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
        fragments = inflater.inflate(R.layout.fragment_profile_page, container, false);

        // get id from xml
        viewPager = fragments.findViewById(R.id.viewPager);
        tabLayout = fragments.findViewById(R.id.tabLayout);

        return fragments;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // set up tabs
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        // function for state of tabs
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab){

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab){

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        // set up adapter for tabs
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        // add fragments to tab
        adapter.addFragment(new ChildProfileFragment(), "Child");
        adapter.addFragment(new AdminProfileFragment(), "Admin");

        // run adapter to display tabs
        viewPager.setAdapter(adapter);
    }
}