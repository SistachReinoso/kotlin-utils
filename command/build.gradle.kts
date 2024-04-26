plugins {
    application
    kotlin("jvm")
}

dependencies {
    implementation(libs.click)
}

application {
    mainClass = "org.linux.command.org.linux.command.MainKt"
}
