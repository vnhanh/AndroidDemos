import dependencies.basicAndroidComponent
import dependencies.test
import modules.data.NoteDataModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = NoteDataModule.NAME_SPACE
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = NoteDataModule.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles(NoteDataModule.CONSUME_PROGUARD_FILES)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.IS_MINIFY_ENABLED
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.IS_MINIFY_ENABLED

            proguardFiles(
                getDefaultProguardFile(NoteDataModule.Release.PROGUARD_ANDROID_OPTIMIZE),
                NoteDataModule.Release.PROGUARD_RULES
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
