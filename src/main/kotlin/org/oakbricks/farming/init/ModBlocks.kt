package org.oakbricks.farming.init

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.CropBlock
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.oakbricks.farming.OakFarming
import org.oakbricks.farming.blocks.CustomCropBlock
import org.oakbricks.farming.OakFarming.MOD_ID

object ModBlocks {
    var TEST_BLOCK: Block = Block(
        FabricBlockSettings.of(Material.WOOD).luminance(5)
    )
    val RICE_CROP: CropBlock = CustomCropBlock(
        AbstractBlock.Settings.of(Material.PLANT).nonOpaque().noCollision().ticksRandomly().breakInstantly()
            .sounds(BlockSoundGroup.CROP)
    )
    val WILD_RICE_CROP: Block = Block(
        FabricBlockSettings.of(Material.PLANT).nonOpaque().noCollision().breakInstantly()
            .sounds(BlockSoundGroup.CROP)
    )
    fun registerBlocks() {
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "test_block"), TEST_BLOCK)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "test_block"), BlockItem(
            TEST_BLOCK, Item.Settings().group(
                OakFarming.ITEM_GROUP
            )))
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "rice_crop"), RICE_CROP)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "wild_rice_crop"), WILD_RICE_CROP)
    }
}