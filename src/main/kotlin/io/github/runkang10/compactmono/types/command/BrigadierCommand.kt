package io.github.runkang10.compactmono.types.minecraft

import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack

interface MinecraftCommand {
    fun meta(): MinecraftCommandMeta
    fun execute(): LiteralCommandNode<CommandSourceStack>
}