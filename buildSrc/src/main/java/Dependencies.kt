object ApplicationId {
    const val id = "com.lodjinha"
}

object Modules {
    const val app = ":app"
    const val remote = ":remote"
    const val model = ":model"
    const val repository = ":repository"
    const val commonTest = ":common_teste"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Plugins {
    const val application = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object Versions {
    const val compileSdk = 28
    const val minSdk = 15
    const val targetSdk = 28
    const val kotlin = "1.3.31"
    const val picasso = "2.71828"
    const val koin = "1.0.2"
    const val lifecycle = "2.1.0-alpha04"
    const val constraintlayout = "1.1.3"
    const val appCompat = "1.1.0-alpha04"
    const val recyclerview = "1.0.0"
    const val nav = "2.0.0"
    const val androidTestRunner = "1.1.2-alpha02"
    const val espressoCore = "3.2.0-alpha02"
    const val archCoreTest = "2.0.0"
    const val androidJunit = "1.1.0"
    const val fragmentTest = "1.1.0-alpha06"
    const val mockwebserver = "2.7.5"
    const val mockk = "1.9.2"
    const val retrofit = "2.5.0"
    const val retrofitCoroutines = "0.9.2"
    const val retrofitGson = "2.4.0"
    const val coroutines = "1.1.1"
    const val databinding = "3.3.2"
    const val gson = "2.8.5"
    const val okHttp = "3.12.1"
    const val coreKtx = "1.1.0-alpha04"
}

object AndroidLibs {
    const val kotlinCoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val androidxCore = "androidx.core:core-ktx:1.0.1"
    const val constrainLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
}

object Libs {
    const val retrofitCoroutineAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val picassoTransformations = "jp.wasabeef:picasso-transformations:2.2.1"

    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"

    const val klint = "com.github.shyiko:ktlint:0.15.0"

    const val zoomImage = "com.jsibbold:zoomage:1.2.0"
}

object KotlinLibs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object TestLibs {
    const val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
    const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCoreTest}"
    const val junit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val fragmentNav = "androidx.fragment:fragment-testing:${Versions.fragmentTest}"
    const val koin = "org.koin:koin-test:${Versions.koin}"
    const val mockWebServer = "com.squareup.okhttp:mockwebserver:${Versions.mockwebserver}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val databinding = "androidx.databinding:databinding-compiler:${Versions.databinding}"
}

object ProjectDeps {
    const val gradle = "com.android.tools.build:gradle:3.4.1"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafe = "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0"
}
