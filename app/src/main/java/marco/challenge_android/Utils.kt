package marco.challenge_android

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.text.Html
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

object Utils{

    fun loadImage(context: Context, file: String?, imgView: ImageView) {
        Glide.with(context)
            .load(file)
            .error(R.drawable.logo_menu)
            .into(imgView)
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun formatCurrencyNew(value: Double?): String {
        return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(value)
    }

    fun formatHTML(description: String?): CharSequence? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
        else Html.fromHtml(description)
    }
}