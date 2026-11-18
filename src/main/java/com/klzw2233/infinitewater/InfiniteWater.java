package com.klzw2233.infinitewater;

import com.klzw2233.infinitewater.init.ModBlockEntities;
import com.klzw2233.infinitewater.init.ModBlocks;
import com.klzw2233.infinitewater.init.ModCreativeModeTabs;
import com.klzw2233.infinitewater.init.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(InfiniteWater.MODID)
public class InfiniteWater {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "infinitewater";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public InfiniteWater() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // region ModEventBus
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);


        // end region

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}
}
