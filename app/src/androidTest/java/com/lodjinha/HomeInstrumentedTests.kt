package com.lodjinha

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.lodjinha.common_teste.FakeData.createFakeProducts
import com.lodjinha.home.di.featureModule
import com.lodjinha.model.Products
import com.lodjinha.repository.AppDispatchers
import com.lodjinha.repository.LodjinhaRepository
import com.lodjinha.repository.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeInstrumentedTests: KoinTest {

    private val lodjinhaRepository = mockk<LodjinhaRepository>()

    @Before
    fun setUp(){
        loadKoinModules(featureModule, module {
            factory {
                AppDispatchers(Dispatchers.Main, Dispatchers.Main)
            }

            factory {
                lodjinhaRepository
            }
        })
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testRecyclerViewTopSellingContainsItems() {
        coEvery {
            lodjinhaRepository.getTopSellingProductsAsync()
        } returns MutableLiveData<Resource<Products>>().apply {
            postValue(Resource.success(createFakeProducts()))
        }
//        launchFragment()

        onView(withId(R.id.recyclerViewTopSelling)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
//        onView(withId(R.id.recyclerViewTopSelling)).check(withItemCount(5))
    }

//    @Test
//    fun testNavigationToDetailScreen() {
//        coEvery {
//            lodjinhaRepository.signUp(fakeEmail)
//        } returns MutableLiveData<Resource<SignUp>>().apply {
//            postValue(Resource.success(fakeSignUp))
//        }
//        val mockNavController = launchFragment()
//
//        onView(ViewMatchers.withId(R.id.signUp)).perform(click())
//
//        verify {
//            mockNavController.navigate(
//                LoginFragmentDirections.actionHomeFragmentToDogBreedsFragment(),
//                any<FragmentNavigator.Extras>()
//            )
//        }
//    }
//
//    private fun launchFragment(): NavController {
//        val mockNavController = mockk<NavController>(relaxed = true)
//        val homeScenario = launchFragmentInContainer<LoginFragment>()
//        homeScenario.onFragment { fragment ->
//            Navigation.setViewNavController(fragment.requireView(), mockNavController)
//        }
//        return mockNavController
//    }
}