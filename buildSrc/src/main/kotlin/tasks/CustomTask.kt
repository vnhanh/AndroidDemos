package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class CustomTask : DefaultTask() {
    @get: Input
    abstract val fileText: Property<String>

    @Input
    val fileName = "myFile.txt"

    @OutputFile
    val myFile: File = File(fileName)

    @TaskAction
    fun action() {
        myFile.createNewFile()
        myFile.writeText(fileText.get())
    }
}

abstract class MyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("createFileTask", CustomTask::class.java) {
            group = "my plugin"
            description = "Create myFile.txt in the current directory"
            fileText.set("Hello from my plugin")
        }
    }
}
