package org.linux.command.update

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.options.required

class UpdateCommand : CliktCommand() {
    private val password by option().prompt(hideInput = true)
    private val name by option(envvar = "USERNAME").required()
    override fun run() {
        update()
    }
}
