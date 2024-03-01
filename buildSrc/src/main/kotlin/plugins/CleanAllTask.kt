package plugins

import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class CleanAllTask : Delete() {

    @TaskAction
    fun action() {
        description =
            "Clean all cache folders, run it if you want to clear all cache of this project"

        delete(File("build"))
        delete(File("build-cache"))
        delete(File("buildSrc/build"))
        delete(File("buildSrc/.gradle"))
    }
}
