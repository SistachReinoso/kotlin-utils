package org.linux.command.update

enum class UpdateState(val state: String) {
    UP_TO_DATE("All packages are up to date."),
}

fun update() {
    val command = listOf("sudo", "apt", "update")

    val proc = ProcessBuilder(command)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    launch {

    }
    var count = 0
    val a = proc.inputStream.bufferedReader().lineSequence()
    val b = proc.errorStream.bufferedReader().lineSequence()
    for (line in b)
        println("E: ${++count}\tline: $line")
    for (line in a) {
        println("M: ${++count}\tline: $line")
    }
}
