import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.compose") version "1.1.1"
    id("com.squareup.sqldelight")
}

group = "me.eyram"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}


dependencies {
    val koin_version= "3.1.6"
    implementation(compose.desktop.currentOs)
    implementation ("io.insert-koin:koin-core:$koin_version")
    implementation ("com.squareup.sqldelight:sqlite-driver:1.5.3")
    implementation ("com.squareup.sqldelight:coroutines-extensions-jvm:1.5.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Reportly"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    database("ReportlyDatabase") {
        packageName = "io.eyram.reportly.db"
        schemaOutputDirectory = file("build/dbs")
    }
}
