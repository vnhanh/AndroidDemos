plugins {
    id(ProjectPlugins.androidApplication) version ProjectPlugins.androidVersion apply false
    id(ProjectPlugins.androidLibrary) version ProjectPlugins.androidVersion apply false
    id(ProjectPlugins.kotlinAndroid) version ProjectPlugins.kotlinAndroidVersion apply false
    id(ProjectPlugins.mapsPlatformSecretGradle) version ProjectPlugins.mapsPlatformSecretGradleVersion apply false
    id(ProjectPlugins.googleGmsServices) version ProjectPlugins.googleGmsServicesVersion apply false
    id(ProjectPlugins.kspDevTools) version ProjectPlugins.ksp_version apply false
    id(ProjectPlugins.customPluginIdCreateFile)
}
