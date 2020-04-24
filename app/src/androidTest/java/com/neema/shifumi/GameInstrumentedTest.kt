package com.neema.shifumi

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GameInstrumentedTest {

    // private val logoMatcher = withId(R.id.iv_logo)
    private val textVersionMatcher = withId(R.id.tv_version)
    private val btnPlayWithComputer = withId(R.id.btn_vs_computer)
    private val btnAutoPlay = withId(R.id.btn_only_computer)

    private val splashScreenWaitingTime: Long = 3000


    /* Instantiate an Activity Object */
    @get:Rule
    val activitySplashScreenRule = ActivityTestRule(SplashScreen::class.java)
    private val activityMainRule = ActivityTestRule(MainActivity::class.java)
    // val activityGameRule = ActivityTestRule(GameActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.neema.shifumi", appContext.packageName)
    }

    /**
     * Je test si l'interface du Splash Screen correspond
     * Et que tout les elements sont présent
     */
    @Test
    fun testSplashScreen() {
        onView(textVersionMatcher).check(matches(withText(R.string.version_app)))
        //onView(logoMatcher).check(matches(withD))
    }

    /**
     * Je test si l'activité fait une transition
     */
    @Test
    fun testSplashScreenOpenMainActivity() {
        Thread.sleep(splashScreenWaitingTime)  // J'attends 3 second qui correspond à la durée d'attente avant de start le MainAcctivity
        assertTrue(activitySplashScreenRule.activity.isFinishing)   // Je test si l'activité SplashScreen est terminé

        activityMainRule.launchActivity(null)
        onView(btnPlayWithComputer).check(matches(withId(R.id.btn_vs_computer)))
        onView(btnAutoPlay).check(matches(withId(R.id.btn_only_computer)))
        //onView(logoMatcher).check(matches(withD))
        Thread.sleep(3000)
    }

    @Test
    fun testClickOpenGameActivity() {
        Thread.sleep(3000)  // J'attends 3 second qui correspond à la durée d'attente avant de start le MainAcctivity
        assertTrue(activitySplashScreenRule.activity.isFinishing)   // Je test si l'activité SplashScreen est terminé

        activityMainRule.launchActivity(null)
        onView(btnPlayWithComputer).check(matches(withId(R.id.btn_vs_computer)))
        onView(btnAutoPlay).check(matches(withId(R.id.btn_only_computer)))

        Thread.sleep(3000)  // Just pour avoir le temps de voir
        onView(btnPlayWithComputer).perform(click())

        val resultData = Intent()
        resultData.extras?.getBoolean("VS_COMPUTER")

        // val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)

        //onView(logoMatcher).check(matches(withD))
        Thread.sleep(3000)
    }

}