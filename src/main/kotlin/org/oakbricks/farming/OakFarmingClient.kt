package org.oakbricks.farming

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import org.oakbricks.farming.init.ModBlocks

object OakFarmingClient: ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.RICE_CROP)
    }
}