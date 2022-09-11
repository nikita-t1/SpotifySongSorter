import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev774"
}

group = "com.songsorter"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)

    // Ktor Client
    implementation("io.ktor:ktor-server-core:2.1.0")
    // engine that processes network requests
    implementation("io.ktor:ktor-server-netty:2.1.0")

    // A Kotlin implementation of the Spotify Web API
    implementation("com.adamratzman:spotify-api-kotlin-core:3.8.7")

    // HTTP networking library
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")

    // Kotlin Multiplatform lifecycle-aware business logic components
    implementation("com.arkivanov.decompose:decompose:1.0.0-alpha-04")

    // Dependency Injection
    implementation("io.insert-koin:koin-core:3.2.0")
    // Injection library for Compose (Multiplatform and Jetpack), Koin wrapper
    implementation("dev.burnoo:cokoin:0.3.4")

    // Compose Multi Icon Pack
    implementation("br.com.devsrsouza.compose.icons.jetbrains:css-gg:1.0.0")
    implementation("br.com.devsrsouza.compose.icons.jetbrains:feather:1.0.0")

    // Colorful styling for command-line applications
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta7")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "SpotifySongSorter"
            packageVersion = "1.0.0"
        }
    }
}