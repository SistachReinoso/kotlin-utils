package org.linux.command.org.linux.command.git

data class Branch(val current: String?, val localBranches: List<String>, val remotes: Map<String, List<String>>) {
    companion object {
        fun of(input: String): Branch {
            val (remotes, local) = input
                .lines()
                .map(String::trim)
                .partition { line -> line.startsWith("remotes/") }

            val (current, localBranches) = currentAndLocalBranches(local)
            val remotesBranches = remotesBranches(remotes)

            return Branch(current = current, localBranches = localBranches, remotes = remotesBranches)
        }

        private fun currentAndLocalBranches(local: List<String>): Pair<String?, List<String>> {
            var current: String? = null
            val output = local.mapNotNull { line ->
                if (line.startsWith("* ")) {
                    current = line.drop(2); return@mapNotNull current
                }

                line
            }
            return Pair(current, output)
        }

        private fun remotesBranches(remotes: List<String>): Map<String, List<String>> =
            remotes.map { line -> line.split("/") }
                .groupBy(keySelector = { it[1] }, valueTransform = { it.drop(2).joinToString() })
    }
}
