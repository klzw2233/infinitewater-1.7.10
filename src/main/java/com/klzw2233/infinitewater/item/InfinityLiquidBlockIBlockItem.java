package com.klzw2233.infinitewater.item;

import com.klzw2233.infinitewater.blockentity.InfiniteLiquidBlockBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

public class InfinityLiquidBlockIBlockItem extends BlockItem {

    public InfinityLiquidBlockIBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    // 在物品栏显示 输出液体种类和速率
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, TooltipFlag flag) {
        CompoundTag itemTag = stack.getTag();
        if (itemTag != null && itemTag.contains("BlockEntityTag")) {
            CompoundTag beTag = itemTag.getCompound("BlockEntityTag");

            if (beTag.contains("StoredFluid") && beTag.contains("RateIndex")) {

                // ---- 1. 读取 NBT ----
                FluidStack storedFluid = FluidStack.loadFluidStackFromNBT(beTag.getCompound("StoredFluid"));

                int[] presets = InfiniteLiquidBlockBlockEntity.RATE_PRESETS;
                int rateIndex = Mth.clamp(beTag.getInt("RateIndex"), 0, presets.length - 1);
                int rate = presets[rateIndex];

                // ---- 2. 流体种类行 ----
                Component fluidName = storedFluid.isEmpty()
                        ? Component.translatable("tooltip.infinitewater.infinity_liquid.empty")
                        .withStyle(ChatFormatting.GRAY)
                        : storedFluid.getDisplayName().copy()
                        .withStyle(ChatFormatting.AQUA);

                MutableComponent fluidLine = Component.translatable(
                        "tooltip.infinitewater.infinity_liquid.fluid",
                        fluidName
                );
                tooltip.add(fluidLine);

                // ---- 3. 输出速率行 ----
                MutableComponent rateLine = Component.translatable(
                        "tooltip.infinitewater.infinity_liquid.rate",
                        Component.literal(String.valueOf(rate)).withStyle(ChatFormatting.YELLOW)
                );
                tooltip.add(rateLine);
            }
        }

        super.appendHoverText(stack, level, tooltip, flag);
    }
}
