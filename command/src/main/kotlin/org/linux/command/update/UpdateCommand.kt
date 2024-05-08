package org.linux.command.update

import com.github.ajalt.clikt.core.CliktCommand

class UpdateCommand : CliktCommand() {
    override fun run() {
        update()
    }
}
