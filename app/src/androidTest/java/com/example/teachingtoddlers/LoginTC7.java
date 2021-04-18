package com.example.teachingtoddlers;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginTC7 {
    @Test
    public void createAccount(){
        // launch login page
        ActivityScenario activityScenario = ActivityScenario.launch(LoginPage.class);

        // see if create an account link is showing
        onView(withId(R.id.createAnAccount)).check(matches(isDisplayed()));

        // see if clicking on create an account link works
        onView(withId(R.id.createAnAccount)).perform(click());
    }
}