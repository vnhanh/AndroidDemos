package modules.network

object NetworkImplModule {
    const val nameSpace = "com.vnhanh.network.impl"

    const val projectName = ":network:impl"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
