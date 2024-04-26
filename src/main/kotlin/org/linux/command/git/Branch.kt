package org.linux.command.org.linux.command.git

import java.util.concurrent.TimeUnit

data class Branch(val current: String?, val locals: Set<String>, val remotes: Map<String, Set<String>>) {
    companion object {
        private val command = listOf("git", "branch", "-a")
        // TODO fer que les commandes siguin externes!
        fun done() : Branch {
            val proc = ProcessBuilder(command)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()
            proc.waitFor(10, TimeUnit.SECONDS)
            val exitStatus = proc.exitValue()
            if (exitStatus != 0)
                throw Error("Ups todo + logs")
            return parser(proc.inputStream.bufferedReader().lineSequence())
        }
        fun parser(input: CharSequence): Branch = parser(input.lineSequence())
        private fun parser(input: Sequence<String>): Branch {
            val (remoteLines, localLines) = input
                .map(String::trim)
                .filter { line -> "HEAD -> " !in line }
                .partition { line -> line.startsWith("remotes/") }

            val (currentBranch, localBranches) = parseLocalLines(localLines)
            val remoteBranches = parseRemoteLines(remoteLines)

            return Branch(current = currentBranch, locals = localBranches.toSet(), remotes = remoteBranches)
        }

        private fun parseLocalLines(localLines: Collection<String>): Pair<String?, List<String>> {
            var current: String? = null
            val output = localLines.mapNotNull { line ->
                if (line.startsWith("* ")) {
                    current = line.drop(2)
                    current
                } else line
            }
            return Pair(current, output)
        }

        private fun parseRemoteLines(remotes: List<String>): Map<String, Set<String>> =
            remotes.map { line -> line.split("/") }
                .groupBy(keySelector = { it[1] }, valueTransform = { it.drop(2).joinToString("/") })
                .mapValues { it.value.toSet() }
    }
}
