package com.luizzabuscka.alodjinha.ui.product


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.alodjinha.ui.home.HomeActivity
import com.luizzabuscka.alodjinha.ui.splash.SplashActivity
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
class ProductActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    private val requestsTimeout = 4000L

    @Test
    fun productActivityTest() {
        Thread.sleep(requestsTimeout)
        val constraintLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rvBestSellers),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            6
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        val imageView = onView(
            allOf(
                withId(R.id.ivProduct),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.svProduct),
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
                withId(R.id.tvName), withText("Fifa 17"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.svProduct),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.tvPriceFrom), withText("De: 299,00"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        3
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("De: 299,00")))

        val textView3 = onView(
            allOf(
                withId(R.id.tvPrice), withText("Por 119,99"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        3
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Por 119,99")))

        val textView4 = onView(
            allOf(
                withId(R.id.tvDescription),
                withText("Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.\n\nCopo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.\n\nA ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.svProduct),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(isDisplayed()))

        val floatingActionButton = onView(
            allOf(
                withId(R.id.fabReserve),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        Thread.sleep(requestsTimeout)

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
