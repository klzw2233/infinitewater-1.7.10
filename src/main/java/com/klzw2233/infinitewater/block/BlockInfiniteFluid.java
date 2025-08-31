package com.klzw2233.infinitewater.block;

import com.klzw2233.infinitewater.tileentity.TileInfiniteFluid;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;
import net.minecraft.entity.item.EntityItem;

/*
* 无限流体类，可以改变流体类型和输出速率
* 更灵活，避免改变之前的方块代码，兼容老版本的存档
*/
public class BlockInfiniteFluid extends BlockBase {

    public static final String name = "block_infinite_fluid"; //注册进游戏系统的方块名

    public static String displayName = name;

    /**
     * 构造函数，用于初始化方块的属性。
     */
    public BlockInfiniteFluid() {
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


    // 右键方块改变液体类型
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                                    EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileInfiniteFluid) {
                TileInfiniteFluid tile = (TileInfiniteFluid) te;
                ItemStack held = player.getCurrentEquippedItem();

                if (held != null) {
                    // 手里有物品，尝试设置输出流体
                    FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(held);
                    if (fs != null) {
                        tile.setOutputFluid(fs.getFluid());
                        player.addChatMessage(new ChatComponentText(
                            "Output fluid set to: " + fs.getFluid().getLocalizedName(fs)
                        ));
                        return true;
                    }
                } else {
                    // 空手
                    if (player.isSneaking()) {
                        // 潜行空手：循环切换输出速率
                        tile.cycleOutputRate();
                        player.addChatMessage(new ChatComponentText(
                            "Output rate: " + tile.getOutputRate() + " mB/t"
                        ));
                        return true;
                    } else {
                        // 普通空手：显示当前流体类型和速率
                        player.addChatMessage(new ChatComponentText(
                            "Fluid: " + tile.getOutputFluid().getLocalizedName(new FluidStack(tile.getOutputFluid(), 1))
                                + " | Rate: " + tile.getOutputRate() + " mB/t"
                        ));
                        return true;
                    }
                }
            }
        }
        return true;
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
        return new TileInfiniteFluid();
    }


    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileInfiniteFluid) {
                TileInfiniteFluid tile = (TileInfiniteFluid) te;

                // 让 TileEntity 有机会清理资源
                tile.onBlockBroken();

                // 创建方块物品
                Item item = Item.getItemFromBlock(this);
                if (item != null) {
                    ItemStack stack = new ItemStack(item);

                    // 写入自定义 NBT
                    NBTTagCompound tag = new NBTTagCompound();
                    tile.writeCustomNBT(tag);
                    stack.setTagCompound(tag);

                    // 掉落物品到世界
                    EntityItem entityItem = new EntityItem(
                        world,
                        x + 0.5, y + 0.5, z + 0.5,
                        stack
                    );
                    world.spawnEntityInWorld(entityItem);
                }
            }

            // 手动移除 TileEntity 和方块
            world.removeTileEntity(x, y, z);
            world.setBlockToAir(x, y, z);
        }
        // 注意：这里不调用 super.breakBlock(...)，阻止默认掉落
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileInfiniteFluid && stack.hasTagCompound()) {
                NBTTagCompound tag = stack.getTagCompound();
                ((TileInfiniteFluid) te).readCustomNBT(tag); // 从物品 NBT 恢复数据
            }
        }
        super.onBlockPlacedBy(world, x, y, z, placer, stack);
    }

}
