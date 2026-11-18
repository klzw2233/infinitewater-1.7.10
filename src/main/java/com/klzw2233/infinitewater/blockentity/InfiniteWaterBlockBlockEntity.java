package com.klzw2233.infinitewater.blockentity;

import com.klzw2233.infinitewater.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class InfiniteWaterBlockBlockEntity extends BlockEntity {

    // 每 tick 向每个面输出的水量，可按需调整
    private static final int OUTPUT_AMOUNT = 65536;

    public InfiniteWaterBlockBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.InfiniteWaterBlock_BE.get(), pPos, pBlockState);
    }


    // 实例 tick 方法，由 Block 类里的 ticker 直接调用
    public void tick() {
        Level level = this.level;
        // 双重保险：只在服务端执行；level 在方块实体刚创建时可能为 null
        if (level == null || level.isClientSide) return;

        BlockPos pos = this.worldPosition;
        FluidStack water = new FluidStack(Fluids.WATER, OUTPUT_AMOUNT);

        for (Direction dir : Direction.values()) {
            BlockPos targetPos = pos.relative(dir);
            BlockEntity targetBE = level.getBlockEntity(targetPos);
            if (targetBE == null) continue;

            // 从相邻方块的视角看，接口朝向我们的方向是 dir.getOpposite()
            LazyOptional<IFluidHandler> cap = targetBE.getCapability(
                    ForgeCapabilities.FLUID_HANDLER, dir.getOpposite()
            );

            cap.ifPresent(handler -> {
                // 先用 SIMULATE 检查能否接收，避免无意义的状态变更
                int accepted = handler.fill(water, IFluidHandler.FluidAction.SIMULATE);
                if (accepted > 0) {
                    handler.fill(new FluidStack(Fluids.WATER, accepted), IFluidHandler.FluidAction.EXECUTE);
                }
            });
        }
    }
}
