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
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = NoteFeatureModule.testInstrumentationRunner
        consumerProguardFiles(NoteFeatureModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled

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
    implementation(project(CommonComposeModule.projectName))
    implementation(project(CommonLogModule.projectName))

    basicAndroidComponent()

    test()
}
