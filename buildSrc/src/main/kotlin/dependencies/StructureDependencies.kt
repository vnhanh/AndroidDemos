package dependencies

import ProjectPlugins

object AndroidXDependencies {
    private const val appCompatVersion = "1.6.1"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val coreKtxVersion = "1.12.0"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val activityKtxVersion = "1.8.1"
    const val activityKtx = "androidx.activity:activity-ktx:$activityKtxVersion"

    private const val fragmentKtxVersion = "1.6.2"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentKtxVersion"

    private const val navFragmentKtxVersion = "2.7.7"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:$navFragmentKtxVersion"

    private const val navigationUiKtxVersion = "2.7.7"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationUiKtxVersion"

    private const val constraintLayoutVersion = "2.1.4"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

    private const val annotationJvmVersion = "1.7.1"
    const val annotationJvm = "androidx.annotation:annotation-jvm:$annotationJvmVersion"

    private const val dataStoreVersion = "1.0.0"
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:$dataStoreVersion"
    const val dataStoreProto = "androidx.datastore:datastore-core:$dataStoreVersion"
}

object KotlinDependencies {
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${ProjectPlugins.kotlinAndroidVersion}"
}

object LifeCycleDependencies {
    private const val lifecycleVersion = "2.6.2"

    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
}
