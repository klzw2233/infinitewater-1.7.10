package com.klzw2233.infinitewater;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 这是一个无限水源方块。
 * 它继承自 BlockContainer，用于容纳一个 TileEntity（方块实体）。
 */
public class BlockInfiniteWater extends BlockContainer {

    /**
     * 构造函数，用于初始化方块的属性。
     */
    public BlockInfiniteWater() {
        // 调用父类构造函数，设置方块材质为铁（Material.iron）。
        super(Material.iron);

        // 设置方块的内部名称，用于游戏注册和识别。
        setBlockName(InfiniteWaterMod.BLOCK_NAME);

        // 设置方块的纹理名称，格式为 "模组ID:纹理名称"。
        setBlockTextureName(InfiniteWaterMod.MODID + ":infinite_water");

        // 将方块添加到方块的创造模式标签页中。
        setCreativeTab(CreativeTabs.tabBlock);

        // 设置方块的硬度，影响挖掘所需时间。
        setHardness(2.0F);

        // 设置方块的爆炸抗性。
        setResistance(5.0F);

        // 设置挖掘此方块所需的工具和工具等级。
        // "pickaxe" 表示需要镐子，1 表示石镐或更高级的镐子。
        setHarvestLevel("pickaxe", 1);
    }

    /**
     * 当方块被放置在世界中时，此方法被调用以创建一个新的 TileEntity。
     * @param world 所在的世界对象。
     * @param meta 方块的元数据值。
     * @return 返回一个新的 TileInfiniteWater 实例。
     */
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        // 返回一个与此方块关联的 TileEntity 实例。
        return new TileInfiniteWater();
    }
}
