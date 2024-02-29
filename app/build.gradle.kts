import dependencies.AndroidXDependencies
import dependencies.ComposeConfiguration
import dependencies.GoogleDependencies
import dependencies.KotlinDependencies
import dependencies.basicAndroidComponent
import dependencies.implementation
import dependencies.koin
import dependencies.test
import modules.AppModule
import modules.common.CommonAndroidHelperModule
import modules.common.CommonComposeModule
import modules.common.CommonDataHelperModule
import modules.common.CommonLogModule
import modules.network.NetworkImplModule

plugins {
    id(ModulePlugins.androidApplication)
    id(ModulePlugins.kotlinAndroid)
}

android {
    namespace = AppModule.nameSpace
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        applicationId = AppModule.applicationId
        minSdk = AndroidConfiguration.minSdk

        targetSdk = AndroidConfiguration.targetSdk
        versionCode = AppModule.versionCode
        versionName = AppModule.versionName

        testInstrumentationRunner = AppModule.testInstrumentationRunner
    }

    buildTypes {
        debug {
            isDebuggable = BuildTypeConfiguration.Debug.isDebuggable
            isMinifyEnabled = BuildTypeConfiguration.Debug.isMinifyEnabled
        }

        release {
            isDebuggable = BuildTypeConfiguration.Release.isDebuggable
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled
            isShrinkResources = BuildTypeConfiguration.Release.isShrinkResources
            proguardFiles(
                getDefaultProguardFile(AppModule.Release.proguardAndroidOptimize),
                AppModule.Release.proguardRules
            )
        }
    }

    buildFeatures {
        compose = true
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
        viewBinding = AppModule.viewBinding
    }
}

dependencies {
    /**
     * Implement modules
     */
    implementation(project(NetworkImplModule.projectName))
    implementation(project(CommonAndroidHelperModule.projectName))
    implementation(project(CommonComposeModule.projectName))
    implementation(project(CommonDataHelperModule.projectName))
    implementation(project(CommonLogModule.projectName))

    /**
     * Android dependencies
     */
    basicAndroidComponent()

    implementation(AndroidXDependencies.activityKtx)
    implementation(AndroidXDependencies.fragmentKtx)
    implementation(AndroidXDependencies.navigationFragmentKtx)
    implementation(AndroidXDependencies.navigationUiKtx)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(GoogleDependencies.material)

    // Kotlin
    implementation(KotlinDependencies.stdLib)

    koin()

    test()
}
