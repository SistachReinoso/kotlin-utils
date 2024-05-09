package org.linux.command.update

import kotlinx.coroutines.*

enum class UpdateState(val state: String) {
    UP_TO_DATE("All packages are up to date"),
    CAN_BE_UPGRADED("""apt list --upgradable"""),
    UNKNOWN("Unknown stat")
}

fun update() = runBlocking {
    apt()
}
