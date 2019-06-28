package com.ame.lojinhatesteame.ui

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.ame.lojinhatesteame.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
open class HomeActivityScreenTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    companion object {
        const val TIME_SLEEP_INITIAL : Long = 2000
        const val TIME_SLEEP_FINAL : Long = 1000
    }

    @Before
    fun setUpFragment() {
        activityTestRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun clickOnItemRecyclerCategory() {
        Thread.sleep(TIME_SLEEP_INITIAL)
//        Espresso.onView(ViewMatchers.withId(R.id.progressBarCategory))
//            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewCategory))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(TIME_SLEEP_FINAL)
    }

    @Test
    fun clickOnItemRecyclerBestSales() {
        Thread.sleep(TIME_SLEEP_INITIAL)
//        Espresso.onView(ViewMatchers.withId(R.id.progressBarBestSales))
//            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewBestSales))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(TIME_SLEEP_FINAL)
    }
}