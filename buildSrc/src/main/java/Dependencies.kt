object Params {
    /**
     * @param type variable of [gradle]
     */
    const val ApiKeyParam = "API_KEY"
    /**
     * @param type variable of [BaseUrl]
     */
    const val BaseUrl = "BASE_URL"
    /**
     * @param type variable of [OriginalImageUrl]
     */
    const val OriginalImageUrl = "ORIGINAL_IMAGE_URL"
    /**
     * @param type variable of [String]
     */
    const val String = "String"
}

object Versions {
    /**
     * @param type variable of [namespace]
     */
    const val namespace = "com.example.kreditplusandroidassessment"
    /**
     * @param type variable of [complerSdk]
     */
    const val complerSdk = 32
    /**
     * @param type variable of [applicationId]
     */
    const val applicationId = "com.example.kreditplusandroidassessment"
    /**
     * @param type variable of [minSdk]
     */
    const val minSdk = 26
    /**
     * @param type variable of [targetSdk]
     */
    const val targetSdk = 32
    /**
     * @param type variable of [versionCode]
     */
    const val versionCode = 1
    /**
     * @param type variable of [versionName]
     */
    const val versionName = "1.0"
    /**
     * @param type variable of [koin]
     */
    const val koin = "3.3.0"
    /**
     * @param type variable of [core]
     */
    const val core = "1.8.0"
    /**
     * @param type variable of [junit]
     */
    const val junit = "4.13.2"
    /**
     * @param type variable of [extJunit]
     */
    const val extJunit = "1.1.4"
    /**
     * @param type variable of [extJunit]
     */
    const val espresso = "3.5.0"
    /**
     * @param type variable of [recyclerView]
     */
    const val recyclerView = "1.2.1"
    /**
     * @param type variable of [legacy]
     */
    const val legacy = "1.0.0"
    /**
     * @param type variable of [lifecycle]
     */
    const val lifecycle = "2.5.1"
    /**
     * @param type variable of [navigationFragment]
     */
    const val navigationFragment = "2.5.2"
    /**
     * @param type variable of [navigationUi]
     */
    const val navigationUi = "2.5.2"
    /**
     * @param type variable of [lottie]
     */
    const val lottie = "5.2.0"
    /**
     * @param type variable of [splash]
     */
    const val splash = "1.0.0-beta01"
    /**
     * @param type variable of [room]
     */
    const val room = "2.4.3"
    /**
     * @param type variable of [glide]
     */
    const val glide = "4.12.0"
    /**
     * @param type variable of [nav]
     */
    const val nav = "2.5.3"
    /**
     * @param type variable of [dotViewPager]
     */
    const val dotViewPager = "4.3"
    /**
     * @param type variable of [retrofit]
     */
    const val retrofit = "2.9.0"
    /**
     * @param type variable of [okhttp]
     */
    const val okhttp = "5.0.0-alpha.2"
    /**
     * @param type variable of [coroutineCore]
     */
    const val coroutineCore = "1.4.1"
    /**
     * @param type variable of [coroutineAndroid]
     */
    const val coroutineAndroid = "1.6.1"
    /**
     * @param type variable of [gson]
     */
    const val gson = "2.9.0"
    /**
     * @param type variable of [activity]
     */
    const val activity = "1.5.1"
    /**
     * @param type variable of [fragment]
     */
    const val fragment = "1.5.4"
    /**
     * @param type variable of [dataStore]
     */
    const val dataStore = "1.0.0"
    /**
     * @param type variable of [youtube]
     */
    const val youtube = "11.1.0"
}

object KotlinLibraries {
    /**
     * @param type dependecies of type [coroutineCore]
     */
    val coroutineCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCore}" }
    /**
     * @param type dependecies of type [coroutineAndroid]
     */
    val coroutineAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineAndroid}" }

}

object AndroidLibraries {
    /**
     * @param type dependecies of type [core]
     */
    val core by lazy { "androidx.core:core-ktx:${Versions.core}" }
    /**
     * @param type dependecies of type [recyclerView]
     */
    val recyclerView by lazy { "androidx.recyclerview:recyclerview:${Versions.recyclerView}" }
    /**
     * @param type dependecies of type [legacy]
     */
    val legacy by lazy { "androidx.legacy:legacy-support-v4:${Versions.legacy}" }
    /**
     * @param type dependecies of type [lifecycleLiveData]
     */
    val lifecycleLiveData by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
    /**
     * @param type dependecies of type [lifecycleViewModel]
     */
    val lifecycleViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
    /**
     * @param type dependecies of type [lifecycleRuneTime]
     */
    val lifecycleRuneTime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}" }
    /**
     * @param type dependecies of type [navigationFragment]
     */
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}" }
    /**
     * @param type dependecies of type [navigationUi]
     */
    val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}" }
    /**
     * @param type dependecies of type [splash]
     */
    val splash by lazy { "androidx.core:core-splashscreen:${Versions.splash}" }
    /**
     * @param type dependecies of type [activity]
     */
    val activity by lazy { "androidx.activity:activity-ktx:${Versions.activity}" }
    /**
     * @param type dependecies of type [fragment]
     */
    val fragment by lazy { "androidx.fragment:fragment-ktx:${Versions.fragment}" }
    /**
     * @param type dependecies of type [dataStore]
     */
    val dataStore by lazy { "androidx.datastore:datastore-preferences:${Versions.dataStore}" }
    /**
     * @param type dependecies of type [roomRuntime]
     */
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    /**
     * @param type dependecies of type [roomKtx]
     */
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }
    /**
     * @param type dependecies of type [navFragment]
     */
    val navFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.nav}" }
    /**
     * @param type dependecies of type [navUi]
     */
    val navUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.nav}" }
}

object Libraries {
    /**
     * @param type dependecies of type [koinAndroid]
     */
     val koinAndroid by lazy { "io.insert-koin:koin-android:${Versions.koin}" }
    /**
     * @param type dependecies of type [lottie]
     */
    val lottie by lazy { "com.airbnb.android:lottie:${Versions.lottie}" }
    /**
     * @param type dependecies of type [dotViewPager]
     */
    val dotViewPager by lazy { "com.tbuonomo:dotsindicator:${Versions.dotViewPager}" }
    /**
     * @param type dependecies of type [glide]
     */
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    /**
     * @param type dependecies of type [retrofit]
     */
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    /**
     * @param type dependecies of type [retrofitGson]
     */
    val retrofitGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    /**
     * @param type dependecies of type [gson]
     */
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
    /**
     * @param type dependecies of type [okhttp]
     */
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
    /**
     * @param type dependecies of type [logging]
     */
    val logging by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}" }
    /**
     * @param type dependecies of type [youtube]
     */
    val youtube by lazy { "com.pierfrancescosoffritti.androidyoutubeplayer:core:${Versions.youtube}" }
}

object Kapt {
    /**
     * @param type dependecies of type [roomCompiler]
     */
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    /**
     * @param type dependecies of type [glideCompiler]
     */
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }
    /**
     * @param type dependecies of type [lifecycleCompiler]
     */
    val lifecycleCompiler by lazy { "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}" }
}

object AnnotationProcessor {
    /**
     * @param type dependecies of type [roomCompiler]
     */
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }

}

object TestLibraries {
    /**
     * @param type dependecies of type [junit]
     */
    val junit by lazy { "junit:junit:${Versions.junit}" }
    /**
     * @param type dependecies of type [extJunit]
     */
    val extJunit by lazy { "androidx.test.ext:junit:${Versions.extJunit}" }
    /**
     * @param type dependecies of type [espresso]
     */
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
}