import dependencies.AndroidXDependencies
import dependencies.GoogleDependencies
import dependencies.RetrofitDependencies
import dependencies.koin
import dependencies.test
import modules.common.CommonDataHelperModule
import modules.common.CommonLogModule
import modules.network.NetworkBaseModule
import modules.network.NetworkImplModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
    id(ModulePlugins.ksp)
}

android {
    namespace = NetworkImplModule.nameSpace
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = NetworkImplModule.testInstrumentationRunner
        consumerProguardFiles(NetworkImplModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.IS_MINIFY_ENABLED
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.IS_MINIFY_ENABLED

            proguardFiles(
                getDefaultProguardFile(NetworkImplModule.Release.proguardAndroidOptimize),
                NetworkImplModule.Release.proguardRules
            )
        }
    }

    buildFeatures {
        buildConfig = NetworkImplModule.buildConfig
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
    implementation(project(NetworkBaseModule.PROJECT_NAME))
    implementation(project(CommonDataHelperModule.projectName))
    implementation(project(CommonLogModule.PROJECT_NAME))

    api(RetrofitDependencies.retrofit)
    api(RetrofitDependencies.gsonConverter)
    api(RetrofitDependencies.loggingInterceptor)
    implementation(GoogleDependencies.gson)
    implementation(AndroidXDependencies.annotationJvm)

    // di
    koin()

    test()
}
