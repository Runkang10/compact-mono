package io.github.runkang10.compactmono.types.commands

import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack

interface BrigadierCommand {
    fun meta(): BrigadierCommandMeta
    fun execute(): LiteralCommandNode<CommandSourceStack>
}