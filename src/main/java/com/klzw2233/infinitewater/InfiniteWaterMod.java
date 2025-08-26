package com.klzw2233.infinitewater;

import com.klzw2233.InfiniteWaterMod.Tags;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = InfiniteWaterMod.MODID, version = Tags.VERSION, name = "Infinite Water Block", acceptedMinecraftVersions = "[1.7.10]")
public class InfiniteWaterMod {

    public static final String MODID = "infinitewater";
    public static final Logger LOG = LogManager.getLogger(MODID);

    public static Block infiniteWaterBlock;

    @SidedProxy(clientSide = "com.klzw2233.infinitewater.ClientProxy", serverSide = "com.klzw2233.infinitewater.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {

        proxy.init(event);

        //注册方块和方块实体
        infiniteWaterBlock = new BlockInfiniteWater();
        GameRegistry.registerBlock(infiniteWaterBlock, "infinite_water_block");
        GameRegistry.registerTileEntity(TileInfiniteWater.class, "tile_infinite_water");
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
