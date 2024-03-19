import dependencies.ComposeConfiguration
import dependencies.ComposeDependencies
import dependencies.test
import modules.common.CommonComposeModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = CommonComposeModule.nameSpace
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = CommonComposeModule.testInstrumentationRunner
        consumerProguardFiles(CommonComposeModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.IS_MINIFY_ENABLED
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.IS_MINIFY_ENABLED

            proguardFiles(
                getDefaultProguardFile(CommonComposeModule.Release.proguardAndroidOptimize),
                CommonComposeModule.Release.proguardRules
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = KotlinConfiguration.sourceCompatibility
        targetCompatibility = KotlinConfiguration.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = KotlinConfiguration.jvmTarget
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ComposeConfiguration.kotlinCompilerExtensionVersion
    }
}

dependencies {
    api(platform(ComposeDependencies.composeBom))
    api(ComposeDependencies.runtime)
    api(ComposeDependencies.ui)
    api(ComposeDependencies.foundation)
    api(ComposeDependencies.foundationLayout)
    api(ComposeDependencies.runtimeLiveData)
    api(ComposeDependencies.ACTIVITY_COMPOSE)
    api(ComposeDependencies.LIFECYCLE_VIEW_MODEL)
    api(ComposeDependencies.LIFECYCLE_RUNTIME)
    api(ComposeDependencies.material)
    api(ComposeDependencies.material3)
    api(ComposeDependencies.material3WindowSizeClass)
    debugApi(ComposeDependencies.debugUiTooling)
    api(ComposeDependencies.uiToolingPreview)
    api(ComposeDependencies.COMPOSE_NAVIGATION)
    api(ComposeDependencies.ANIMATION)
    api(ComposeDependencies.ANIMATION_GRAPHICS_ANDROID)

    test()
}
