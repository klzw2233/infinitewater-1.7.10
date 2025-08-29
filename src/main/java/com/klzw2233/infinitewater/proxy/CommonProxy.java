package com.klzw2233.infinitewater.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        // 服务端 & 客户端通用的初始化逻辑
        // 例如注册事件、网络通道等
    }

    public void init(FMLInitializationEvent event) {
        // 通用的 init 阶段逻辑
    }

    public void postInit(FMLPostInitializationEvent event) {
        // 模组和其他模组交互的兼容逻辑
    }

    public void serverStarting(FMLServerStartingEvent event) {
    }
}
