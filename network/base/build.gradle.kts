import dependencies.test
import modules.network.NetworkBaseModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = NetworkBaseModule.nameSpace
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = NetworkBaseModule.testInstrumentationRunner
        consumerProguardFiles(NetworkBaseModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled

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

    test()
}