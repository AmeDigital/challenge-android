package com.ame.lojinhatesteame.ui.product

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Category
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
open class ProductByCategoryActivityScreenTest {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<ProductByCategoryActivity> =
        object : ActivityTestRule<ProductByCategoryActivity>(ProductByCategoryActivity::class.java) {
            override fun getActivityIntent(): Intent {

                val category = Category()
                with(category) {
                    id = 1
                    description = "Games"
                    urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
                }

                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, ProductByCategoryActivity::class.java)
                result.putExtra("category", category)
                return result
            }
        }

    companion object {
        const val TIME_SLEEP_INITIAL : Long = 2000
        const val TIME_SLEEP_FINAL : Long = 1000
    }

    @Before
    fun setUpFragment() {
        activityTestRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun showTitleActionBar() {
        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withResourceName("action_bar"))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("games")))
    }

    @Test
    fun clickOnItemRecyclerCategory() {
        Thread.sleep(TIME_SLEEP_INITIAL)
        Espresso.onView(ViewMatchers.withId(R.id.progressBarProdCategory))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewProductCategory))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(TIME_SLEEP_FINAL)
    }
}