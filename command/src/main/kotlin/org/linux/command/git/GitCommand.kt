package org.linux.command.git

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import org.linux.command.org.linux.command.git.Branch

class GitCommand: CliktCommand() {
    init {
        subcommands(BranchCommand())
    }

    override fun run() {
    }
}

class BranchCommand: CliktCommand() {
    override fun run() {
        echo(Branch.done())
    }
}
