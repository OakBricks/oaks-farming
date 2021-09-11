package org.oakbricks.farming

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.oakbricks.farming.init.ModBlocks
import org.oakbricks.farming.init.ModFeatures
import org.oakbricks.farming.init.ModItems
import kotlin.io.path.*

@Suppress("UNUSED")
object OakFarming: ModInitializer {
    const val MOD_ID = "oaks_farming"
    val ITEM_GROUP = FabricItemGroupBuilder.build(
        Identifier(MOD_ID, "general")
    ) { ItemStack(Blocks.COBBLESTONE) }
    private val LOGGER = LogManager.getLogger(MOD_ID)
    private val configPath = FabricLoader.getInstance().configDir
    val configFile = configPath.resolve("oaks_farming.json")
    val configuredJson = Json{prettyPrint = true}
    private val version: Int = 1

    @Serializable
    data class ConfigEntries(val jsonVersion: Int, val wildRiceSpawnChance: Int, val wildRiceSpawnAmount: Int)

    override fun onInitialize() {

        val data = ConfigEntries(version, 2, 15)
        val string = configuredJson.encodeToString(data)
        println(string)

        // bunch of code made for the custom config system!

        if (configFile.exists()) {
            LOGGER.info("config file exists! :)")
            if (configuredJson.decodeFromString<ConfigEntries>(configFile.readText()).jsonVersion != version) {
                LOGGER.warn("Config out of date, replacing config with a newer version!")
                configFile.writeText(string)
            }
            val outputStringFromFile = configuredJson.decodeFromString<ConfigEntries>(configFile.readText())
            LOGGER.warn(outputStringFromFile)
            LOGGER.warn(outputStringFromFile.wildRiceSpawnChance)
            LOGGER.warn(outputStringFromFile.wildRiceSpawnAmount)
            LOGGER.warn(outputStringFromFile.jsonVersion)
        } else {
            LOGGER.info("config file does not exist! :(")
            configFile.createFile()
            configFile.writeText(string)
            val outputStringFromFile = configuredJson.decodeFromString<ConfigEntries>(configFile.readText())

        }

        LOGGER.info("Oak's Farming has initialized!")
        if (FabricLoader.getInstance().isDevelopmentEnvironment == true) {
            LOGGER.info("running via IDE!")
        }
        ModBlocks.registerBlocks()
        ModItems.registerItems()
        ModFeatures.registerFeatures()




    }
}