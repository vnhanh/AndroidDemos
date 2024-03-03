import dependencies.ComposeConfiguration
import dependencies.basicAndroidComponent
import dependencies.koin
import dependencies.room
import dependencies.test
import modules.base.BaseAndroidModule
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
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        minSdk = AndroidConfiguration.minSdk

        testInstrumentationRunner = AuthenticationFeatureModule.testInstrumentationRunner
        consumerProguardFiles(AuthenticationFeatureModule.consumerProguardFiles)
    }

    buildTypes {
        debug {
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled

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
    implementation(project(BaseAndroidModule.projectName))
    implementation(project(UserDataModule.projectName))
    implementation(project(NetworkBaseModule.projectName))
    implementation(project(NetworkImplModule.projectName))
    implementation(project(CommonComposeModule.projectName))
    implementation(project(CommonLogModule.projectName))

    basicAndroidComponent()

    // di
    koin()

    // database
    room()

    test()
}
