import dependencies.basicAndroidComponent
import dependencies.test
import modules.common.CommonComposeModule
import modules.common.CommonLogModule
import modules.features.LoginFeatureModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = LoginFeatureModule.nameSpace
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = LoginFeatureModule.testInstrumentationRunner
        consumerProguardFiles(LoginFeatureModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile(LoginFeatureModule.Release.proguardAndroidOptimize),
                LoginFeatureModule.Release.proguardRules
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
    implementation(project(CommonComposeModule.projectName))
    implementation(project(CommonLogModule.projectName))

    basicAndroidComponent()

    test()
}
