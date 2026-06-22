package io.github.runkang10.compactmono.components

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextColor

fun textComponent(
    content: String,
    color: TextColor? = null,
    builder: TextComponent.Builder.() -> Unit = {}
) = Component.text().content(content).color(color).apply(builder).build()

fun textComponent(
    value: Int,
    color: TextColor? = null,
    builder: TextComponent.Builder.() -> Unit = {}
) = Component.text(value, color).toBuilder().apply(builder).build()

fun textComponent(
    value: Long,
    color: TextColor? = null,
    builder: TextComponent.Builder.() -> Unit = {}
) = Component.text(value, color).toBuilder().apply(builder).build()

fun textComponent(
    value: Float,
    color: TextColor? = null,
    builder: TextComponent.Builder.() -> Unit = {}
) = Component.text(value, color).toBuilder().apply(builder).build()

fun textComponent(
    value: Double,
    color: TextColor? = null,
    builder: TextComponent.Builder.() -> Unit = {}
) = Component.text(value, color).toBuilder().apply(builder).build()

fun textComponent(
    value: Char,
    color: TextColor? = null,
    builder: TextComponent.Builder.() -> Unit = {}
) = Component.text(value, color).toBuilder().apply(builder).build()

fun textComponent(
    value: Boolean,
    color: TextColor? = null,
    builder: TextComponent.Builder.() -> Unit = {}
) = Component.text(value, color).toBuilder().apply(builder).build()
