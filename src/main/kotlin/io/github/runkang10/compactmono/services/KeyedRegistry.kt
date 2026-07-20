package io.github.runkang10.compactmono.services

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class KeyedRegistry(threadSafe: Boolean = false) {
    data class Keyed<T : Any>(
        val type: KClass<T>,
        val qualifier: Any = Unit
    )


    private val registry: MutableMap<Keyed<*>, Any> = if (threadSafe) ConcurrentHashMap() else mutableMapOf()


    fun <T : Any> add(
        kClass: KClass<T>,
        instance: T,
        qualifier: Any = Unit
    ) {
        val previous = registry.putIfAbsent(Keyed(kClass, qualifier), instance)
        if (previous != null) throw IllegalStateException("$instance is already added!")
    }

    inline fun <reified T : Any> add(
        instance: T,
        qualifier: Any = Unit
    ) = add(T::class, instance, qualifier)


    fun <T : Any> getOrNull(
        kClass: KClass<T>,
        qualifier: Any = Unit
    ) = registry[Keyed(kClass, qualifier)] as? T

    inline fun <reified T : Any> getOrNull(qualifier: Any = Unit) = getOrNull(T::class, qualifier)


    fun <T : Any> get(
        kClass: KClass<T>,
        qualifier: Any = Unit
    ) = getOrNull(kClass, qualifier) ?: error("No instance registered for $kClass (qualifier=$qualifier)")

    inline fun <reified T : Any> get(qualifier: Any = Unit) = get(T::class, qualifier)

    fun getAll() = registry.toMap()


    fun <T : Any> remove(
        kClass: KClass<T>,
        qualifier: Any = Unit
    ) = registry.remove(Keyed(kClass, qualifier))

    inline fun <reified T : Any> remove(qualifier: Any = Unit) = remove(T::class, qualifier)

    fun clear() = registry.clear()
}