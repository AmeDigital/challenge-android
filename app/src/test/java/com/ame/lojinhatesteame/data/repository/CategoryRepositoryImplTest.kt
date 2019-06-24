package com.ame.lojinhatesteame.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ame.lojinhatesteame.data.model.Category
import com.ame.lojinhatesteame.data.remote.ApiService
import com.ame.lojinhatesteame.data.remote.response.BodyResponseCategory
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryImplTest {
    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var call: Call<BodyResponseCategory>

    @Mock
    private lateinit var categoryCallbacks: CategoryRepository.CategoryCallbacks

    @InjectMocks
    private lateinit var categoryRepository: CategoryRepositoryImpl

    private val category = Category().apply {
        id = 1
        description = "Games"
        urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
    }
    private val categories = ArrayList<Category>().apply { add(category) }
    private val messageError = "Authorization Required"

    @Test
    fun categoryApiCall() {
        `when`(apiService.getCategory()).thenReturn(call)
        `when`(call.execute()).thenReturn(Response.success(BodyResponseCategory(categories)))

        categoryRepository.loadCategory(categoryCallbacks)

        verify(apiService, after(5).atMost(4)).getCategory()
        verify(call, after(5).atMost(4)).execute()

        verifyNoMoreInteractions(apiService)
        verifyNoMoreInteractions(call)
    }

    @Test
    fun categoriesSuccessCallback() {
        `when`(apiService.getCategory()).thenReturn(call)
        `when`(call.execute()).thenReturn(Response.success(BodyResponseCategory(categories)))

        categoryRepository.loadCategory(categoryCallbacks)
        verify(categoryCallbacks, after(5).atMost(4)).onLoadCategorySuccess(categories.toMutableList())
    }

    @Test
    fun categoriesErrorCallback() {
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val body = ResponseBody.create(mediaType, "Unauthorized")

        val response = Response.error<BodyResponseCategory>(401, body)

        `when`(apiService.getCategory()).thenReturn(call)
        `when`(call.execute()).thenReturn(response)

        categoryRepository.loadCategory(categoryCallbacks)
        verify(categoryCallbacks, after(5).atMost(4)).onLoadCategoryError(messageError)
    }
}