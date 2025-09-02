package com.klzw2233.infinitewater.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import com.klzw2233.infinitewater.core.ModConstants;

/**
 * 无限液体输出，可配置的
 * 通过手持装有液体的容器右键方块设置输出种类
 * 空手右键方块输出信息（液体种类 输出速率）
 * 向六个面的容器或者管道输出无限液体
*/
public class TileInfiniteFluid extends TileBase implements IFluidHandler{

    public static final String name = "tile_infinite_fluid";
    private Fluid outputFluid = FluidRegistry.WATER; // default water
    private int outputRate = ModConstants.rateList[0]; // mB/t

    public FluidStack Infinite_Fluid = new FluidStack(outputFluid, outputRate);

    /**
     * 每刻（tick）都会调用的更新方法。
     * 用于向相邻方块填充液体。
     */
    @Override
    public void updateEntity() {
        // 只在服务器端执行
        if (worldObj.isRemote) return;

        // 每 20 tick 执行一次（减少性能压力）
        // if (worldObj.getTotalWorldTime() % 20 != 0) return;

        for(ForgeDirection side : ForgeDirection.values()) {
            TileEntity tile = this.worldObj.getTileEntity(this.xCoord + side.offsetX, this.yCoord + side.offsetY, this.zCoord + side.offsetZ);
            if(tile != null && tile instanceof IFluidHandler) {
                int mAmount = ((IFluidHandler)tile).fill(side.getOpposite(), Infinite_Fluid, false);
                if(mAmount != 0) {
                    Infinite_Fluid.amount = mAmount;
                    ((IFluidHandler)tile).fill(side.getOpposite(), Infinite_Fluid, true);
                }
            }
        }

        Infinite_Fluid.amount = outputRate;
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
     * 如果请求抽取的是指定液体，则提供请求的流体量。
     * @param from 尝试抽取的流体方向。
     * @param resource 请求抽取的流体信息。
     * @param doDrain 是否执行实际抽取操作。
     * @return 如果请求的是指定液体，则返回一个包含请求流体量的 FluidStack；否则返回 null。
     */
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        // 如果请求的流体为空或者不是outputFluid，则返回 null。
        if (resource == null || resource.getFluid() != outputFluid) return null;
        // 否则，返回一个包含请求流体量的outputFluid流体栈。
        return new FluidStack(outputFluid, resource.amount);
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
        return new FluidStack(outputFluid, maxDrain);
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
     * @return 如果是指定液体则返回 true，否则返回 false。
     */
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return fluid == outputFluid;
    }

    /**
     * 获取流体箱（tank）信息。
     * 告诉其他方块这个方块的流体存储能力。
     * @param from 检查流体信息的方向。
     * @return 返回一个 FluidTankInfo 数组，表示这个方块有一个指定容量的液体箱。
     */
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] {
            // 返回一个 FluidTankInfo 对象，表示这是一个指定容量的液体箱。
            new FluidTankInfo(Infinite_Fluid, Integer.MAX_VALUE)
        };
    }


    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (Infinite_Fluid != null) {
            NBTTagCompound fluidTag = new NBTTagCompound();
            Infinite_Fluid.writeToNBT(fluidTag);
            tag.setTag("InfiniteFluidStack", fluidTag);
        }
        tag.setInteger("OutputRate", outputRate);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey("InfiniteFluidStack")) {
            FluidStack fs = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("InfiniteFluidStack"));
            if (fs != null && fs.getFluid() != null) {
                Infinite_Fluid = fs;
                outputFluid = fs.getFluid();
            }
        } else {
            // 兼容旧存档
            if (tag.hasKey("OutputFluid")) {
                Fluid f = FluidRegistry.getFluid(tag.getString("OutputFluid"));
                if (f != null) outputFluid = f;
            }
            if (tag.hasKey("OutputRate")) {
                outputRate = tag.getInteger("OutputRate");
            }
            Infinite_Fluid = new FluidStack(outputFluid, outputRate);
        }
    }

    public void setOutputFluid(Fluid fluid) {
        outputFluid = fluid;
        Infinite_Fluid = new FluidStack(outputFluid, outputRate);
        markDirty();
        if (worldObj != null) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    /**
     * get output rate
    */
    public int getOutputRate(){
        return outputRate;
    }

    public Fluid getOutputFluid(){ return outputFluid; }

    /**
     * 玩家蹲下空手右键方块以循环设置输出速率
     * 输出速率数组存在常量类中
     * */
    public void cycleOutputRate() {
        int idx = 0;
        for (int i = 0; i < ModConstants.rateList.length; i++) {
            if (ModConstants.rateList[i] == outputRate) {
                idx = i;
                break;
            }
        }
        outputRate = ModConstants.rateList[(idx + 1) % ModConstants.rateList.length];
        Infinite_Fluid = new FluidStack(outputFluid, outputRate); // 同步更新
        markDirty(); // 通知保存
    }

    public void writeCustomNBT(NBTTagCompound nbt) {
        this.writeToNBT(nbt);
    }

    public void readCustomNBT(NBTTagCompound nbt) {
        this.readFromNBT(nbt);
    }

}
