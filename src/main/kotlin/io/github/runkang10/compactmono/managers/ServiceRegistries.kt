package io.github.runkang10.compactmono.managers

import io.github.runkang10.compactmono.services.ServiceRegistry

object ServiceRegistries {
    private val registries = mutableMapOf<String, ServiceRegistry>()


    fun register(registry: ServiceRegistry) {
        registries[registry.id] = registry
    }

    fun get(id: String) = registries.getValue(id)

    fun contains(id: String) = registries.contains(id)

    fun unregister(id: String) = registries.remove(id)
}