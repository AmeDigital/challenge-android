package marco.challenge_android

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.action.ViewActions.swipeUp
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import marco.challenge_android.data.Category
import marco.challenge_android.data.Product
import marco.challenge_android.presenter.ProductDetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProductDetailActivityTest {

    private lateinit var product: Product

    @get:Rule
    var activityTestRule: ActivityTestRule<ProductDetailActivity> =
        object : ActivityTestRule<ProductDetailActivity>(ProductDetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                product = Product(
                    41,
                    "HTML e CSS",
                    "https://images-submarino.b2w.io/produtos/01/00/item/126167/8/126167842_1GG.jpg",
                    "Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>",
                    299.0,
                    19.989999999999998436805981327779591083526611328125,
                    Category(
                        2,
                        "Livros",
                        "http://4.bp.blogspot.com/-6Bta1H9d22g/UJAIJbqcHhI/AAAAAAAAKi4/hvgjWrlFc64/s1600/resenha-missiologia.png"
                    )
                )
                return ProductDetailActivity.newInstance(
                    InstrumentationRegistry.getInstrumentation().targetContext,
                    product
                )
            }
        }

    @Test
    fun verifyViews() {
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.img_product)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar_normal)).check(matches(isDisplayed()))
        onView(withId(R.id.scroll)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_reserve)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyTextsOnViews() {
        onView(withId(R.id.txt_product_name)).check(matches(withText(product.name)))

        val description = Utils.formatHTML(product.description)
        onView(withId(R.id.txt_product_description)).check(matches(withText(description.toString())))

        val priceFrom = Utils.formatCurrencyNew(product.priceFrom)
        onView(withId(R.id.txt_price_from)).check(matches(withText("De: $priceFrom")))

        val priceTo = Utils.formatCurrencyNew(product.priceTo)
        onView(withId(R.id.txt_price_to)).check(matches(withText("Por: $priceTo")))
    }

    @Test
    fun shouldHideProductImageWhenSwipeUpAndShowImageWhenSwipeDown() {
        onView(withId(android.R.id.content)).perform(swipeUp())
        onView(withId(android.R.id.content)).perform(swipeDown())
    }

}