package org.linux.command.update

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt

class UpdateCommand: CliktCommand() {
    private val password by option().prompt(hideInput = true)
    override fun run() {
        echo("your password is $password")
        echo("ups, that normally is a secret")
    }
}
