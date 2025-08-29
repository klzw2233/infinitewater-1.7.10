package com.klzw2233.infinitewater;

import com.klzw2233.infinitewater.config.ModConfig;
import com.klzw2233.infinitewater.core.ModConstants;
import com.klzw2233.infinitewater.proxy.CommonProxy;
import com.klzw2233.infinitewater.registry.ModBlocks;
import com.klzw2233.infinitewater.registry.ModItems;
import com.klzw2233.infinitewater.registry.ModRecipes;
import com.klzw2233.infinitewater.registry.ModTileEntities;
import com.klzw2233.infinitewater.util.ModLogger;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;

/**
 * 无限水源模组主类
 */
@Mod(
    modid = ModConstants.MODID,
    name = ModConstants.NAME,
    version = ModConstants.VERSION,
    acceptedMinecraftVersions = "1.7.10"
)
public class InfiniteWaterMod {

    @Mod.Instance(ModConstants.MODID)
    public static InfiniteWaterMod INSTANCE;

    @SidedProxy(
        clientSide = ModConstants.CLIENT_PROXY,
        serverSide = ModConstants.COMMON_PROXY
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModLogger.info("=== PreInit stage begin === "+ModConstants.NAME+" "+ModConstants.VERSION);

        // 加载配置
        ModConfig.load(event.getSuggestedConfigurationFile());

        // 注册内容
        ModBlocks.register();
        ModItems.register();
        ModTileEntities.register();
        ModRecipes.register();

        // 调用代理
        proxy.preInit(event);

        ModLogger.info("=== PreInit stage finish ===");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModLogger.info("=== Init stage begin ===");
        proxy.init(event);
        ModLogger.info("=== Init stage finish ===");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModLogger.info("=== PostInit stage begin ===");
        proxy.postInit(event);
        ModLogger.info("=== PostInit stage finish ===");
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        ModLogger.info("=== Server startup phase ===");
        proxy.serverStarting(event);
    }
}
