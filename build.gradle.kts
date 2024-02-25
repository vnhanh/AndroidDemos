
plugins {
    id(ProjectPlugins.androidApplication) version ProjectPlugins.androidVersion apply false
    id(ProjectPlugins.androidLibrary) version ProjectPlugins.androidVersion apply false
    id(ProjectPlugins.kotlinAndroid) version ProjectPlugins.kotlinAndroidVersion apply false
    id(ProjectPlugins.mapsPlatformSecretGradle) version ProjectPlugins.mapsPlatformSecretGradleVersion apply false
    id(ProjectPlugins.googleGmsServices) version ProjectPlugins.googleGmsServicesVersion apply false
}