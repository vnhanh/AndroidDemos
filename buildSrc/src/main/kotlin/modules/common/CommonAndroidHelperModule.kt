package modules.common

object CommonAndroidHelperModule {
    const val NAME_SPACE = "com.vnhanh.common.androidhelper"

    const val PROJECT_NAME = ":common:androidHelper"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    const val BUILD_CONFIG = true

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
