package modules.common

object CommonComposeModule {
    const val nameSpace = "com.vnhanh.common.compose"

    const val PROJECT_NAME = ":common:compose"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
