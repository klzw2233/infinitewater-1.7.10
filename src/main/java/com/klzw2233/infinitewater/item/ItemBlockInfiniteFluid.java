package com.klzw2233.infinitewater.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class ItemBlockInfiniteFluid extends ItemBlock {

    public ItemBlockInfiniteFluid(Block block) {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT) // 只在客户端执行
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        // 检查是否有 NBT
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();

            // 读取 outputFluid
            if (tag.hasKey("OutputFluid")) {
                String fluidName = tag.getString("OutputFluid");
                list.add(EnumChatFormatting.YELLOW + "流体类型: " + EnumChatFormatting.WHITE + fluidName);
            }

            // 读取 outputRate
            if (tag.hasKey("OutputRate")) {
                int rate = tag.getInteger("OutputRate");
                list.add(EnumChatFormatting.GREEN + "输出速率: " + EnumChatFormatting.WHITE + rate + " mB/t");
            }
        } else {
            // 没有 NBT 时的默认提示
            list.add(EnumChatFormatting.GRAY + "未设置流体类型");
            list.add(EnumChatFormatting.GRAY + "默认输出速率");
        }
    }
}
