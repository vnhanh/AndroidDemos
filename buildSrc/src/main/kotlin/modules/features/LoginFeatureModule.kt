package modules.features

object LoginFeatureModule {
    const val nameSpace = "com.vnhanh.demo.feature.login"

    const val projectName = ":feature:login"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
