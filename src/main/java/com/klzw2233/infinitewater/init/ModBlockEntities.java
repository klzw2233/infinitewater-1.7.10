package com.klzw2233.infinitewater.init;

import com.klzw2233.infinitewater.InfiniteWater;
import com.klzw2233.infinitewater.blockentity.InfiniteWaterBlockBlockEntity;
import com.klzw2233.infinitewater.blockentity.InfiniteLiquidBlockBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


// 注册 BlockEntityType
public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, InfiniteWater.MODID);

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    /**
     * 工业处理单元的 BlockEntityType 注册。
     *
     * 这里做了三件事：
     *  1. 指定构造函数（如何创建 BlockEntity）
     *  2. 指定可绑定的方块（哪个 Block 使用它）
     *  3. 构建并注册到游戏
     */
    public static final RegistryObject<BlockEntityType<InfiniteWaterBlockBlockEntity>>
        InfiniteWaterBlock_BE =
        BLOCK_ENTITIES.register("infinite_water_block_be", () ->

            // Builder.of(构造器引用, 绑定的方块...)
            BlockEntityType.Builder.of(
                    InfiniteWaterBlockBlockEntity::new, // 如何创建 BE
                    ModBlocks.INFINITE_WATER_BLOCK.get() // 哪些方块可以拥有它
            ).build(null)
        );

    // 注册 无限液体方块的方块实体
    public static final RegistryObject<BlockEntityType<InfiniteLiquidBlockBlockEntity>>
            InfiniteLiquidBlock_BE =
            BLOCK_ENTITIES.register("infinite_liquid_block_be", () ->

                    // Builder.of(构造器引用, 绑定的方块...)
                    BlockEntityType.Builder.of(
                            InfiniteLiquidBlockBlockEntity::new, // 如何创建 BE
                            ModBlocks.INFINITE_LIQUID_BLOCK.get() // 哪些方块可以拥有它
                    ).build(null)
            );
}
