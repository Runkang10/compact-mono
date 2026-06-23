package io.github.runkang10.compactmono.services.schedulers

import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.plugin.java.JavaPlugin

class GlobalRegionScheduler(
    private val plugin: JavaPlugin
) {
    private val scheduler = plugin.server.globalRegionScheduler


    fun execute(runnable: () -> Unit) = scheduler.execute(plugin) { runnable() }

    fun run(scheduledTask: (ScheduledTask) -> Unit) = scheduler.run(plugin, scheduledTask)

    fun runDelayed(
        delay: Long,
        scheduledTask: (ScheduledTask) -> Unit
    ) = scheduler.runDelayed(plugin, scheduledTask, delay)

    fun runAtFixedRate(
        initialDelay: Long,
        delay: Long,
        scheduledTask: (ScheduledTask) -> Unit
    ) = scheduler.runAtFixedRate(plugin, scheduledTask, initialDelay, delay)

    fun cancelTasks() = scheduler.cancelTasks(plugin)
}