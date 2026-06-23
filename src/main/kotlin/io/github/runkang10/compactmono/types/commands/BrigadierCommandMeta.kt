package io.github.runkang10.compactmono.types.command

data class BrigadierCommandMeta(
    val description: String,
    val aliases: List<String> = emptyList()
)
