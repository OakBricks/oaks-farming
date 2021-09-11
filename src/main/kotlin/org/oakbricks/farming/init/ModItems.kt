package org.oakbricks.farming.init

import net.minecraft.item.AliasedBlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.oakbricks.farming.OakFarming
import org.oakbricks.farming.OakFarming.MOD_ID


object ModItems {
    val RICE: Item = AliasedBlockItem(ModBlocks.RICE_CROP, Item.Settings().group(OakFarming.ITEM_GROUP))
    val BLUEBERRIES: Item = AliasedBlockItem(ModBlocks.BLUEBERRY_BUSH, Item.Settings().group(OakFarming.ITEM_GROUP))
    fun registerItems() {
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "custom_seeds"), RICE)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "blueberries"), BLUEBERRIES)
    }
}