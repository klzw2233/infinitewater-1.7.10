package com.derongan.minecraft.infinitewater.item;

import com.derongan.minecraft.infinitewater.block.BlockInfiniteFluid;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockInfiniteFluid extends ItemBlock {
    public ItemBlockInfiniteFluid(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "tile." + BlockInfiniteFluid.NAME;
    }
}