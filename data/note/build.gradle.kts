import dependencies.basicAndroidComponent
import dependencies.test
import modules.note.NoteDataModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = NoteDataModule.nameSpace
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = NoteDataModule.testInstrumentationRunner
        consumerProguardFiles(NoteDataModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile(NoteDataModule.Release.proguardAndroidOptimize),
                NoteDataModule.Release.proguardRules
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
