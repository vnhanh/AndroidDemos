import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import dependencies.AndroidXDependencies
import dependencies.ComposeConfiguration
import dependencies.GoogleDependencies
import dependencies.KotlinDependencies
import dependencies.basicAndroidComponent
import dependencies.implementation
import dependencies.koin
import dependencies.test
import flavors.BuildConfigField
import flavors.FireBaseConfigField
import modules.app.AppModule
import modules.common.CommonAndroidHelperModule
import modules.common.CommonComposeModule
import modules.common.CommonDataHelperModule
import modules.common.CommonLogModule
import modules.features.AuthenticationFeatureModule
import modules.network.NetworkImplModule

plugins {
    id(ModulePlugins.androidApplication)
    id(ModulePlugins.kotlinAndroid)
    id(ModulePlugins.ksp)
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
            manifestPlaceholders[FireBaseConfigField.crashlyticsCollectionEnabled] =
                BuildTypeConfiguration.Debug.crashlytics

        }

        release {
            isDebuggable = BuildTypeConfiguration.Release.isDebuggable
            isMinifyEnabled = BuildTypeConfiguration.Release.isMinifyEnabled
            isShrinkResources = BuildTypeConfiguration.Release.isShrinkResources
            manifestPlaceholders[FireBaseConfigField.crashlyticsCollectionEnabled] =
                BuildTypeConfiguration.Release.crashlytics
            proguardFiles(
                getDefaultProguardFile(AppModule.Release.proguardAndroidOptimize),
                AppModule.Release.proguardRules
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = AppModule.buildConfig
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
        buildConfig = AppModule.buildConfig
    }

    applicationVariants.all {
        val outputFileName = BuildTypeConfiguration.getFileBuildName(
            buildTypeName = this.buildType.name,
            flavor = this.productFlavors[0].name,
            versionName = this.versionName,
        )

        outputs.all {
            val output = this as? BaseVariantOutputImpl
            output?.outputFileName = outputFileName
        }
    }

    flavorDimensions += AppModule.Flavors.dimension

    productFlavors {
        create(AppModule.Flavors.Dev.name) {
            applicationId = AppModule.Flavors.Dev.applicationId
            versionCode = AppModule.Flavors.Dev.versionCode
            versionName = AppModule.Flavors.Dev.versionName
            dimension = AppModule.Flavors.dimension

            buildConfigField(
                BuildConfigField.typeString,
                BuildConfigField.fieldNameBaseUrl,
                AppModule.Flavors.Dev.fieldValueBaseUrl
            )
            buildConfigField(
                BuildConfigField.typeString,
                BuildConfigField.fieldNameApiVersion,
                AppModule.Flavors.Dev.fieldValueApiVersion
            )
        }

        create(AppModule.Flavors.QC.name) {
            applicationId = AppModule.Flavors.QC.applicationId
            versionCode = AppModule.Flavors.QC.versionCode
            versionName = AppModule.Flavors.QC.versionName
            dimension = AppModule.Flavors.dimension

            buildConfigField(
                BuildConfigField.typeString,
                BuildConfigField.fieldNameBaseUrl,
                AppModule.Flavors.QC.fieldValueBaseUrl
            )
            buildConfigField(
                BuildConfigField.typeString,
                BuildConfigField.fieldNameApiVersion,
                AppModule.Flavors.QC.fieldValueApiVersion
            )
        }

        create(AppModule.Flavors.Staging.name) {
            applicationId = AppModule.Flavors.Staging.applicationId
            versionCode = AppModule.Flavors.Staging.versionCode
            versionName = AppModule.Flavors.Staging.versionName
            dimension = AppModule.Flavors.dimension

            buildConfigField(
                BuildConfigField.typeString,
                BuildConfigField.fieldNameBaseUrl,
                AppModule.Flavors.Staging.fieldValueBaseUrl
            )
            buildConfigField(
                BuildConfigField.typeString,
                BuildConfigField.fieldNameApiVersion,
                AppModule.Flavors.Staging.fieldValueApiVersion
            )
        }

        create(AppModule.Flavors.Production.name) {
            applicationId = AppModule.Flavors.Production.applicationId
            versionCode = AppModule.Flavors.Production.versionCode
            versionName = AppModule.Flavors.Production.versionName
            dimension = AppModule.Flavors.dimension

            buildConfigField(
                BuildConfigField.typeString,
                BuildConfigField.fieldNameBaseUrl,
                AppModule.Flavors.Production.fieldValueBaseUrl
            )
            buildConfigField(
                BuildConfigField.typeString,
                BuildConfigField.fieldNameApiVersion,
                AppModule.Flavors.Production.fieldValueApiVersion
            )
        }
    }
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir(KotlinConfiguration.SourceSets.srcDirs)
        }
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
    implementation(project(AuthenticationFeatureModule.projectName))

    /**
     * Android dependencies
     */
    basicAndroidComponent()

    implementation(AndroidXDependencies.activityKtx)
    implementation(AndroidXDependencies.fragmentKtx)
    implementation(AndroidXDependencies.navigationFragmentKtx)
    implementation(AndroidXDependencies.navigationUiKtx)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.dataStorePreferences)
    implementation(AndroidXDependencies.dataStoreProto)
    implementation(GoogleDependencies.material)

    // Kotlin
    implementation(KotlinDependencies.stdLib)

    koin()

    test()
}
