import dependencies.ComposeConfiguration
import dependencies.basicAndroidComponent
import dependencies.koin
import dependencies.room
import dependencies.test
import modules.base.BaseAndroidModule
import modules.common.CommonAndroidHelperModule
import modules.common.CommonComposeModule
import modules.common.CommonLogModule
import modules.data.UserDataModule
import modules.features.AuthenticationFeatureModule
import modules.network.NetworkBaseModule
import modules.network.NetworkImplModule

plugins {
    id(ModulePlugins.androidLibrary)
    id(ModulePlugins.kotlinAndroid)
    id(ModulePlugins.ksp)
}

android {
    namespace = AuthenticationFeatureModule.nameSpace
    compileSdk = AndroidConfiguration.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfiguration.MIN_SDK

        testInstrumentationRunner = AuthenticationFeatureModule.testInstrumentationRunner
        consumerProguardFiles(AuthenticationFeatureModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.IS_MINIFY_ENABLED
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.IS_MINIFY_ENABLED

            proguardFiles(
                getDefaultProguardFile(AuthenticationFeatureModule.Release.proguardAndroidOptimize),
                AuthenticationFeatureModule.Release.proguardRules
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

    composeOptions {
        kotlinCompilerExtensionVersion = ComposeConfiguration.kotlinCompilerExtensionVersion
    }

    buildFeatures {
        compose = true
        buildConfig = AuthenticationFeatureModule.buildConfig
    }
}

dependencies {
    // import modules
    implementation(project(BaseAndroidModule.PROJECT_NAME))
    implementation(project(UserDataModule.PROJECT_NAME))
    implementation(project(NetworkBaseModule.PROJECT_NAME))
    implementation(project(NetworkImplModule.PROJECT_NAME))
    implementation(project(CommonAndroidHelperModule.PROJECT_NAME))
    implementation(project(CommonComposeModule.PROJECT_NAME))
    implementation(project(CommonLogModule.PROJECT_NAME))

    basicAndroidComponent()

    // di
    koin()

    // database
    room()

    test()
}
