package com.klzw2233.infinitewater.registry;

import com.klzw2233.infinitewater.block.BlockInfiniteWater;
import com.klzw2233.infinitewater.core.ModConstants;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {

    // 在这里声明所有方块
    public static Block infiniteWater;

    public static void register() {
        infiniteWater = new BlockInfiniteWater();
        GameRegistry.registerBlock(infiniteWater, ModConstants.prefix(BlockInfiniteWater.name));
    }
}
