import modules.common.CommonDataHelperModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = CommonDataHelperModule.nameSpace
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = CommonDataHelperModule.testInstrumentationRunner
        consumerProguardFiles(CommonDataHelperModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.IS_MINIFY_ENABLED
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.IS_MINIFY_ENABLED

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
