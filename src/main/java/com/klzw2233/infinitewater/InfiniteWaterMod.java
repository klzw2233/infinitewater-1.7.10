package com.klzw2233.infinitewater;

import com.klzw2233.InfiniteWaterMod.Tags;
import net.minecraft.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;

/**
 * 无限水源模组的主类。
 * 使用 @Mod 注解来定义模组的 ID、版本、名称和兼容的 Minecraft 版本。
 */
@Mod(modid = InfiniteWaterMod.MODID, version = Tags.VERSION, name = InfiniteWaterMod.MODName, acceptedMinecraftVersions = "[1.7.10]")
public class InfiniteWaterMod {

    /** 模组的唯一标识符。*/
    public static final String MODID = "infinitewater";

    public static final String MODName = "Infinite Water Block";

    /** 用于记录日志的日志对象。*/
    public static final Logger LOG = LogManager.getLogger(MODID);

    // 添加常量以集中管理字符串，避免硬编码
    public static final String BLOCK_NAME = "infinite_water_block";
    public static final String TILE_NAME = "tile_infinite_water";


    /** 无限水源方块的静态实例。*/
    public static Block infiniteWaterBlock;

    /** * 侧代理模式（SidedProxy）的实例，用于区分客户端和服务器端的逻辑。
     * @SidedProxy 注解指定了客户端和服务器端的代理类。
     */
    @SidedProxy(clientSide = "com.klzw2233.infinitewater.ClientProxy", serverSide = "com.klzw2233.infinitewater.CommonProxy")
    //这行代码本身就是为了让 Forge 框架能够找到并初始化你的代理实例，从而将你的模组逻辑正确地分配给客户端或服务器环境。
    public static CommonProxy proxy;

}
