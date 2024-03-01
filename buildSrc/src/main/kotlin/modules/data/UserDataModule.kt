package modules.data

object UserDataModule {
    const val nameSpace = "com.vnhanh.demo.data.user"

    const val projectName = ":data:user"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
