package com.klzw2233.infinitewater.init;

import com.klzw2233.infinitewater.InfiniteWater;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, InfiniteWater.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // 为 infinite_water_block 注册对应的物品形式（BlockItem）。
    // 该注册名与方块注册名保持一致，
    public static final RegistryObject<Item> INFINITE_WATER_BLOCK =
        ITEMS.register("infinite_water_block",
            // 创建 BlockItem，将其与已注册的方块绑定。
            // ModBlocks.INFINITE_WATER_BLOCK.get() 获取对应的方块实例，
            // 这样玩家在背包中持有该物品时，才能放置出对应的方块。
            () -> new BlockItem(
                ModBlocks.INFINITE_WATER_BLOCK.get(),
                new Item.Properties()));

    public static final RegistryObject<Item> INFINITE_LIQUID_BLOCK =
            ITEMS.register("infinite_liquid_block",
                    () -> new BlockItem(
                            ModBlocks.INFINITE_LIQUID_BLOCK.get(),
                            new Item.Properties()));
}
