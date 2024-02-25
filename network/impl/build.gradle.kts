import dependencies.basicAndroidComponent
import dependencies.test
import modules.network.NetworkImplModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = NetworkImplModule.nameSpace
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = NetworkImplModule.testInstrumentationRunner
        consumerProguardFiles(NetworkImplModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile(NetworkImplModule.Release.proguardAndroidOptimize),
                NetworkImplModule.Release.proguardRules
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
    basicAndroidComponent()

    test()
}
