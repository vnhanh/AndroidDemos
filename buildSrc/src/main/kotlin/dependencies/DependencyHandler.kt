package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.api(dependency: String) {
    add("api", dependency)
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandler.test(dependency: String) {
    add("test", dependency)
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.annotationProcessor(dependency: String) {
    add("annotationProcessor", dependency)
}
