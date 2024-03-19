import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object BuildTypeConfiguration {
    object Debug {
        const val name = "debug"
        const val isDebuggable = true
        const val IS_MINIFY_ENABLED = false
        const val isShrinkResources = false
        const val crashlytics = "false"
    }

    object Release {
        const val name = "release"
        const val isDebuggable = false
        const val IS_MINIFY_ENABLED = true
        const val isShrinkResources = true
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
        const val crashlytics = "true"
    }

    fun getFileBuildName(
        buildTypeName: String,
        flavor: String,
        versionName: String,
    ): String {
        val projectName = "Android_Demo"
        val separateChar = "-"
        val formattedDate: String = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
        val fileExtension = ".apk"
        return "$projectName$separateChar$versionName$separateChar$flavor$separateChar$buildTypeName$separateChar$formattedDate$fileExtension"
    }
}
