package com.klzw2233.infinitewater.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * TileEntity 通用基类
 * - 提供统一的 NBT 存储/读取
 * - 客户端同步
 * - 红石状态检测
 * - 拆方块钩子
 */
public class TileBase extends TileEntity {


    /**
     * 自定义方法，需要你自己在 breakBlock 里手动调用
     * 处理 TileEntity 内部的清理逻辑，比如：
     * 保存数据到掉落物 NBT
     * 断开与其他 TileEntity 的连接
     * 停止定时任务、释放资源
     * 子类可覆盖以实现掉落物、保存数据等逻辑
     */
    public void onBlockBroken() {
        // 默认无操作
        markDirty();
    }

    /**
     * 检测是否被红石信号激活
     * @return true 表示有红石信号
     */
    public boolean isPowered() {
        return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    /**
     * 写入 NBT（包含自定义数据）
     */
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        writeCustomNBT(tag);
    }

    /**
     * 读取 NBT（包含自定义数据）
     */
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        readCustomNBT(tag);
    }

    /**
     * 子类实现：写入自定义 NBT 数据
     */
    public void writeCustomNBT(NBTTagCompound tag){};

    /**
     * 子类实现：读取自定义 NBT 数据
     */
    public void readCustomNBT(NBTTagCompound tag){};

    /**
     * 同步到客户端的数据包
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    /**
     * 客户端接收服务端同步包
     */
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }
}
