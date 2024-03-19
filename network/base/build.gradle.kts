import dependencies.GoogleDependencies
import dependencies.test
import modules.network.NetworkBaseModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = NetworkBaseModule.nameSpace
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = NetworkBaseModule.testInstrumentationRunner
        consumerProguardFiles(NetworkBaseModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.IS_MINIFY_ENABLED
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.IS_MINIFY_ENABLED

            proguardFiles(
                getDefaultProguardFile(BuildTypeConfiguration.Release.proguardAndroidOptimize),
                BuildTypeConfiguration.Release.proguardRules
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
}

dependencies {
    implementation(GoogleDependencies.gson)

    test()
}