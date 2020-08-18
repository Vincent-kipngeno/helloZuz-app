package com.moringaschool.hellozuz;

import android.content.Intent;
import android.os.Build;

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
public class NewsFeedsActivityTest {
    private NewsFeedsActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(NewsFeedsActivity.class);
    }

    @Test
    public void secondActivityStarted(){
        activity.findViewById(R.id.directMessages).performClick();
        Intent expectedIntent = new Intent(activity, MessageListActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}