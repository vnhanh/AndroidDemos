package plugins

import modules.app.AppModule
import modules.base.BaseAndroidModule
import modules.common.CommonAndroidHelperModule
import modules.common.CommonComposeModule
import modules.common.CommonDataHelperModule
import modules.common.CommonLogModule
import modules.network.NetworkBaseModule
import modules.network.NetworkImplModule
import modules.note.NoteDataModule
import modules.note.NoteFeatureModule
import org.gradle.api.Plugin
import org.gradle.api.Project


abstract class CustomPlugin : Plugin<Project> {
    private val taskCreateFile = "createFileTask"
    private val cleanAll = "cleanAll"

    override fun apply(target: Project) {
        target.tasks.register(taskCreateFile, CreateFileTask::class.java) {
            group = "my plugin"
            description = "Custom Plugin includes custom tasks"
            fileText.set("Hello from my plugin")
        }

        target.tasks.register(cleanAll, CleanAllTask::class.java) {
            group = "my plugin"
            description = "Clean build and cache folders"

            doFirst {
                println("Cleaning projectDir/build, build-cache and build folder of modules...")
            }

            // configuration phase
            dependsOn("${AppModule.projectName}:clean")
            dependsOn("${BaseAndroidModule.projectName}:clean")
            dependsOn("${CommonAndroidHelperModule.projectName}:clean")
            dependsOn("${CommonDataHelperModule.projectName}:clean")
            dependsOn("${CommonComposeModule.projectName}:clean")
            dependsOn("${CommonLogModule.projectName}:clean")
            dependsOn("${NetworkBaseModule.projectName}:clean")
            dependsOn("${NetworkImplModule.projectName}:clean")
            dependsOn("${NoteDataModule.projectName}:clean")
            dependsOn("${NoteFeatureModule.projectName}:clean")

            doLast {
                println("Cleaned")
            }
        }
    }
}
