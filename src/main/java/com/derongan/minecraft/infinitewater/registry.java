package com.derongan.minecraft.infinitewater.registry;

import com.derongan.minecraft.infinitewater.InfiniteWaterMod;
import com.derongan.minecraft.infinitewater.block.BlockInfiniteFluid;
import com.derongan.minecraft.infinitewater.block.BlockInfiniteWater;
import com.derongan.minecraft.infinitewater.item.ItemBlockInfiniteFluid;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRegistry {

    public static Block infiniteWater;
    public static Block infiniteFluid;

    public static void initBlocks() {
        infiniteWater = new BlockInfiniteWater().setRegistryName(BlockInfiniteWater.NAME).setUnlocalizedName(InfiniteWaterMod.MODID + "." + BlockInfiniteWater.NAME);
        GameRegistry.register(infiniteWater);

        infiniteFluid = new BlockInfiniteFluid().setRegistryName(BlockInfiniteFluid.NAME).setUnlocalizedName(InfiniteWaterMod.MODID + "." + BlockInfiniteFluid.NAME);
        GameRegistry.register(infiniteFluid);
        GameRegistry.register(new ItemBlockInfiniteFluid(infiniteFluid).setRegistryName(BlockInfiniteFluid.NAME));
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerRender(infiniteWater);
        registerRender(infiniteFluid);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(item, 0, new net.minecraft.client.renderer.block.model.ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}