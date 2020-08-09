package com.moringaschool.hellozuz;

import android.os.Build;
import android.widget.TextView;

import com.moringaschool.hellozuz.ui.MessageListActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class MessageListActivityTest {
    private MessageListActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(MessageListActivity.class);
    }

    @Test
    public void validateTextViewContent(){
        TextView pageHeader = activity.findViewById(R.id.pageHeader);
        assertTrue("MESSAGES".equals(pageHeader.getText().toString()));
    }
}