package com.klzw2233.infinitewater.registry;

import com.klzw2233.infinitewater.block.BlockInfiniteFluid;
import com.klzw2233.infinitewater.block.BlockInfiniteWater;
import com.klzw2233.infinitewater.core.ModConstants;
import com.klzw2233.infinitewater.item.ItemBlockInfiniteFluid;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {

    // 在这里声明所有方块
    public static Block infiniteWater; // 无限水方块

    public static Block infiniteFluid; // 无限流体方块

    public static void register() {
        infiniteWater = new BlockInfiniteWater();
        GameRegistry.registerBlock(infiniteWater, ModConstants.prefix(BlockInfiniteWater.name));

        //注册时绑定了方块物品类
        infiniteFluid = new BlockInfiniteFluid();
        GameRegistry.registerBlock(infiniteFluid, ItemBlockInfiniteFluid.class, ModConstants.prefix(BlockInfiniteFluid.name));
    }
}
