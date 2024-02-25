package dependencies

object AndroidXDependencies {
    private const val appCompatVersion = "1.6.1"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val coreKtxVersion = "1.12.0"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val activityKtxVersion = "1.8.1"
    const val activityKtx = "androidx.activity:activity-ktx:$activityKtxVersion"

    private const val fragmentKtxVersion = "1.6.2"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentKtxVersion"

    private const val navFragmentKtxVersion = "2.7.7"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:$navFragmentKtxVersion"

    private const val navigationUiKtxVersion = "2.7.7"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationUiKtxVersion"

    private const val constraintLayoutVersion = "2.1.4"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

    private const val annotationJvmVersion = "1.7.1"
    const val annotationJvm = "androidx.annotation:annotation-jvm:$annotationJvmVersion"
}

object GoogleDependencies {
    private const val materialVersion = "1.11.0"
    const val material = "com.google.android.material:material:$materialVersion"

    private const val gsonVersion = "2.9.0"
    const val gson = "com.google.code.gson:gson:$gsonVersion"
}

object FirebaseDependencies {
    const val boomVersion = "32.7.2"
    const val boom = "com.google.firebase:firebase-bom"

    const val crashlyticsVersion = "18.6.2"
    const val crashlytics = "com.google.firebase:firebase-crashlytics:$crashlyticsVersion"
}

object LifeCycleDependencies {
    private const val lifecycleVersion = "2.6.2"

    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
}

object RoomDependencies {
    private const val roomVersion = "2.6.0"

    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val annotationProcessorRoomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val kspRoomCompiler = "androidx.room:room-compiler:$roomVersion"
}

object RetrofitDependencies {
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"

    private const val gsonConverterVersion = "2.9.0"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:$gsonConverterVersion"

    private const val loggingInterceptorVersion = "4.10.0"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"
}

object HiltDependency {
    private const val hiltVersion = "2.48"
    private const val hiltNavigationCompose = "1.1.0"
    private const val hiltAndroidCompiler = "2.46.1"

    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationCompose"
    const val kaptHiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltAndroidCompiler"
}

object ImageDependencies {
    private const val frescoVersion = "1.11.0"
    const val fresco = "com.facebook.fresco:fresco:$frescoVersion"

    private const val frescoImageViewerVersion = "0.5.0"
    const val frescoImageViewer = "com.github.stfalcon:frescoimageviewer:$frescoImageViewerVersion"

    private const val imagePickerVersion = "2.1"
    const val imagePicker = "com.github.dhaval2404:imagepicker:$imagePickerVersion"
}

object ChartDependencies {
    private const val mpAndroidChartVersion = "v3.1.0"
    const val mpAndroidChart = "com.github.PhilJay:MPAndroidChart:$mpAndroidChartVersion"
}

object DateTimeDependencies {
    private const val jodaVersion = "2.9.1"
    const val joda = "joda-time:joda-time:$jodaVersion"
}

object LogDependencies {
    private const val timberVersion = "5.0.1"

    const val timber = "com.jakewharton.timber:timber:$timberVersion"
}
