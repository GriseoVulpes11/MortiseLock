import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.4.20"
    id("org.jetbrains.compose") version "0.2.0-build132"

}

group = "me.cooperbrown"
version = "1.10"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev")

    }
}

dependencies {
    implementation(compose.desktop.currentOs)
    compile ("com.github.dhiraj072:random-word-generator:1.1.0")
    implementation(kotlin("script-runtime"))

}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "MortiseLock"
        }
    }
}