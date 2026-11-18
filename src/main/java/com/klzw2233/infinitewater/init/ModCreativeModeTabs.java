package com.klzw2233.infinitewater.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, com.klzw2233.infinitewater.InfiniteWater.MODID);

    // 注册本模组的创造模式标签。
    // "infinitewater" 为该标签的注册名，会作为内部 ID 使用。
    public static final RegistryObject<CreativeModeTab> InfiniteWater =
        CREATIVE_MODE_TABS.register("infinitewater",
            () -> CreativeModeTab.builder()

                // 设置创造标签在界面中显示的图标。
                // 这里使用石头作为示例图标，后续可以替换为模组物品。
                .icon(() -> new ItemStack(Items.STONE))

                // 设置标签的显示名称。
                // 使用可本地化文本（语言文件中定义）。
                .title(Component.translatable("tab.infinitewater"))

                // 定义该标签中显示的物品内容。
                // output.accept(...) 用于向标签中添加物品。
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModBlocks.INFINITE_WATER_BLOCK.get());    // 无限水方块
                    output.accept(ModBlocks.INFINITE_LIQUID_BLOCK.get());    // 无限液体方块
                })

                // 构建最终的 CreativeModeTab 实例。
                .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
