package com.moringaschool.hellozuz;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;
import android.content.Context;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.userName)).perform(typeText("Vincent"))
                .check(matches(withText("Vincent")));
    }
    @Test
    public void validatePassword() {
        onView(withId(R.id.password)).perform(typeText("Taptet#2001"))
                .check(matches(withText("Taptet#2001")));
    }

    @Test
    public void userNameIsSentToNewsFeedsActivity() {
        String userName = "Vincent";
        onView(withId(R.id.userName)).perform(typeText(userName)).perform(closeSoftKeyboard());
        try {                             // the sleep method requires to be checked and handled so we use try block
            Thread.sleep(250);
        } catch (InterruptedException e){
            System.out.println("got interrupted!");
        }
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.introText)).check(matches
                (withText("Welcome To Our NewsFeeds " + userName)));
    }
}
