package io.github.runkang10.compactmono.services

import kotlin.reflect.KClass

class ServiceRegistry(val id: String) {
    companion object {
        private val registries = mutableMapOf<String, ServiceRegistry>()


        fun contains(id: String) = registries.contains(id)

        fun get(id: String) = registries.getValue(id)

        fun remove(id: String) = registries.remove(id)
    }


    private val registry = mutableMapOf<KClass<*>, Any>()


    init {
        registries[id] = this
    }

    inline fun <reified T : Any> add(instance: T) = add(T::class, instance)
    fun <T : Any> add(
        kClass: KClass<T>,
        instance: T
    ) {
        registry[kClass] = instance
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(kClass: KClass<T>) = registry.getValue(kClass) as T
    inline fun <reified T : Any> get() = get(T::class)

    fun <T : Any> contains(kClass: KClass<T>) = registry.contains(kClass)
    inline fun <reified T : Any> contains() = contains(T::class)

    fun <T : Any> remove(kClass: KClass<T>) = registry.remove(kClass)
    inline fun <reified T : Any> remove() = remove(T::class)
}