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
    ":base:android",
    ":network:base",
    ":network:impl",
    ":common:dataHelper",
    ":common:androidHelper",
    ":common:compose",
    ":common:log",
    ":data:note",
    ":feature:note",
    ":data:user",
    ":feature:authentication",
)
include(":data:book")
