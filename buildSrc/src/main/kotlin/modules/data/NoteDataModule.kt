package modules.data

object NoteDataModule {
    const val NAME_SPACE = "com.vnhanh.demo.data.note"

    const val PROJECT_NAME = ":data:note"

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val CONSUME_PROGUARD_FILES = "consumer-rules.pro"

    object Release {
        const val PROGUARD_ANDROID_OPTIMIZE = "proguard-android-optimize.txt"
        const val PROGUARD_RULES = "proguard-rules.pro"
    }
}
