import org.gradle.api.JavaVersion

object KotlinConfiguration  {
    const val kotlinVersion = "1.9.2"

    const val jvmTarget = "19"

    val sourceCompatibility = JavaVersion.VERSION_19
    val targetCompatibility = JavaVersion.VERSION_19
}
