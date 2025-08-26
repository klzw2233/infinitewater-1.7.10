package com.example.infinitewater;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInfiniteWater extends BlockContainer {
    public BlockInfiniteWater() {
        super(Material.iron);
        setBlockName("infinite_water_block");
        setBlockTextureName(InfiniteWaterMod.MODID + ":infinite_water");
        setCreativeTab(CreativeTabs.tabBlock);
        setHardness(2.0F);
        setResistance(5.0F);
        setHarvestLevel("pickaxe", 1); // 石镐等级
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileInfiniteWater();
    }
}
