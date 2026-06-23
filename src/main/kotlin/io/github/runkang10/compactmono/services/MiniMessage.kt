package io.github.runkang10.compactmono.services

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.Tag
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver


val miniMessage by lazy {
    MiniMessage.builder()
        .editTags { t ->
            t
                .resolver(colorOf("primary", "#2596be"))
                .resolver(colorOf("secondary", "#99AAB5"))
                .resolver(colorOf("success", "#00ffa6"))
                .resolver(colorOf("danger", "#fc3a3a"))
                .resolver(colorOf("warning", "#fff700"))
        }
        .build()
}


private fun colorOf(
    name: String,
    color: String
) = TagResolver.resolver(name, Tag.styling(TextColor.fromHexString(color) ?: NamedTextColor.WHITE))