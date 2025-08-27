package com.klzw2233.infinitewater;

import com.klzw2233.InfiniteWaterMod.Tags;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;


/**
 * 这是一个通用的代理类，用于处理服务器端和客户端共享的事件。
 * 通过在方法上添加 @Mod.EventHandler 注解，可以直接在此类中处理模组事件。
 */
public class CommonProxy {

    // 声明无限水源方块的静态实例
    public static Block infiniteWaterBlock;

    /**
     * 模组预初始化阶段的事件处理器。
     * 在此阶段，通常进行配置读取、方块和物品的创建。
     * @param event 预初始化事件对象。
     */
    //@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        // 注：原始代码中的 Config 和 Tags 变量未提供，此处保留了原始调用结构。
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        InfiniteWaterMod.LOG.info(Config.greeting);
        InfiniteWaterMod.LOG.info("loading mod "+ InfiniteWaterMod.MODName + "-" + Tags.VERSION);

    }

    /**
     * 模组初始化阶段的事件处理器。
     * 在此阶段，进行模组的实际设置，如注册方块、方块实体和合成配方。
     * @param event 初始化事件对象。
     */
    //@Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        // 创建并注册无限水源方块
        infiniteWaterBlock = new BlockInfiniteWater();
        GameRegistry.registerBlock(infiniteWaterBlock, InfiniteWaterMod.BLOCK_NAME);

        // 注册 TileEntity
        GameRegistry.registerTileEntity(TileInfiniteWater.class, InfiniteWaterMod.TILE_NAME);

        // 在这里注册配方
        Recipes.registerRecipes();

    }

    /**
     * 模组后初始化阶段的事件处理器。
     * 在此阶段，主要处理与其他模组的交互。
     * @param event 后初始化事件对象。
     */
    //@Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    /**
     * 服务器启动事件处理器。
     * 在此阶段，可以注册服务器命令。
     * @param event 服务器启动事件对象。
     */
    //@Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {

    }
}
