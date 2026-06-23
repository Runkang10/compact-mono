package io.github.runkang10.compactmono.types.minecraft

data class MinecraftCommandMeta(
    val description: String,
    val aliases: List<String> = emptyList()
)
