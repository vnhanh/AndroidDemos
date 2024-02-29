gradlePlugin {
    plugins {
        create("create-my-file") {
            id = "com.gradle.plugin.create-file-plugin"
            implementationClass = "tasks.MyPlugin"
        }
    }
}

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}
