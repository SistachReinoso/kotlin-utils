plugins {
    kotlin("jvm") version "1.9.23" apply false
}

group = "org.linux.command"
version = "0.2.0-SNAPSHOT"

subprojects {
    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
