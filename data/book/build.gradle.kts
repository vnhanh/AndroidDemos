import dependencies.test
import modules.data.BookDataModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
    id(ModulePlugins.ksp)
}

android {
    namespace = BookDataModule.NAME_SPACE
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = BookDataModule.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles(BookDataModule.CONSUME_PROGUARD_FILES)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.IS_MINIFY_ENABLED
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.IS_MINIFY_ENABLED

            proguardFiles(
                getDefaultProguardFile(BookDataModule.Release.PROGUARD_ANDROID_OPTIMIZE),
                BookDataModule.Release.PROGUARD_RULES
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
    test()
}