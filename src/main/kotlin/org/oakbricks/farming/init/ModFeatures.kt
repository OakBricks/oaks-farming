package org.oakbricks.farming.init

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.impl.networking.NetworkingImpl.MOD_ID
import net.minecraft.block.Blocks
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.ConfiguredFeatures
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig
import net.minecraft.world.gen.placer.SimpleBlockPlacer
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider


object ModFeatures {
    var WILD_RICE_PATCH: ConfiguredFeature<*, *> = Feature.RANDOM_PATCH
        .configure(
            RandomPatchFeatureConfig.Builder(
                SimpleBlockStateProvider(ModBlocks.WILD_RICE_CROP.getDefaultState()),
                SimpleBlockPlacer()
            )
                .tries(5) // how many times the feature attempts to spawn in a chunk
                .cannotProject()
                .whitelist(
                    setOf(
                        Blocks.GRASS_BLOCK,
                        Blocks.DIRT
                    )
                ) // block that are allowed to spawn the feature ontop of
                .build()
        )
        .decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE)
        .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
        .repeat(5) // how many times this feature is going to be repeated

    fun registerFeatures() {
        val patchWildRice = RegistryKey.of(
            Registry.CONFIGURED_FEATURE_KEY,
            Identifier(MOD_ID, "patch_wild_rice")
        )
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, patchWildRice.value, WILD_RICE_PATCH)
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.TOP_LAYER_MODIFICATION,
            patchWildRice
        )
    }
}