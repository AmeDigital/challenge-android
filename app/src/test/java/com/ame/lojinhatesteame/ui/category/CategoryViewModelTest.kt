package com.ame.lojinhatesteame.ui.category

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ame.lojinhatesteame.data.model.Category
import com.ame.lojinhatesteame.data.repository.CategoryRepository
import com.ame.lojinhatesteame.util.any
import com.ame.lojinhatesteame.util.capture
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryViewModelTest {
    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var viewModel: CategoryViewModel

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    @Captor
    private lateinit var categoryCallbacks: ArgumentCaptor<CategoryRepository.CategoryCallbacks>

    @Test
    fun callsCategoryRepository() {
        viewModel.onLoadCategories()
        verify(categoryRepository).loadCategory(any())
    }

    @Test
    fun showsProgressBar() {
        viewModel.onLoadCategories()
        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(true))
    }

    @Test
    fun progressBarIsInvisibleInitially() {
        MatcherAssert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
    }

    @Test
    fun onLoadCategorySuccess() {
        val category = Category()
        with(category) {
            id = 7
            description = "Games"
            urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
        }

        val categories = ArrayList<Category>()
        categories.add(category)

        viewModel.onLoadCategories()
        verify(categoryRepository).loadCategory(capture(categoryCallbacks))
        categoryCallbacks.value.onLoadCategorySuccess(categories.toMutableList())

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.categoryLiveData.value?.get(0)?.id, categories[0].id)
    }

    @Test
    fun onLoadCategoryError() {
        val messageError = "erro ao carregar categoria"

        viewModel.onLoadCategories()
        verify(categoryRepository).loadCategory(capture(categoryCallbacks))
        categoryCallbacks.value.onLoadCategoryError(messageError)

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.showErrorToast.value, messageError)
    }
}