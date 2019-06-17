package marco.challenge_android

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UtilsTest {

    @Mock
    private lateinit var networkInfo: NetworkInfo

    @Mock
    private lateinit var packageManager: PackageManager

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var connectivityManager: ConnectivityManager

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(context.packageManager).thenReturn(packageManager)
        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        Mockito.`when`(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
    }

    @Test
    fun shouldReturnTrueWhenNetworkIsAvailable() {
        Mockito.`when`(networkInfo.isConnected).thenReturn(true)
        Utils.isNetworkAvailable(context)
        assertEquals(networkInfo.isConnected, true)
    }

    @Test
    fun shouldReturnFalseWhenNetworkIsAvailable() {
        Mockito.`when`(networkInfo.isConnected).thenReturn(false)
        Utils.isNetworkAvailable(context)
        assertEquals(networkInfo.isConnected, false)
    }

    @Test
    fun shouldReturnTrueDoubleInCurrencyValue() {
        val doubleValue = 7.12
        val currencyValue = Utils.formatCurrencyNew(doubleValue)
        assertEquals("R$ 7,12", currencyValue)
    }

    @Test
    fun shouldReturnFalseDoubleInCurrencyValue() {
        val doubleValue = 19.00
        val currencyValue = Utils.formatCurrencyNew(doubleValue)
        assertNotEquals("R$ 19.00", currencyValue)
    }

}