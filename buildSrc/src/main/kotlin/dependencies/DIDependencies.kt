package dependencies


object KoinDependencies {
    private const val koinVersion = "3.5.0"

    const val koinCore = "io.insert-koin:koin-core:$koinVersion"
    const val koinAndroid = "io.insert-koin:koin-android:$koinVersion"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:$koinVersion"
    const val koinTest = "io.insert-koin:koin-test:$koinVersion"

    private const val kspVersion = "1.3.1"
    const val ksp = "io.insert-koin:koin-ksp-compiler:$kspVersion"
    const val koinAnnotation = "io.insert-koin:koin-annotations:$kspVersion"
}

object HiltDependency {
    private const val hiltVersion = "2.48"
    private const val hiltNavigationCompose = "1.1.0"
    private const val hiltAndroidCompiler = "2.46.1"

    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationCompose"
    const val kaptHiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltAndroidCompiler"
}
