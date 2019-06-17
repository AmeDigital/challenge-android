package marco.challenge_android

import android.app.Application
import java.io.File

class MainApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApp? = null
        fun isNetworkAvailable(): Boolean {
            return Utils.isNetworkAvailable(instance?.applicationContext)
        }
        fun getFileCacheDir(): File? {
            return instance?.cacheDir
        }

    }
}
