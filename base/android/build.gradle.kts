import dependencies.basicAndroidComponent
import dependencies.test
import modules.base.BaseAndroidModule
import modules.common.CommonAndroidHelperModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
    id(ModulePlugins.kotlinParcelize)
}

android {
    namespace = BaseAndroidModule.nameSpace
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = BaseAndroidModule.testInstrumentationRunner
        consumerProguardFiles(BaseAndroidModule.consumerProguardFiles)
    }

    buildTypes {
        release {
            isMinifyEnabled = BaseAndroidModule.Release.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile(BaseAndroidModule.Release.proguardAndroidOptimize),
                BaseAndroidModule.Release.proguardRules
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
    // import modules
    implementation(project(CommonAndroidHelperModule.PROJECT_NAME))

    basicAndroidComponent()

    test()
}
