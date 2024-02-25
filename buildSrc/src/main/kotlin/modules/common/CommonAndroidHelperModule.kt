package modules.common

object CommonAndroidHelperModule {
    const val nameSpace = "com.vnhanh.common.androidhelper"

    const val projectName = ":common:androidHelper"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
