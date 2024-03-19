package modules.base

object BaseAndroidModule {
    const val nameSpace = "com.vnhanh.base.android"

    const val PROJECT_NAME = ":base:android"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val isMinifyEnabled = true
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
