plugins {
    application
    kotlin("jvm")
}

dependencies {
    implementation(libs.coroutine)
    testImplementation(libs.bundles.kotest)
}

application {
    mainClass = "org.linux.command.org.linux.command.MainKt"
}
