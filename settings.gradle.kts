pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}
/**
 * Enable local build cache: https://www.baeldung.com/gradle-build-cache
 */
buildCache {
    local {
        directory = File(rootDir, "build-cache")
        removeUnusedEntriesAfterDays = 30
    }
}

rootProject.name = ("AndroidDemo")

include (
    ":app",
    ":network:base"
)
include(":common:log")
include(":common:dataHelper")
include(":common:compose")
include(":common:androidHelper")
include(":network:impl")
include(":data:note")
include(":feature:note")
