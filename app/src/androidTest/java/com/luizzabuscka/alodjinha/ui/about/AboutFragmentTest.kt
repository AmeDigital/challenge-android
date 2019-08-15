package com.luizzabuscka.alodjinha.ui.about


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.alodjinha.ui.home.HomeActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AboutFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    private val requestsTimeout = 4000L

    @Test
    fun aboutActivityTest() {
        Thread.sleep(requestsTimeout)
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val navigationMenuItemView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val imageView = onView(
            allOf(
                withId(R.id.ivLogo),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.tvAppName), withText("a Lodjinha"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("a Lodjinha")))

        val textView2 = onView(
            allOf(
                withId(R.id.tvDevName), withText("Luiz Zabuscka"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Luiz Zabuscka")))

        val textView3 = onView(
            allOf(
                withId(R.id.tvDate), withText("10 de agosto de 2019"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("10 de agosto de 2019")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
