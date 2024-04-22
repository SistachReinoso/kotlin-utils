package org.linux.command.org.linux.command

import com.github.ajalt.clikt.core.CliktCommand
import org.linux.command.org.linux.command.git.Branch

class Command: CliktCommand() {
    override fun run() {
        echo(Branch.done())
    }
}

fun main(args: Array<String>) = Command().main(args)
