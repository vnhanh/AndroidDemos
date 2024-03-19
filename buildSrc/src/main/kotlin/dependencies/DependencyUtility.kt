package dependencies

import TestDependencies
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.room() {
    implementation(RoomDependencies.roomRuntime)
    implementation(RoomDependencies.roomKtx)
    ksp(RoomDependencies.kspRoomCompiler)
    annotationProcessor(RoomDependencies.annotationProcessorRoomCompiler)
}

fun DependencyHandler.koin() {
    implementation(KoinDependencies.koinCore)
    implementation(KoinDependencies.koinAndroid)
    implementation(KoinDependencies.koinCompose)
    implementation(KoinDependencies.koinAnnotation)
    ksp(KoinDependencies.ksp)

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

fun DependencyHandler.dataModuleDependencies() {
    implementation(GoogleDependencies.gson)
}

fun DependencyHandler.test() {
    testImplementation(TestDependencies.jUnit)
    androidTestImplementation(TestDependencies.jUnitExt)
    androidTestImplementation(TestDependencies.espressoCore)
}
