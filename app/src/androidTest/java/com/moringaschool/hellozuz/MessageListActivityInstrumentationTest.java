package com.moringaschool.hellozuz;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class MessageListActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MessageListActivity> activityTestRule =
            new ActivityTestRule<>(MessageListActivity.class);

    @Test
    public void listItemClickDisplaysToastWithCorrectText() {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
        onData(anything())
                .inAdapterView(withId(R.id.messageList))
                .atPosition(0)
                .perform(click());
        onView(withText("Chat box will open when clicked")).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText("Chat box will open when clicked")));
    }
}
