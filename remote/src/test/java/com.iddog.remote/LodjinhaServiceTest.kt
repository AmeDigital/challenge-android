import com.iddog.remote.base.BaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection

class LodjinhaServiceTest: BaseTest() {

    @Test
    fun `get banner`() {
        mockHttpResponse(mockServer, "banners.json", HttpURLConnection.HTTP_OK)

        runBlocking {
            val banners = service.bannerAsync().await()
            Assert.assertEquals(
                "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png",
                banners.data[0].linkUrl
            )
            Assert.assertEquals(1, banners.data[0].id)
        }
    }

    @Test(expected = HttpException::class)
    fun `get banner fail`() {
        mockHttpResponse(mockServer,"banners.json", HttpURLConnection.HTTP_FORBIDDEN)

        runBlocking {
            service.bannerAsync().await()
        }
    }

    @Test
    fun `get feed category`() {
        mockHttpResponse(mockServer,"categories.json", HttpURLConnection.HTTP_OK)

        runBlocking {
            val categories = service.categoriesAsync().await()
            Assert.assertEquals(2, categories.data[1].id)
            Assert.assertEquals("Livros", categories.data[1].description)
        }
    }

    @Test(expected = HttpException::class)
    fun `get feed category and fail`() {
        mockHttpResponse(mockServer,"categories.json", HttpURLConnection.HTTP_FORBIDDEN)

        runBlocking {
            service.categoriesAsync().await()
        }
    }

    @Test
    fun `get products category`() {
        mockHttpResponse(mockServer,"products.json", HttpURLConnection.HTTP_OK)

        runBlocking {
            val products = service.productsAsync().await()
            Assert.assertEquals(75, products.total)
            Assert.assertEquals("Games", products.data[2].category.description)
            Assert.assertEquals("Metal Gear V", products.data[2].name)
        }
    }

    @Test(expected = HttpException::class)
    fun `get products and fail`() {
        mockHttpResponse(mockServer,"products.json", HttpURLConnection.HTTP_FORBIDDEN)

        runBlocking {
            service.productsAsync().await()
        }
    }

    @Test
    fun `get top selling products category`() {
        mockHttpResponse(mockServer,"products.json", HttpURLConnection.HTTP_OK)

        runBlocking {
            val products = service.topSellingProductsAsync().await()
            Assert.assertEquals(75, products.total)
            Assert.assertEquals("Games", products.data[3].category.description)
            Assert.assertEquals("Gear of War 4", products.data[3].name)
        }
    }

    @Test(expected = HttpException::class)
    fun `get top selling products and fail`() {
        mockHttpResponse(mockServer,"products.json", HttpURLConnection.HTTP_FORBIDDEN)

        runBlocking {
            service.topSellingProductsAsync().await()
        }
    }

    @Test
    fun `get product category`() {
        mockHttpResponse(mockServer,"product.json", HttpURLConnection.HTTP_OK)

        runBlocking {
            val product = service.productAsync(1).await()
            Assert.assertEquals(1, product.id)
            Assert.assertEquals("Games", product.category.description)
            Assert.assertEquals("Game Horizon Zero Down", product.name)
        }
    }

    @Test(expected = HttpException::class)
    fun `get product and fail`() {
        mockHttpResponse(mockServer,"product.json", HttpURLConnection.HTTP_FORBIDDEN)

        runBlocking {
            service.productAsync(1).await()
        }
    }
}
