import dependencies.LogDependencies
import dependencies.test
import modules.common.CommonLogModule
import modules.log.LogModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = CommonLogModule.nameSpace
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = CommonLogModule.testInstrumentationRunner
        consumerProguardFiles(CommonLogModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile(CommonLogModule.Release.proguardAndroidOptimize),
                CommonLogModule.Release.proguardRules
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

    buildFeatures {
        buildConfig = LogModule.BUILD_CONFIG
    }
}

dependencies {
    api(LogDependencies.timber)

    test()
}
