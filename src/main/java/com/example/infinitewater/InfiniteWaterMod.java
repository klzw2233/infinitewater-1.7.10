package com.example.infinitewater;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

@Mod(modid = InfiniteWaterMod.MODID, name = "Infinite Water Block", version = "1.0")
public class InfiniteWaterMod {
    public static final String MODID = "infinitewater";
    public static Block infiniteWaterBlock;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        infiniteWaterBlock = new BlockInfiniteWater();
        GameRegistry.registerBlock(infiniteWaterBlock, "infinite_water_block");
        GameRegistry.registerTileEntity(TileInfiniteWater.class, "tile_infinite_water");
    }
}
