package modules.common

object CommonLogModule {
    const val nameSpace = "com.vnhanh.common.log"

    const val projectName = ":common:log"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
