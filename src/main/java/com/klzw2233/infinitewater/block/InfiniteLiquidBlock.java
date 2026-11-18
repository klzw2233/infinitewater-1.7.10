package com.klzw2233.infinitewater.block;

import com.klzw2233.infinitewater.blockentity.InfiniteLiquidBlockBlockEntity;
import com.klzw2233.infinitewater.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import javax.annotation.Nullable;
import java.util.Optional;


// 无限液体方块 可以配置输出的液体类型和速率
public class InfiniteLiquidBlock extends Block implements EntityBlock {
    public InfiniteLiquidBlock(){
        // 定义方块基础属性（硬度、声音等后续可在这里扩展）
        super(Properties.of()
        .strength(4.0F));
    }

    // 把方块实体绑定到方块上
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new InfiniteLiquidBlockBlockEntity(blockPos, blockState);
    }

    // 方块引人ticker
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {

        // 只在服务端执行
        if (pLevel.isClientSide()) {
            return null;
        }

        return pBlockEntityType == ModBlockEntities.InfiniteLiquidBlock_BE.get()
                ? (lvl, p, st, be) -> ((InfiniteLiquidBlockBlockEntity) be).tick()
                : null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof InfiniteLiquidBlockBlockEntity liquidBE)) {
            return InteractionResult.PASS;
        }

        // 1) 蹲下 + 空手：循环切换速率
        if (player.isShiftKeyDown() && stack.isEmpty()) {
            if (!level.isClientSide) {
                liquidBE.cycleRate();
                player.displayClientMessage(
                        Component.translatable("message.infinitewater.rate_set",
                                liquidBE.getCurrentRate()), true);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        // 2) 手持液体容器：设置输出液体
        if (!stack.isEmpty()) {
            Optional<IFluidHandlerItem> cap =
                    stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).resolve();
            if (cap.isPresent()) {
                FluidStack fluid = cap.get().getFluidInTank(0);
                if (!level.isClientSide) {
                    if (fluid.isEmpty()) {
                        player.displayClientMessage(
                                Component.translatable("message.infinitewater.container_empty"), true);
                    } else {
                        liquidBE.setStoredFluid(fluid);
                        player.displayClientMessage(
                                Component.translatable("message.infinitewater.fluid_set",
                                        fluid.getDisplayName()), true);
                    }
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        // 3) 空手（非蹲下）：在聊天栏以操作者名义报告状态
        if (stack.isEmpty() && !player.isShiftKeyDown()) {
            if (!level.isClientSide) {
                sendStatusReport(player, liquidBE);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    private static void sendStatusReport(Player player, InfiniteLiquidBlockBlockEntity be) {
        FluidStack fluid = be.getStoredFluid();

        // 未设置液体时用占位文案，避免 getDisplayName() 返回 "Air"
        Component fluidName = fluid.isEmpty()
                ? Component.translatable("message.infinitewater.no_fluid")
                : fluid.getDisplayName();

        Component message = Component.translatable(
                "message.infinitewater.status_report",
                player.getDisplayName(),      // 操作者名
                fluidName,                    // 输出液体名
                be.getCurrentRate());         // 速率 mB/tick

        // 第二个参数 false = 聊天栏；true 才是动作栏（物品栏上方）
        player.displayClientMessage(message, false);
    }
}
