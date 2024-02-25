package modules.note

object NoteFeatureModule {
    const val nameSpace = "com.vnhanh.demo.feature.note"

    const val projectName = ":feature:note"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
