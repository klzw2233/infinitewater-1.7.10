package com.klzw2233.infinitewater.registry;

import com.klzw2233.infinitewater.core.ModConstants;
import com.klzw2233.infinitewater.tileentity.TileInfiniteWater;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void register() {

        GameRegistry.registerTileEntity(TileInfiniteWater.class, ModConstants.underLine(TileInfiniteWater.name));
    }
}
