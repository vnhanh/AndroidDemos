import modules.common.CommonDataHelperModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = CommonDataHelperModule.nameSpace
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = CommonDataHelperModule.testInstrumentationRunner
        consumerProguardFiles(CommonDataHelperModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile(CommonDataHelperModule.Release.proguardAndroidOptimize),
                CommonDataHelperModule.Release.proguardRules
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
