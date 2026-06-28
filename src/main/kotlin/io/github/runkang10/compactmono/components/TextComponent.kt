package io.github.runkang10.compactmono.components

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

fun textComponent(
    content: String,
    color: TextColor? = null,
    decoration: TextDecoration? = null,
    builder: TextComponent.Builder.() -> Unit = {}
) = Component.text()
    .content(content)
    .color(color)
    .also {
        if (decoration != null) it.decorate(decoration)
    }
    .apply(builder)
    .build()
