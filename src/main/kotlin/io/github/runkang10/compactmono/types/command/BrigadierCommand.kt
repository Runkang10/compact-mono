package io.github.runkang10.compactmono.types.command

import io.github.runkang10.compactmono.commands.Literal

interface BrigadierCommand {
    fun meta(): BrigadierCommandMeta
    fun execute(): Literal
}