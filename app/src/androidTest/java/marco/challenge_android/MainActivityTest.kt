package marco.challenge_android

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions.close
import android.support.test.espresso.contrib.DrawerActions.open
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import kotlinx.android.synthetic.main.fragment_home.*
import marco.challenge_android.presenter.CategoryActivity
import marco.challenge_android.presenter.MainActivity
import marco.challenge_android.presenter.category.CategoryAdapter
import marco.challenge_android.presenter.home.HomeCategoriesAdapter
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        activityTestRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun verifyViews() {
        onView(withId(R.id.toolbar_main)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToHomeInDrawerLayout() {
        onView(withId(R.id.drawer_layout)).perform(open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_home))
        onView(withId(R.id.drawer_layout)).perform(close())

        onView(allOf(withId(R.id.pager_banner), withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToAboutInDrawerLayout() {
        onView(withId(R.id.drawer_layout)).perform(open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_about))
        onView(withId(R.id.drawer_layout)).perform(close())

        onView(allOf(withId(R.id.imgLogo), withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.txtLogo), withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.txtDeveloper), withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.txtDevelopmentDate), withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldValidateIfCategoriesListIsShowing() {
        Thread.sleep(2000)

        onView(withId(R.id.categories_list)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldScrollCategoriesToLastAndReturnToFirstPosition() {
        onView(withId(R.id.categories_list))
            .perform(RecyclerViewActions.scrollToPosition<HomeCategoriesAdapter.HomeCategoriesViewHolder>(
                activityTestRule.activity.categories_list.adapter?.itemCount?.minus(1)!!
            ))
        onView(withId(R.id.categories_list))
            .perform(RecyclerViewActions.scrollToPosition<HomeCategoriesAdapter.HomeCategoriesViewHolder>(0))
    }

    @Test
    fun shouldOpenCategoryActivityWhenUserClickOnItemCategoryList() {
        Thread.sleep(2000)

        onView(withId(R.id.categories_list))
            .perform(RecyclerViewActions.scrollToPosition<HomeCategoriesAdapter.HomeCategoriesViewHolder>(7))

        Intents.init()
        onView(withId(R.id.categories_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<HomeCategoriesAdapter.HomeCategoriesViewHolder>(7, click()))

        intended(hasComponent(CategoryActivity::class.java.name))

        onView(withId(R.id.products_list)).check(matches(isDisplayed()))
        Intents.release()
    }

    @Test
    fun shouldScrollBestSellerToLastAndReturnToFirstPosition() {
        Thread.sleep(2000)

        onView(withId(R.id.best_sellers_list))
            .perform(RecyclerViewActions.scrollToPosition<CategoryAdapter.CategoryProductsViewHolder<Any>>(
                activityTestRule.activity.best_sellers_list.adapter?.itemCount?.minus(1)!!
            ))
        onView(withId(R.id.best_sellers_list))
            .perform(RecyclerViewActions.scrollToPosition<CategoryAdapter.CategoryProductsViewHolder<Any>>(0))
    }

}