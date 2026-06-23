package io.github.runkang10.compactmono.services.schedulers

import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin

class EntityScheduler(
    private val plugin: JavaPlugin
) {
    fun run(
        entity: Entity,
        scheduledTask: (ScheduledTask) -> Unit,
        retired: (() -> Unit)? = {}
    ): ScheduledTask? {
        val scheduler = entity.scheduler
        return scheduler.run(plugin, scheduledTask, retired)
    }

    fun runDelayed(
        entity: Entity,
        delay: Long,
        scheduledTask: (ScheduledTask) -> Unit,
        retired: (() -> Unit)? = {}
    ): ScheduledTask? {
        val scheduler = entity.scheduler
        return scheduler.runDelayed(plugin, scheduledTask, retired, delay)
    }

    fun runAtFixedRate(
        entity: Entity,
        initialDelay: Long,
        delay: Long,
        scheduledTask: (ScheduledTask) -> Unit,
        retired: (() -> Unit)? = {}
    ): ScheduledTask? {
        val scheduler = entity.scheduler
        return scheduler.runAtFixedRate(plugin, scheduledTask, retired, initialDelay, delay)
    }
}