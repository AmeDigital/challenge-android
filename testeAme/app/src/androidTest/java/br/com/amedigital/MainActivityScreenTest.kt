package br.com.amedigital

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
open class MainActivityScreenTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun clickOnItemRecyclerCategory() {
        onView(withId(R.id.progressBarCategory)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Thread.sleep(1000)
        onView(withId(R.id.recyclerviewCategory))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
    }

    @Test
    fun clickOnItemRecyclerMoreSales() {
        onView(withId(R.id.progressBarProdMore)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Thread.sleep(1000)
        onView(withId(R.id.recyclerviewMoreSales))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
    }
}