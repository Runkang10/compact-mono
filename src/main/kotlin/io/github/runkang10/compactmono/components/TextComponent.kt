package io.github.runkang10.compactmono.components

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration


class AppendableTextComponent {
    var component = Component.empty()

    operator fun TextComponent.unaryPlus() {
        component = append(this)
    }
}

fun textComponents(builder: AppendableTextComponent.() -> Unit) = AppendableTextComponent().apply(builder).component

fun textComponents(vararg components: TextComponent) = Component.textOfChildren(*components)


fun textComponent(builder: TextComponent.() -> Unit) = Component.empty().apply(builder)

fun textComponent(
    content: String,
    color: TextColor? = null,
    decoration: TextDecoration? = null,
    builder: TextComponent.() -> Unit = {}
) = Component.text(content, color)
    .content(content)
    .color(color)
    .also {
        if (decoration != null) it.decorate(decoration)
    }.apply(builder)


fun TextComponent.hoverComponent(
    content: String,
    color: TextColor? = null,
    decoration: TextDecoration? = null,
    builder: TextComponent.() -> Unit = {}
) = hoverEvent(textComponent(content, color, decoration).apply(builder))


operator fun TextComponent.plus(component: TextComponent) = append(component)