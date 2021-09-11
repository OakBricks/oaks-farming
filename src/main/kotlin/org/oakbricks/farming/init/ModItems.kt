package org.oakbricks.farming.init

import net.minecraft.item.AliasedBlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.oakbricks.farming.OakFarming.MOD_ID


object ModItems {
    val CUSTOM_SEEDS: Item = AliasedBlockItem(ModBlocks.RICE_CROP, Item.Settings().group(ItemGroup.MISC))
    fun registerItems() {
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "custom_seeds"), CUSTOM_SEEDS)
    }
}