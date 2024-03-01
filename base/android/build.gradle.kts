import dependencies.basicAndroidComponent
import dependencies.test
import modules.base.BaseAndroidModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = BaseAndroidModule.nameSpace
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

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
    basicAndroidComponent()

    test()
}
