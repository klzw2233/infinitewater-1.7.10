package com.klzw2233.infinitewater.core;

import com.klzw2233.InfiniteWaterMod.Tags;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModConstants {

    /** Mod 基本信息 */
    public static final String MODID   = "infinitewater";      // 注册用 ID
    public static final String NAME    = "Infinite Water Block";     // 模组显示名称
    public static final String VERSION = "1.3"+"-"+ Tags.VERSION;    // 版本号 + git提交号

    //代理路径
    public static final String CLIENT_PROXY = "com.klzw2233.infinitewater.proxy.ClientProxy";
    public static final String COMMON_PROXY = "com.klzw2233.infinitewater.proxy.CommonProxy";


    /** 创造模式物品栏标签 */
    public static final CreativeTabs TAB = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            // 返回标签图标，比如你的无限水方块的物品实例
            return Item.getItemFromBlock(
                com.klzw2233.infinitewater.registry.ModBlocks.infiniteWater
            );
        }
    };

    /** 路径工具方法 */
    public static String prefix(String name) {
        return MODID + "." + name; // 用于 setBlockName / setUnlocalizedName
    }

    public static String tex(String name) {
        return MODID + ":" + name; // 用于 setBlockTextureName / setTextureName
    }

    public static String underLine(String name){ return MODID + "_"+ name; } //模组id和name用下划线连接，用于注册方块实体
}
