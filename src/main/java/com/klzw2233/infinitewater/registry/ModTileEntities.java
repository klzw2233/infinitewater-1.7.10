package com.klzw2233.infinitewater.registry;

import com.klzw2233.infinitewater.core.ModConstants;
import com.klzw2233.infinitewater.tileentity.TileInfiniteFluid;
import com.klzw2233.infinitewater.tileentity.TileInfiniteWater;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void register() {

        // 无限水方块对应的方块实体
        GameRegistry.registerTileEntity(TileInfiniteWater.class, ModConstants.underLine(TileInfiniteWater.name));

        GameRegistry.registerTileEntity(TileInfiniteFluid.class, ModConstants.underLine(TileInfiniteFluid.name));
    }
}
