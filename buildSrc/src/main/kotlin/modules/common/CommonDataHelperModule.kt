package modules.common

object CommonDataHelperModule {
    const val nameSpace = "com.vnhanh.common.datahelper"

    const val projectName = ":common:dataHelper"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
