package io.github.runkang10.compactmono.utilities

import org.bukkit.Bukkit
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

object PermissionUtility {
    fun register(
        permission: String,
        default: PermissionDefault
    ): String {
        val pluginManager = Bukkit.getServer().pluginManager
        if (pluginManager.getPermission(permission) != null) return permission

        val permissionNode = Permission(permission, default)
        pluginManager.addPermission(permissionNode)
        return permission
    }

    fun unregister(permission: String) {
        val pluginManager = Bukkit.getServer().pluginManager
        pluginManager.removePermission(permission)
    }

    fun unregister(permission: Permission) {
        val pluginManager = Bukkit.getServer().pluginManager
        pluginManager.removePermission(permission)
    }
}