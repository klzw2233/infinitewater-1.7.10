package com.klzw2233.infinitewater;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * 这是一个管理所有配方的类。
 */
public class Recipes {

    /**
     * 注册所有配方的方法。
     */
    public static void registerRecipes() {
        // 创建一个包含你方块的 ItemStack
        ItemStack infiniteWaterBlockStack = new ItemStack(CommonProxy.infiniteWaterBlock, 4);

        // 使用水桶和铁锭的配方
        GameRegistry.addRecipe(infiniteWaterBlockStack,
            "III",
            "IWI",
            "III",
            'I', Items.iron_ingot,
            'W', Items.water_bucket
        );

    }
}
