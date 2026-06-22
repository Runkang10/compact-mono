package io.github.runkang10.compactmono.services

import io.github.runkang10.compactmono.types.GenericService
import io.github.runkang10.compactmono.types.ReloadableService
import kotlinx.serialization.DeserializationStrategy
import net.mamoe.yamlkt.Yaml
import java.io.File

class YamlConfiguration<T : Any>(
    private val logger: ColoredLogger,
    private val file: File,
    private val default: T,
    private val serializer: DeserializationStrategy<T>
) : ReloadableService, GenericService {
    private val yaml = Yaml { encodeDefaultValues = true }
    private val serializedDefault = yaml.encodeToString(default)


    private var config: T? = default


    override fun load() {
        runCatching {
            logger.info("Loading '${file.name}'...")
            if (!file.exists()) save(serializedDefault)

            val content = file.readText()
            config = yaml.decodeFromString(serializer, content)
        }.onSuccess {
            logger.success("Successfully loaded '${file.name}'.")
        }.onFailure {
            logger.error("An error happened while reading '${file.name}': ", it)
        }
    }

    override fun unload() {
        runCatching {
            config = default
        }.onSuccess { logger.success("Successfully unloaded '${file.name}'.") }
    }

    override fun reload() {
        unload()
        load()
    }

    fun get() = config


    private fun save(content: String) = runCatching {
        logger.info("Saving '${file.path}'...")

        file.parentFile?.mkdirs()
        file.createNewFile()
        file.writeText(content)
    }.onSuccess {
        logger.success("Successfully saved '${file.name}'.")
    }.onFailure {
        logger.error("An error happened while saving '${file.name}': ", it)
    }
}