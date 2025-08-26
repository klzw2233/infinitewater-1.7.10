package com.klzw2233.infinitewater;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

public class TileInfiniteWater extends TileEntity implements IFluidHandler {
    private static final FluidStack INFINITE_WATER =
        new FluidStack(FluidRegistry.WATER, Integer.MAX_VALUE);

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity te = worldObj.getTileEntity(
                xCoord + dir.offsetX,
                yCoord + dir.offsetY,
                zCoord + dir.offsetZ
            );
            if (te instanceof IFluidHandler) {
                ((IFluidHandler) te).fill(dir.getOpposite(), INFINITE_WATER, true);
            }
        }
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return 0; // 不接收
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || resource.getFluid() != FluidRegistry.WATER) return null;
        return new FluidStack(FluidRegistry.WATER, resource.amount);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return new FluidStack(FluidRegistry.WATER, maxDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return fluid == FluidRegistry.WATER;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] {
            new FluidTankInfo(INFINITE_WATER, Integer.MAX_VALUE)
        };
    }
}
