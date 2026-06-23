package io.github.runkang10.compactmono.utilities

fun strings(
    separator: CharSequence = "\n",
    vararg s: String,
) = s.joinToString(separator)

fun strings(
    separator: CharSequence = "\n",
    s: Collection<String>
) = strings(separator, *s.toTypedArray())

fun Iterable<String>.strings(separator: CharSequence = "\n") = joinToString(separator)