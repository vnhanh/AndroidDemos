package modules

object AppModule {
    const val nameSpace = "com.alanvo.androiddemo"
    const val applicationId = "com.alanvo.androiddemo"
    const val versionCode = 1
    const val versionName = "0.0.1"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }

    const val viewBinding = true
    const val buildConfig = true
}
