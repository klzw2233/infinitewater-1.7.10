package com.klzw2233.infinitewater.block;

import com.klzw2233.infinitewater.tileentity.TileBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.klzw2233.infinitewater.core.ModConstants;

/**
 * BlockBase类，是所有含有方块实体的方块类的基类，不会自动生成对应的方块实体
 * 避免使用BlockContainer 使用非普通方块渲染
*/
public class BlockBase extends Block {

    public BlockBase(Material material, String name) {
        super(material);
        setBlockName(ModConstants.prefix(name));
        setBlockTextureName(ModConstants.tex(name));
        setCreativeTab(ModConstants.TAB);
    }

    /** 如果这个方块有 TileEntity，就让子类返回 true */
    @Override
    public boolean hasTileEntity(int meta) {
        return false;
    }

    /** 如果 hasTileEntity 返回 true，则创建对应的 Tile */
    @Override
    public TileEntity createTileEntity(World world, int meta) {
        return null;
    }

    /** 拆方块时调用 Tile 清理逻辑 */
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileBase) {
                ((TileBase) te).onBlockBroken();
            }
            super.breakBlock(world, x, y, z, block, meta);
            world.removeTileEntity(x, y, z);
        }
    }
}
