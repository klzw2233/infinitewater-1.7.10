package com.klzw2233.infinitewater.init;

import com.klzw2233.infinitewater.InfiniteWater;
import com.klzw2233.infinitewater.block.InfiniteWaterBlock;
import com.klzw2233.infinitewater.block.InfiniteLiquidBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    // 创建一个方块注册器。
    // 第一个参数指定注册类型（方块），
    // 第二个参数指定本模组的 MODID。
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, InfiniteWater.MODID);

    // 将本类中的注册器挂载到 Mod 事件总线。
    // 只有调用此方法后，方块才会在加载阶段被真正注册到游戏中。
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


    // 注册 无限水方块
    public static final RegistryObject<Block> INFINITE_WATER_BLOCK =
        BLOCKS.register("infinite_water_block",
            InfiniteWaterBlock::new);

    // 无限液体方块
    public static final RegistryObject<Block> INFINITE_LIQUID_BLOCK =
        BLOCKS.register("infinite_liquid_block",
            InfiniteLiquidBlock::new);

}
