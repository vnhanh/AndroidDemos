import modules.app.AppModule
import modules.base.BaseAndroidModule
import modules.common.CommonAndroidHelperModule
import modules.common.CommonComposeModule
import modules.common.CommonDataHelperModule
import modules.common.CommonLogModule
import modules.network.NetworkBaseModule
import modules.network.NetworkImplModule
import modules.note.NoteDataModule
import modules.note.NoteFeatureModule

plugins {
    id(ProjectPlugins.androidApplication) version ProjectPlugins.androidVersion apply false
    id(ProjectPlugins.androidLibrary) version ProjectPlugins.androidVersion apply false
    id(ProjectPlugins.kotlinAndroid) version ProjectPlugins.kotlinAndroidVersion apply false
    id(ProjectPlugins.mapsPlatformSecretGradle) version ProjectPlugins.mapsPlatformSecretGradleVersion apply false
    id(ProjectPlugins.googleGmsServices) version ProjectPlugins.googleGmsServicesVersion apply false
    id(ProjectPlugins.kspDevTools) version ProjectPlugins.ksp_version apply false
    id("com.gradle.plugin.create-file-plugin")
}

tasks.register("cleanAll", Delete::class) {
    doFirst {
        println("Start cleaning...")
    }

    description =
        "Task cleans all cache folder, run it if you want to clear all cache of this project"

    delete(File(".idea"))
    delete(File("build"))
    delete(File("build-cache"))
    delete(File("*/build"))

    dependsOn("${AppModule.projectName}:clean")
    dependsOn("${BaseAndroidModule.projectName}:clean")
    dependsOn("${CommonAndroidHelperModule.projectName}:clean")
    dependsOn("${CommonDataHelperModule.projectName}:clean")
    dependsOn("${CommonComposeModule.projectName}:clean")
    dependsOn("${CommonLogModule.projectName}:clean")
    dependsOn("${NetworkBaseModule.projectName}:clean")
    dependsOn("${NetworkImplModule.projectName}:clean")
    dependsOn("${NoteDataModule.projectName}:clean")
    dependsOn("${NoteFeatureModule.projectName}:clean")

    doLast {
        println("Clean all build and cache folders")
    }
}
