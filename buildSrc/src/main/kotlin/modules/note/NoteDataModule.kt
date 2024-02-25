package modules.note

object NoteDataModule {
    const val nameSpace = "com.vnhanh.demo.data.note"

    const val projectName = ":data:note"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }
}
