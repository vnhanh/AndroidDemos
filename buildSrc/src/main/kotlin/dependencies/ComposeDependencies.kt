package dependencies

object ComposeConfiguration {
    const val kotlinCompilerExtensionVersion = "1.5.10"
}

object ComposeDependencies {
    private const val bomVersion = "2023.10.01"
    private const val ACTIVITY_COMPOSE_VERSION = "1.8.1"
    private const val LIFECYCLE_VERSION = "2.6.2"
    private const val material3Version = "1.2.0-alpha11"
    private const val navigationVersion = "2.7.4"

    const val composeBom = "androidx.compose:compose-bom:$bomVersion"
    const val material = "androidx.compose.material:material"
    const val material3 = "androidx.compose.material3:material3:$material3Version"
    const val material3WindowSizeClass =
        "androidx.compose.material3:material3-window-size-class:$material3Version"
    const val ui = "androidx.compose.ui:ui"
    const val foundation = "androidx.compose.foundation:foundation"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout"
    const val runtime = "androidx.compose.runtime:runtime"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val debugUiTooling = "androidx.compose.ui:ui-tooling"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:$ACTIVITY_COMPOSE_VERSION"
    const val LIFECYCLE_VIEW_MODEL =
        "androidx.lifecycle:lifecycle-viewmodel-compose:$LIFECYCLE_VERSION"
    const val LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-compose:$LIFECYCLE_VERSION"
    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val ANIMATION_VERSION = "1.6.3"
    const val ANIMATION = "androidx.compose.animation:animation:$ANIMATION_VERSION"

    private const val ANIMATION_GRAPHICS_ANDROID_VERSION = "1.6.3"
    const val ANIMATION_GRAPHICS_ANDROID =
        "androidx.compose.animation:animation-graphics-android:$ANIMATION_GRAPHICS_ANDROID_VERSION"
}
