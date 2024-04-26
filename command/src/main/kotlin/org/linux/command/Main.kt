package org.linux.command

import com.github.ajalt.clikt.core.CliktCommand
import org.linux.command.org.linux.command.git.Branch

class MyCommand: CliktCommand() {
    override fun run() {
        echo(Branch.done())
    }
}

fun main(args: Array<String>) = MyCommand().main(args)
