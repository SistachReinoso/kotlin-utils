plugins {
    application
    kotlin("jvm") version "1.9.23"
}

group = "org.linux.command"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.click)
    testImplementation(libs.bundles.kotest)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}

application {
    mainClass = "org.linux.command.org.linux.command.MainKt"
}
