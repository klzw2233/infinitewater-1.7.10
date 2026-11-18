package com.klzw2233.infinitewater.block;

import com.klzw2233.infinitewater.blockentity.InfiniteWaterBlockBlockEntity;
import com.klzw2233.infinitewater.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;


// 无限水方块 继承
public class InfiniteWaterBlock extends Block implements EntityBlock {
    public InfiniteWaterBlock(){
        // 定义方块基础属性（硬度、声音等后续可在这里扩展）
        super(Properties.of()
        .strength(2.0F));
    }

    // 把方块实体绑定到方块上
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new InfiniteWaterBlockBlockEntity(blockPos, blockState);
    }

    // 方块引人ticker
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {

        // 只在服务端执行
        if (pLevel.isClientSide()) {
            return null;
        }

        return pBlockEntityType == ModBlockEntities.InfiniteWaterBlock_BE.get()
                ? (lvl, p, st, be) -> ((InfiniteWaterBlockBlockEntity) be).tick()
                : null;
    }
}
