package org.linux.command

import com.github.ajalt.clikt.completion.completionOption
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.PrintMessage
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.eagerOption
import com.github.ajalt.clikt.parameters.options.versionOption
import org.linux.command.git.GitCommand
import org.linux.command.update.UpdateCommand

class MyCommand : CliktCommand(name = "myCommand") {
    init {
        completionOption()
        versionOption("0.0.0")
        subcommands(GitCommand())
        subcommands(UpdateCommand())

        eagerOption("--myVersion") {
            // TODO mirar si podem mirar les versions de les llibreries que hi depen
            throw PrintMessage("$commandName version 1.0")
        }
    }

    override fun run() {
        // TODO interessant val name by option(envvar = "MY_NAME")
    }
}

fun main(args: Array<String>) = MyCommand().main(args)
