package com.klzw2233.infinitewater.block;

import com.klzw2233.infinitewater.tileentity.TileInfiniteWater;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 这是一个无限水源方块。
 * 它继承自 BlockContainer，用于容纳一个 TileEntity（方块实体）。
 */
public class BlockInfiniteWater extends BlockBase {//改自BlockContainer

    public static final String name = "block_infinite_water";

    /**
     * 构造函数，用于初始化方块的属性。
     */
    public BlockInfiniteWater() {
        // 调用父类构造函数，设置方块材质为铁（Material.iron）。
        super(Material.iron, name);


        // 设置方块的硬度，影响挖掘所需时间。
        setHardness(2.0F);

        // 设置方块的爆炸抗性。
        setResistance(5.0F);

        // 设置挖掘此方块所需的工具和工具等级。
        // "pickaxe" 表示需要镐子，1 表示石镐或更高级的镐子。
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    /**
     * 当方块被放置在世界中时，此方法被调用以创建一个新的 TileEntity。
     * @param world 所在的世界对象。
     * @param meta 方块的元数据值。
     * @return 返回一个新的 TileInfiniteWater 实例。
     */
    @Override
    public TileEntity createTileEntity(World world, int meta) {
        // 返回一个与此方块关联的 TileEntity 实例。
        return new TileInfiniteWater();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileInfiniteWater) {
            ((TileInfiniteWater) te).onBlockBroken(); // 预留给 TileEntity 清理用
        }
        super.breakBlock(world, x, y, z, block, meta);
        world.removeTileEntity(x, y, z);
    }
}
