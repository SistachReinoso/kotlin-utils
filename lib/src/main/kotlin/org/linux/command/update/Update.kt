package org.linux.command.update

import kotlinx.coroutines.*

enum class UpdateState(val state: Regex) {
    UP_TO_DATE("All packages are up to date.".toRegex()),
    CAN_BE_UPGRADED("""\d+ package can be upgraded. Run 'apt list --upgradable' to see it.""".toRegex())
}

fun update() = runBlocking {
    extracted()
}

private suspend fun extracted() = coroutineScope {
    val command = listOf("sudo", "apt", "update")

    val proc = ProcessBuilder(command)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    var count = 0
    launch {
        proc.errorStream
            .bufferedReader()
            .lineSequence()
            .filter { it.isNotBlank() }
            .forEach { line -> println("E: ${++count}\tline: $line") }
    }

    val result = async {
        proc.inputStream
            .bufferedReader()
            .lineSequence()
            .map { line -> println("M: ${++count}\tline: $line"); line.trim() }
            .filter { it.isNotBlank() }
            .last()
    }

    val result2 = result.await()
    println("result: $result2")
    val a = UpdateState.entries
        .first { it.state.matches(result2) }
    println("update State=$a")

    proc.waitFor()
    println("Exit status: ${proc.exitValue()}")
}
