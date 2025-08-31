package com.klzw2233.infinitewater.registry;

import com.klzw2233.infinitewater.config.ModConfig;
import com.klzw2233.infinitewater.util.ModLogger;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes {

    public static void register() {
        ModLogger.info("=== [Start] Registering recipe ===");

        if (ModConfig.enableInfiniteWater) {
            registerInfiniteWaterRecipe();
        } else {
            ModLogger.warn("[Skipped] Infinite Water Block has been disabled in the configuration file");
        }

        ModLogger.info("=== [Completed] Recipe registration completed ===");
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
                ModLogger.info("[Success] Infinite Water Block recipe (IC2 Refined Iron)");
                return;
            } else {
                ModLogger.warn("[Warning] IC2 detected, but the item refinedIronIngot was not found. Default recipe has been applied");
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
        ModLogger.info("[Success] Infinite Water Block recipe (Default Iron Ingot Cauldron)");
    }
}
