import dependencies.LogDependencies
import dependencies.basicAndroidComponent
import dependencies.test
import modules.common.CommonLogModule

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
}

dependencies {
    api(LogDependencies.timber)

    test()
}
