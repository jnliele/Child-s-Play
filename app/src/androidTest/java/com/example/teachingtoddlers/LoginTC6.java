package com.example.teachingtoddlers;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class LoginTC6 {
    @Test
    public void resetPassword(){
        // launch login page
        ActivityScenario activityScenario = ActivityScenario.launch(LoginPage.class);

        // see if forgot password is showing
        onView(withId(R.id.forgotPassword)).check(matches(isDisplayed()));

        // see if clicking on forgot password link works
        onView(withId(R.id.forgotPassword)).perform(click());

        // see if enter button is showing to confirm email address for password recovery
        onView(withId(R.id.enterButton)).check(matches(isDisplayed()));

        // see if clicking on enter button works
        onView(withId(R.id.enterButton)).perform(click());
    }
}