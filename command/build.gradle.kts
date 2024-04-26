plugins {
    application
    kotlin("jvm")
}

dependencies {
    implementation(project(":command-lib"))
    implementation(libs.click)
}

application {
    mainClass = "org.linux.command.MainKt"
}
