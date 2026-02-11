package com.derongan.minecraft.infinitewater.tileentity;

import com.derongan.minecraft.infinitewater.config.ModConfiguration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "ic2.api.tile.IWrenchable", modid = "IC2")
public class TileInfiniteFluid extends TileInfiniteWater {
    public static final String NAME = "infinite-fluid";

    private String fluidName = "water"; // Default to water

    public TileInfiniteFluid() {
        super();
    }

    @Override
    public FluidStack getTankInfo() {
        Fluid fluid = FluidRegistry.getFluid(fluidName);
        if (fluid != null) {
            return new FluidStack(fluid, ModConfiguration.getFlowRate(getCurrentMode()));
        } else {
            // Fallback to water if fluid is not found
            return new FluidStack(FluidRegistry.WATER, ModConfiguration.getFlowRate(getCurrentMode()));
        }
    }

    public void setFluidType(Fluid fluid) {
        this.fluidName = fluid.getName();
        markDirtyAndSync();
    }

    public String getFluidName() {
        return fluidName;
    }

    @Override
    public void readFromNBT(net.minecraftforge.fml.common.registry.INBTSerializable nbt) {
        super.readFromNBT(nbt);
        if (nbt instanceof net.minecraft.nbt.NBTTagCompound) {
            net.minecraft.nbt.NBTTagCompound compound = (net.minecraft.nbt.NBTTagCompound) nbt;
            if (compound.hasKey("fluidName")) {
                this.fluidName = compound.getString("fluidName");
            }
        }
    }

    @Override
    public void writeToNBT(net.minecraftforge.fml.common.registry.INBTSerializable nbt) {
        super.writeToNBT(nbt);
        if (nbt instanceof net.minecraft.nbt.NBTTagCompound) {
            net.minecraft.nbt.NBTTagCompound compound = (net.minecraft.nbt.NBTTagCompound) nbt;
            compound.setString("fluidName", this.fluidName);
        }
    }
}