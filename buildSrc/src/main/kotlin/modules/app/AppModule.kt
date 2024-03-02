package modules.app

object AppModule {
    const val nameSpace = "com.alanvo.androiddemo"
    const val applicationId = "com.alanvo.androiddemo"
    const val versionCode = 1
    const val versionName = "0.0.1"

    const val projectName = "app"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    object Release {
        const val proguardAndroidOptimize = "proguard-android-optimize.txt"
        const val proguardRules = "proguard-rules.pro"
    }

    const val viewBinding = true
    const val buildConfig = true


    object Flavors {
        const val dimension = "app"

        object Dev {
            const val name = "dev"
            const val applicationId = "com.alanvo.androiddemo.dev"


            const val versionCode = 1
            const val versionName = "0.0.1"

            private const val hostUrl = "https://localhost/dev/api/"
            private const val apiVersion = "v1"
            const val fieldValueBaseUrl = "\"$hostUrl$apiVersion\""
            const val fieldValueApiVersion = "\"v1/\""

            // app link
            const val resValueAppLinkScheme = "https"
            const val resValueAppLinkHost = "test.androiddemo.vnhanh.com"
        }

        object QC {
            const val name = "qc"
            const val applicationId = "com.alanvo.androiddemo.qc"


            const val versionCode = 1
            const val versionName = "0.0.1"

            private const val hostUrl = "https://localhost/qc/api/"
            private const val apiVersion = "v1"
            const val fieldValueBaseUrl = "\"$hostUrl$apiVersion\""
            const val fieldValueApiVersion = "\"v1/\""

            // app link
            const val resValueAppLinkScheme = "https"
            const val resValueAppLinkHost = "test.androiddemo.vnhanh.com"
        }

        object Staging {
            const val name = "staging"
            const val applicationId = "com.alanvo.androiddemo.staging"


            const val versionCode = 1
            const val versionName = "0.0.1"

            private const val hostUrl = "https://localhost/staging/api/"
            private const val apiVersion = "v1"
            const val fieldValueBaseUrl = "\"$hostUrl$apiVersion\""
            const val fieldValueApiVersion = "\"v1/\""

            // app link
            const val resValueAppLinkScheme = "https"
            const val resValueAppLinkHost = "staging.androiddemo.vnhanh.com"
        }

        object Production {
            const val name = "production"
            const val applicationId = "com.alanvo.androiddemo"


            const val versionCode = 1
            const val versionName = "0.0.1"

            private const val hostUrl = "https://localhost/api/"
            private const val apiVersion = "v1"
            const val fieldValueBaseUrl = "\"$hostUrl$apiVersion\""
            const val fieldValueApiVersion = "\"v1/\""

            // app link
            const val resValueAppLinkScheme = "https"
            const val resValueAppLinkHost = "androiddemo.vnhanh.com"
        }
    }

}
