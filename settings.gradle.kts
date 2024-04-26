plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "command"

include(":command")
project(":command").name = "myCommand"
include(":lib")
project(":lib").name = "command-lib"
