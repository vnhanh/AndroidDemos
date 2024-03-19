package modules.features

object AuthenticationFeatureModule {
    const val nameSpace = "com.vnhanh.demo.feature.authentication"

    const val projectName = ":feature:authentication"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    const val buildConfig = true

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
