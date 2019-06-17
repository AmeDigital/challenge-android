package marco.challenge_android

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import kotlinx.android.synthetic.main.activity_category.*
import marco.challenge_android.data.Category
import marco.challenge_android.presenter.CategoryActivity
import marco.challenge_android.presenter.category.CategoryAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CategoryActivityTest {

    private lateinit var category: Category

    @get:Rule
    var activityTestRule: ActivityTestRule<CategoryActivity>
        = ActivityTestRule<CategoryActivity>(CategoryActivity::class.java, true, false)

    @Test
    fun verifyViews() {
        category = Category(1, "Games", null)
        launchActivity()

        Thread.sleep(2000)

        onView(withId(R.id.toolbar_normal)).check(matches(isDisplayed()))
        onView(withId(R.id.products_list)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldScrollProductsToLastAndReturnToFirstPosition() {
        category = Category(2, "Livros", null)
        launchActivity()

        Thread.sleep(2000)

        onView(withId(R.id.products_list))
            .perform(RecyclerViewActions.scrollToPosition<CategoryAdapter.CategoryProductsViewHolder<Any>>(
                activityTestRule.activity.products_list.adapter?.itemCount?.minus(1)!!
            ))
        onView(withId(R.id.products_list))
            .perform(RecyclerViewActions.scrollToPosition<CategoryAdapter.CategoryProductsViewHolder<Any>>(0))
    }

    @Test
    fun shouldScrollShowNoProductsWhenListIsEmpty() {
        category = Category(9, "Papelaria", null)
        launchActivity()

        Thread.sleep(2000)

        onView(withId(R.id.txt_no_products)).check(matches(isDisplayed()))
    }

    private fun launchActivity() {
        val intent = CategoryActivity.newInstance(InstrumentationRegistry.getInstrumentation().targetContext, category)
        activityTestRule.launchActivity(intent)
    }

}