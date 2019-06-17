package marco.challenge_android

import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import marco.challenge_android.presenter.MainActivity
import marco.challenge_android.presenter.SplashScreenActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashScreenActivityTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<SplashScreenActivity> =
        ActivityTestRule(SplashScreenActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun clear() {
        Intents.release()
    }

    @Test
    fun verifyMainScreenIsShowingAfterThis() {
        activityTestRule.launchActivity(Intent())
        intended(hasComponent(MainActivity::class.java.name))
    }

}