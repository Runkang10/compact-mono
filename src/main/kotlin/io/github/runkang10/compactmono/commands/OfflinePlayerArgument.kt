package io.github.runkang10.compactmono.commands

import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import io.github.runkang10.compactmono.components.textComponent
import io.github.runkang10.compactmono.services.OfflinePlayersCache
import io.papermc.paper.command.brigadier.MessageComponentSerializer
import io.papermc.paper.command.brigadier.argument.CustomArgumentType
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.util.concurrent.CompletableFuture

class OfflinePlayerArgument(
    private val loader: OfflinePlayersCache,
    errorMessage: String
) : CustomArgumentType.Converted<OfflinePlayer, String> {
    private val errorUnknownPlayer = DynamicCommandExceptionType { name ->
        MessageComponentSerializer.message().serialize(
            textComponent(errorMessage.replace("<name>", name.toString()))
        )
    }


    override fun convert(nativeType: String): OfflinePlayer {
        val uuid = loader.uuidByName(nativeType) ?: throw errorUnknownPlayer.create(nativeType)
        return Bukkit.getOfflinePlayer(uuid)
    }

    override fun getNativeType(): StringArgumentType = StringArgumentType.word()

    override fun <S : Any> listSuggestions(
        context: CommandContext<S>,
        builder: SuggestionsBuilder
    ): CompletableFuture<Suggestions> {
        loader.names()
            .filter { it.startsWith(builder.remainingLowerCase, true) }
            .forEach(builder::suggest)
        return builder.buildFuture()
    }
}