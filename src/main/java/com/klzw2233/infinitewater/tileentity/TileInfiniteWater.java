package com.klzw2233.infinitewater.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * 无限水源 TileEntity
 * - 能持续向相邻方块提供无限水
 * - 继承 TileBase，具备 NBT 存储/同步能力
 */
public class TileInfiniteWater extends TileBase implements IFluidHandler {

    public static final String name = "tile_infinite_water";

    /**
     * 定义一个静态常量，表示无限的水。
     * FluidStack 包含了流体类型（FluidRegistry.WATER）和流体量（Integer.MAX_VALUE）。
     */
    private static final FluidStack INFINITE_WATER =
        new FluidStack(FluidRegistry.WATER, Integer.MAX_VALUE);

    /**
     * 每刻（tick）都会调用的更新方法。
     * 用于向相邻方块填充水。
     */
    @Override
    public void updateEntity() {
        // 客户端不执行任何操作，只在服务器端处理。
        if (worldObj.isRemote) return;

        // 遍历所有有效的方向（上下左右前后）。
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            // 获取在当前方向上的相邻方块实体。
            TileEntity te = worldObj.getTileEntity(
                xCoord + dir.offsetX,
                yCoord + dir.offsetY,
                zCoord + dir.offsetZ
            );

            // 检查相邻的方块实体是否实现了 IFluidHandler 接口。
            if (te instanceof IFluidHandler) {
                // 如果是，则向该方块填充无限的水。
                // dir.getOpposite() 表示从该相邻方块的方向看向本方块。
                // true 表示执行实际的填充操作。
                ((IFluidHandler) te).fill(dir.getOpposite(), INFINITE_WATER, true);
            }
        }
    }

    /**
     * 填充流体的方法，本方块不接收任何流体。
     * @param from 尝试填充的流体方向。
     * @param resource 填充的流体信息。
     * @param doFill 是否执行实际填充操作。
     * @return 返回 0，表示不接受任何流体。
     */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return 0;
    }

    /**
     * 抽取流体的方法。
     * 如果请求抽取的是水，则提供请求的流体量。
     * @param from 尝试抽取的流体方向。
     * @param resource 请求抽取的流体信息。
     * @param doDrain 是否执行实际抽取操作。
     * @return 如果请求的是水，则返回一个包含请求流体量的 FluidStack；否则返回 null。
     */
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        // 如果请求的流体为空或者不是水，则返回 null。
        if (resource == null || resource.getFluid() != FluidRegistry.WATER) return null;
        // 否则，返回一个包含请求流体量的水流体栈。
        return new FluidStack(FluidRegistry.WATER, resource.amount);
    }

    /**
     * 抽取指定最大量的流体。
     * @param from 尝试抽取的流体方向。
     * @param maxDrain 请求抽取的最大流体量。
     * @param doDrain 是否执行实际抽取操作。
     * @return 总是返回一个包含请求流体量的水流体栈。
     */
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return new FluidStack(FluidRegistry.WATER, maxDrain);
    }

    /**
     * 检查是否可以从指定方向填充特定流体。
     * @param from 尝试填充的流体方向。
     * @param fluid 尝试填充的流体。
     * @return 总是返回 false，因为本方块不接受任何流体。
     */
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return false;
    }

    /**
     * 检查是否可以从指定方向抽取特定流体。
     * @param from 尝试抽取的流体方向。
     * @param fluid 尝试抽取的流体。
     * @return 如果是水则返回 true，否则返回 false。
     */
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return fluid == FluidRegistry.WATER;
    }

    /**
     * 获取流体箱（tank）信息。
     * 告诉其他方块这个方块的流体存储能力。
     * @param from 检查流体信息的方向。
     * @return 返回一个 FluidTankInfo 数组，表示这个方块有一个无限容量的水箱。
     */
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] {
            // 返回一个 FluidTankInfo 对象，表示这是一个无限容量的水箱。
            new FluidTankInfo(INFINITE_WATER, Integer.MAX_VALUE)
        };
    }

    // ===== 覆盖 TileBase 的自定义 NBT 存取 =====

    @Override
    protected void writeCustomNBT(NBTTagCompound tag) {
        // 当前类没有额外字段可保存
    }

    @Override
    protected void readCustomNBT(NBTTagCompound tag) {
        // 当前类没有额外字段需要读取
    }
}
