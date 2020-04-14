package com.neema.shifumi;

import android.app.Activity;
import android.content.Context;

import androidx.core.app.ActivityCompat;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SplashScreenInstrumentedTest {

    @Rule
    public ActivityTestRule<SplashScreen> activityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.neema.shifumi", appContext.getPackageName());
    }

    @Test
    public void testUI() {
        Activity activity = activityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.footer));
    }



    @Test
    public void useAppContext1() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.neema.shifumi", appContext.getPackageName());
    }
}
