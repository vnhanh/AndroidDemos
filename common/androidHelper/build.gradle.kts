import dependencies.DataStoreDependencies
import dependencies.test
import modules.common.CommonAndroidHelperModule
import modules.log.LogModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = CommonAndroidHelperModule.nameSpace
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = CommonAndroidHelperModule.testInstrumentationRunner
        consumerProguardFiles(CommonAndroidHelperModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled
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
}

dependencies {
    implementation(project(LogModule.PROJECT_NAME))

    implementation(DataStoreDependencies.DATA_STORE)
    implementation(DataStoreDependencies.PROTO_DATA_STORE)

    test()
}
