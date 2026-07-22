package io.github.runkang10.compactmono.services

import io.github.runkang10.compactmono.services.schedulers.AsyncScheduler
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class OfflinePlayersCache(
    private val plugin: JavaPlugin,
    private val asyncScheduler: AsyncScheduler
) : GenericService, Listener {
    private val nameToUuidCache = ConcurrentHashMap<String, UUID>()
    private val uuidToNameCache = ConcurrentHashMap<UUID, String>()


    override fun load() {
        asyncScheduler.runNow {
            Bukkit.getOfflinePlayers().forEach { offlinePlayer ->
                val name = offlinePlayer.name!!
                val uuid = offlinePlayer.uniqueId
                nameToUuidCache[name.lowercase()] = uuid
                uuidToNameCache[uuid] = name
            }
        }

        val pluginManager = plugin.server.pluginManager
        pluginManager.registerEvents(this, plugin)
    }

    override fun unload() {
        nameToUuidCache.clear()
        uuidToNameCache.clear()

        HandlerList.unregisterAll(this)
    }

    fun update(
        uuid: UUID,
        name: String
    ) {
        val previousName = uuidToNameCache.put(uuid, name)
        if (previousName != null && !previousName.equals(name, ignoreCase = true))
            nameToUuidCache.remove(previousName.lowercase())
        nameToUuidCache[name.lowercase()] = uuid
    }

    fun names() = uuidToNameCache.values

    fun uuids() = uuidToNameCache.keys

    fun uuidByName(name: String) = nameToUuidCache[name.lowercase()]

    fun nameByUuid(uuid: UUID) = uuidToNameCache[uuid]


    @EventHandler(priority = EventPriority.MONITOR)
    private fun onAsyncPlayerPreJoin(event: AsyncPlayerPreLoginEvent) {
        if (event.loginResult == AsyncPlayerPreLoginEvent.Result.ALLOWED)
            update(event.uniqueId, event.name)
    }
}