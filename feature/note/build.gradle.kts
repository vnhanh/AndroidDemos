import dependencies.basicAndroidComponent
import dependencies.test
import modules.common.CommonComposeModule
import modules.common.CommonLogModule
import modules.features.NoteFeatureModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = NoteFeatureModule.nameSpace
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = NoteFeatureModule.testInstrumentationRunner
        consumerProguardFiles(NoteFeatureModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.IS_MINIFY_ENABLED
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.IS_MINIFY_ENABLED

            proguardFiles(
                getDefaultProguardFile(NoteFeatureModule.Release.proguardAndroidOptimize),
                NoteFeatureModule.Release.proguardRules
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
    implementation(project(CommonComposeModule.PROJECT_NAME))
    implementation(project(CommonLogModule.PROJECT_NAME))

    basicAndroidComponent()

    test()
}
