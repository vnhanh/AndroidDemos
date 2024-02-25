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
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = CommonComposeModule.testInstrumentationRunner
        consumerProguardFiles(CommonComposeModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile(CommonComposeModule.Release.proguardAndroidOptimize),
                CommonComposeModule.Release.proguardRules
            )
        }
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
    api(ComposeDependencies.activityCompose)
    api(ComposeDependencies.lifeCycleViewModel)
    api(ComposeDependencies.lifeCycleRuntime)
    api(ComposeDependencies.material)
    api(ComposeDependencies.material3)
    api(ComposeDependencies.material3WindowSizeClass)
    debugApi(ComposeDependencies.debugUiTooling)
    api(ComposeDependencies.uiToolingPreview)
    api(ComposeDependencies.composeNavigation)

    test()
}
