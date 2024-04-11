package org.linux.command.org.linux.command.git

data class Branch(val current: String?, val locals: List<String>, val remotes: Map<String, List<String>>) {
    companion object {
        fun parser(input: String): Branch {
            val (remoteLines, localLines) = input
                .lines()
                .map(String::trim)
                .filter { line -> "HEAD -> " !in line }
                .partition { line -> line.startsWith("remotes/") }

            val (currentBranch, localBranches) = parseLocalLines(localLines)
            val remoteBranches = parseRemoteLines(remoteLines)

            return Branch(current = currentBranch, locals = localBranches, remotes = remoteBranches)
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

        private fun parseRemoteLines(remotes: List<String>): Map<String, List<String>> =
            remotes.map { line -> line.split("/") }
                .groupBy(keySelector = { it[1] }, valueTransform = { it.drop(2).joinToString("/") })
    }
}
