package com.neema.shifumi

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    // private val logoMatcher = withId(R.id.iv_logo)
    private val textVersionMatcher = withId(R.id.tv_version)
    private val btnPlayWithComputer = withId(R.id.btn_vs_computer)
    private val btnAutoPlay = withId(R.id.btn_only_computer)

    private val splashScreenWaitingTime: Long = 3000


    /* Instantiate an Activity Object */
    @get:Rule
    val activitySplashScreenRule = ActivityTestRule(SplashScreen::class.java)
    private val activityMainActivityRule = ActivityTestRule(MainActivity::class.java)

    // TEST UI
    /**
     * Je test si l'interface du Splash Screen correspond
     * Et que tout les elements sont présent
     */
    @Test
    fun testUISplashScreen() {
        onView(textVersionMatcher).check(matches(withText(R.string.version_app)))
        //onView(logoMatcher).check(matches(withD))

        Thread.sleep(splashScreenWaitingTime)
    }

    // TEST UX
    /**
     * Je test si l'activité fait une transition
     */
    @Test
    fun testSplashScreenOpenMainActivity() {
        Thread.sleep(splashScreenWaitingTime)  // J'attends 3 second qui correspond à la durée d'attente avant de start le MainAcctivity
        assertTrue(activitySplashScreenRule.activity.isFinishing)   // Je test si l'activité SplashScreen est terminé

        activityMainActivityRule.launchActivity(null)
        onView(btnPlayWithComputer).check(matches(withId(R.id.btn_vs_computer)))
        onView(btnAutoPlay).check(matches(withId(R.id.btn_only_computer)))
        //onView(logoMatcher).check(matches(withD))
        Thread.sleep(3000)
    }


    /*// https://medium.com/@dbottillo/android-ui-test-espresso-matcher-for-imageview-1a28c832626f
    fun withDrawable(resourceId: Int): Matcher<View> {
        return DrawableMatcher
    }*/

}