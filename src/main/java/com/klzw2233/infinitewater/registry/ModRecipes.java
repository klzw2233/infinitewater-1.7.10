package com.klzw2233.infinitewater.registry;

import com.klzw2233.infinitewater.config.ModConfig;
import com.klzw2233.infinitewater.util.ModLogger;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes {

    public static void register() {
        ModLogger.info("=== 开始注册配方 ===");

        if (ModConfig.enableInfiniteWater) {
            registerInfiniteWaterRecipe();
        } else {
            ModLogger.warn("[跳过] 无限水方块已被配置文件禁用");
        }

        ModLogger.info("=== 配方注册结束 ===");
    }

    private static void registerInfiniteWaterRecipe() {
        // 如果未来想用其他模组的材料，可以加条件
        if (Loader.isModLoaded("IC2")) {
            ItemStack refinedIron = GameRegistry.findItemStack("IC2", "refinedIronIngot", 1);
            if (refinedIron != null) {
                GameRegistry.addRecipe(new ItemStack(ModBlocks.infiniteWater),
                    "WIW",
                    "IWI",
                    "WIW",
                    'W', Items.cauldron,
                    'I', refinedIron
                );
                ModLogger.info("[成功] 无限水方块配方 (IC2 精炼铁)");
                return;
            } else {
                ModLogger.warn("[警告] 检测到 IC2 但找不到 refinedIronIngot，使用默认配方");
            }
        }

        // 默认配方
        GameRegistry.addRecipe(new ItemStack(ModBlocks.infiniteWater),
            "WIW",
            "IWI",
            "WIW",
            'W', Items.cauldron,
            'I', Items.iron_ingot
        );
        ModLogger.info("[成功] 无限水方块配方 (默认铁锭炼药锅)");
    }
}
