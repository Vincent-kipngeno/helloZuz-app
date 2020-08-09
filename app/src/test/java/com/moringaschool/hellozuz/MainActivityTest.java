package com.moringaschool.hellozuz;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import com.moringaschool.hellozuz.ui.MainActivity;
import com.moringaschool.hellozuz.ui.NewsFeedsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.TestCase.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewContent(){
        TextView aboutHeader = activity.findViewById(R.id.aboutHeader);
        assertTrue("WELCOME TO helloZuz!".equals(aboutHeader.getText().toString()));
    }

    @Test
    public void secondActivityStarted(){
        activity.findViewById(R.id.loginButton).performClick();
        Intent expectedIntent = new Intent(activity, NewsFeedsActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}