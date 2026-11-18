package com.klzw2233.infinitewater.blockentity;

import com.klzw2233.infinitewater.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;


// 可以通过手持液体容器来右键无限液体方块设置要输出的液体类型
// 蹲下空手右键方块来循环设置输出液体的速率
public class InfiniteLiquidBlockBlockEntity extends BlockEntity {

    /** 速率档位，单位 mB/tick。可自由增删，循环顺序即数组顺序 */
    public static final int[] RATE_PRESETS = {1024, 4096, 16384, 65536, 262144, 1048576};

    private static final int DEFAULT_RATE_INDEX = 0; // 默认 1024 mB/tick

    /** 当前输出的液体，EMPTY 表示未设置 */
    private FluidStack storedFluid = FluidStack.EMPTY;
    /** 当前速率在 RATE_PRESETS 中的下标 */
    private int rateIndex = DEFAULT_RATE_INDEX;


    public InfiniteLiquidBlockBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.InfiniteLiquidBlock_BE.get(), pPos, pBlockState);
    }


    public FluidStack getStoredFluid() {
        return storedFluid;
    }

    public int getCurrentRate() {
        return RATE_PRESETS[rateIndex];
    }

    /** 手持液体容器右键时调用，不消耗容器内液体 */
    public void setStoredFluid(FluidStack fluid) {
        this.storedFluid = fluid.copy();
        setChanged();
        syncToClient();
    }

    /** 蹲下空手右键时调用，循环切换速率档位 */
    public void cycleRate() {
        this.rateIndex = (this.rateIndex + 1) % RATE_PRESETS.length;
        setChanged();
        syncToClient();
    }

    private void syncToClient() {
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    /** 每 tick 向六个面的流体容器/管道输出当前液体 */
    public void tick() {
        Level level = this.level;
        if (level == null || level.isClientSide) return;
        if (storedFluid.isEmpty()) return;

        int amount = getCurrentRate();

        for (Direction dir : Direction.values()) {
            BlockPos targetPos = worldPosition.relative(dir);
            BlockEntity targetBE = level.getBlockEntity(targetPos);
            if (targetBE == null) continue;

            LazyOptional<IFluidHandler> cap = targetBE.getCapability(
                    ForgeCapabilities.FLUID_HANDLER, dir.getOpposite());

            cap.ifPresent(handler -> {
                // 直接构造指定数量的 FluidStack，避免依赖 setAmount 的可变性
                FluidStack offer = new FluidStack(storedFluid.getFluid(), amount, storedFluid.getTag());

                int accepted = handler.fill(offer, IFluidHandler.FluidAction.SIMULATE);
                if (accepted > 0) {
                    FluidStack exec = new FluidStack(storedFluid.getFluid(), accepted, storedFluid.getTag());
                    handler.fill(exec, IFluidHandler.FluidAction.EXECUTE);
                }
            });
        }
    }

    // ---------- NBT 持久化 ----------

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!storedFluid.isEmpty()) {
            tag.put("StoredFluid", storedFluid.writeToNBT(new CompoundTag()));
        }
        tag.putInt("RateIndex", rateIndex);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        storedFluid = tag.contains("StoredFluid")
                ? FluidStack.loadFluidStackFromNBT(tag.getCompound("StoredFluid"))
                : FluidStack.EMPTY;
        rateIndex = Mth.clamp(tag.getInt("RateIndex"), 0, RATE_PRESETS.length - 1);
    }

    // ---------- 客户端同步 ----------

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
