package com.klzw2233.infinitewater.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class ItemBlockInfiniteFluid extends ItemBlock {

    public ItemBlockInfiniteFluid(Block block) {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.hasKey("InfiniteFluidStack")) {
                FluidStack fs = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("InfiniteFluidStack"));
                if (fs != null && fs.getFluid() != null) {
                    list.add(EnumChatFormatting.YELLOW + "流体类型: " + EnumChatFormatting.WHITE + fs.getFluid().getLocalizedName(fs));
                    list.add(EnumChatFormatting.GREEN + "保存量: " + EnumChatFormatting.WHITE + fs.amount + " mB");
                }
            }
            if (tag.hasKey("OutputRate")) {
                int rate = tag.getInteger("OutputRate");
                list.add(EnumChatFormatting.AQUA + "输出速率: " + EnumChatFormatting.WHITE + rate + " mB/t");
            }
        } else {
            list.add(EnumChatFormatting.GRAY + "未设置流体类型");
            list.add(EnumChatFormatting.GRAY + "默认输出速率");
        }
    }
}
