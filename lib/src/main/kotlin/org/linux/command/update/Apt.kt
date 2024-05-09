package org.linux.command.update

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun apt() = runBlocking {
    when (aptUpdate()) {
        UpdateState.UP_TO_DATE -> {}
        UpdateState.CAN_BE_UPGRADED -> aptUpgrade()
        UpdateState.UNKNOWN -> throw Exception("Unknown stat: ups")
    }
}

private fun aptUpgrade() = runBlocking {
    val command = listOf("sudo", "apt", "upgrade")

    val proc = ProcessBuilder(command)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    var count = 0
    val input = proc.outputStream
    launch {
        proc.inputStream
            .bufferedReader()
            .lineSequence()
            .map { line -> println("M: ${++count}\tline: $line"); line.trim() }
            .filter { it.isNotBlank() }
            .map { line ->
                if ("Do you want to continue? [Y/n]" in line) {
                    println("write y + entenr")
                    input.write("y\n".toByteArray())
                    input.flush()
                }
            }
    }

    launch {
        proc.errorStream
            .bufferedReader()
            .lineSequence()
            .filter { it.isNotBlank() }
            .forEach { line -> println("E: ${++count}\tline: $line") }
    }

    proc.waitFor()
    input.close()
    if (proc.exitValue() != 0) {
        println("Exit status: ${proc.exitValue()}")
        TODO("Error encara no fet")
    }
}

private suspend fun aptUpdate(): UpdateState = runBlocking {
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
        .firstOrNull { it.state in result2 }
        ?: UpdateState.UNKNOWN
    println("update State=$a")

    proc.waitFor()
    if (proc.exitValue() != 0) {
        println("Exit status: ${proc.exitValue()}")
        TODO("Error encara no fet")
    }
    return@runBlocking a
}
