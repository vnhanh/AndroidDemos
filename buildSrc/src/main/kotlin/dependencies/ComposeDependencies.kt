package dependencies

object ComposeConfiguration {
    const val kotlinCompilerExtensionVersion = "1.5.1"
}

object ComposeDependencies {
    private const val bomVersion = "2023.10.01"
    private const val activityComposeVersion = "1.8.1"
    private const val lifecycleComposeVersion = "2.6.2"
    private const val material3Version = "1.2.0-alpha11"

    const val composeBom = "androidx.compose:compose-bom:$bomVersion"
    const val material = "androidx.compose.material:material"
    const val material3 = "androidx.compose.material3:material3:$material3Version"
    const val material3WindowSizeClass = "androidx.compose.material3:material3-window-size-class:$material3Version"
    const val ui = "androidx.compose.ui:ui"
    const val foundation = "androidx.compose.foundation:foundation"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout"
    const val runtime = "androidx.compose.runtime:runtime"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val debugUiTooling = "androidx.compose.ui:ui-tooling"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
    const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleComposeVersion"
    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:$lifecycleComposeVersion"
}

object GlideDependencies {
    private const val composeVersion = "1.0.0-alpha.3"

    const val glideCompose = "com.github.bumptech.glide:compose:$composeVersion"
}

object MarkdownDependencies {
    private const val composeVersion = "0.3.6"

    const val composeMarkdown = "com.github.jeziellago:compose-markdown:$composeVersion"
}
