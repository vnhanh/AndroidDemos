import org.gradle.api.JavaVersion

object KotlinConfiguration  {
    const val jvmTarget = "19"

    val sourceCompatibility = JavaVersion.VERSION_19
    val targetCompatibility = JavaVersion.VERSION_19

    object SourceSets {
        val srcDirs = listOf("build/generated/ksp/main/kotlin")
    }
}
