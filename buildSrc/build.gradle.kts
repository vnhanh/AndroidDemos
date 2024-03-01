
plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("custom-plugin") {
            id = "com.gradle.plugin.custom-plugin"
            implementationClass = "plugins.CustomPlugin"
        }
    }
}
