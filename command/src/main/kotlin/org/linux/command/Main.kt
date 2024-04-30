package org.linux.command

import com.github.ajalt.clikt.completion.completionOption
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import org.linux.command.git.GitCommand
import org.linux.command.update.UpdateCommand

class MyCommand : CliktCommand(name = "myCommand") {
    init {
        completionOption()
        subcommands(GitCommand())
        subcommands(UpdateCommand())
    }

    override fun run() {
    }
}

fun main(args: Array<String>) = MyCommand().main(args)
