package org.linux.command.org.linux.command.git

data class Branch(val current: String?, val locals: Set<String>, val remotes: Map<String, Set<String>>) {
    companion object {
        fun parser(input: CharSequence): Branch {
            val (remoteLines, localLines) = input
                .lineSequence()
                .map(String::trim)
                .filter { line -> "HEAD -> " !in line }
                .partition { line -> line.startsWith("remotes/") }

            val (currentBranch, localBranches) = parseLocalLines(localLines)
            val remoteBranches = parseRemoteLines(remoteLines)

            return Branch(current = currentBranch, locals = localBranches.toSet(), remotes = remoteBranches)
        }

        private fun parseLocalLines(localLines: List<String>): Pair<String?, List<String>> {
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
