package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.room() {
    implementation(RoomDependencies.roomRuntime)
    implementation(RoomDependencies.roomKtx)
    kapt(RoomDependencies.kspRoomCompiler)
    annotationProcessor(RoomDependencies.annotationProcessorRoomCompiler)
}

fun DependencyHandler.koin() {
    implementation(KoinDependencies.koinCore)
    implementation(KoinDependencies.koinAndroid)
    implementation(KoinDependencies.koinCompose)
    testImplementation(KoinDependencies.koinTest)
}

fun DependencyHandler.hilt() {
    implementation(HiltDependency.hilt)
    implementation(HiltDependency.hiltNavigation)
    kapt(HiltDependency.kaptHiltCompiler)
}

fun DependencyHandler.basicAndroidComponent() {
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(LifeCycleDependencies.lifecycleViewModelKtx)
}

fun DependencyHandler.test() {
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
