package io.github.runkang10.compactmono.services

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class ServiceRegistry(threadSafe: Boolean = false) {
    private val registry: MutableMap<KClass<*>, Any> = if (threadSafe) ConcurrentHashMap() else mutableMapOf()


    inline fun <reified T : Any> add(instance: T) = add(T::class, instance)
    fun <T : Any> add(
        kClass: KClass<T>,
        instance: T
    ) {
        val previous = registry.putIfAbsent(kClass, instance)
        if (previous != null) throw IllegalStateException("$instance has been registered already")
    }


    fun <T : Any> getOrNull(kClass: KClass<T>) = registry[kClass] as? T
    inline fun <reified T : Any> getOrNull() = getOrNull(T::class)

    fun <T : Any> get(kClass: KClass<T>) = getOrNull(kClass) ?: error("No instance registered for $kClass")
    inline fun <reified T : Any> get() = get(T::class)

    fun getAll() = registry.toMap()


    fun <T : Any> remove(kClass: KClass<T>) = registry.remove(kClass)
    inline fun <reified T : Any> remove() = remove(T::class)

    fun clear() = registry.clear()
}