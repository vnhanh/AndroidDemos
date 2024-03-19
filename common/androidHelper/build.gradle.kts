import dependencies.DataStoreDependencies
import dependencies.test
import modules.common.CommonAndroidHelperModule
import modules.log.LogModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = CommonAndroidHelperModule.NAME_SPACE
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = CommonAndroidHelperModule.testInstrumentationRunner
        consumerProguardFiles(CommonAndroidHelperModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.IS_MINIFY_ENABLED
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.IS_MINIFY_ENABLED
            proguardFiles(
                getDefaultProguardFile(CommonAndroidHelperModule.Release.proguardAndroidOptimize),
                CommonAndroidHelperModule.Release.proguardRules
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
        buildConfig = CommonAndroidHelperModule.BUILD_CONFIG
    }
}

dependencies {
    implementation(project(LogModule.PROJECT_NAME))

    implementation(DataStoreDependencies.DATA_STORE)
    implementation(DataStoreDependencies.PROTO_DATA_STORE)

    test()
}
