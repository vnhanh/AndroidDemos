
// Used for Top project level build
object ProjectPlugins {
    const val androidVersion = "8.2.2"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"

    const val kotlinAndroidVersion = "1.9.22"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"

    const val mapsPlatformSecretGradleVersion = "2.0.1"
    const val mapsPlatformSecretGradle = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"

    const val googleGmsServicesVersion = "4.4.1"
    const val googleGmsServices = "com.google.gms.google-services"

    const val ksp_version = "1.9.22-1.0.17"
    const val kspDevTools = "com.google.devtools.ksp"

    const val hiltVersion = "2.46.1"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin"

    const val navigationSafeArgsGradleVersion = "2.7.5"
    const val navigationSafeArgsGradle = "androidx.navigation:navigation-safe-args-gradle-plugin"
}

// Used for Module level build
object ModulePlugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"

    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinParcelize = "kotlin-parcelize"
    const val ksp = "com.google.devtools.ksp"
}
