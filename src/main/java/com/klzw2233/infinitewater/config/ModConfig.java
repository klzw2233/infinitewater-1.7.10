package com.klzw2233.infinitewater.config;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class ModConfig {

    // 无限水方块相关配置
    public static boolean enableInfiniteWater;
    public static int infiniteWaterRate; // 每多少tick生产一次水
    public static boolean redstoneControl; // 是否允许红石控制

    // 未来扩展配置示例
    // public static boolean enableMagicBlock;
    // public static int machinePowerUsage;

    public static void load(File configFile) {
        Configuration config = new Configuration(configFile);
        config.load();

        enableInfiniteWater = config.getBoolean(
            "EnableInfiniteWater",
            "blocks",
            true,
            "是否启用无限水方块"
        );

        infiniteWaterRate = config.getInt(
            "InfiniteWaterRate",
            "blocks",
            20,
            1,
            200,
            "方块每多少tick生成一次水 (20 tick = 1 秒)"
        );

        redstoneControl = config.getBoolean(
            "RedstoneControl",
            "blocks",
            true,
            "是否允许红石信号控制方块运作"
        );

        // 未来扩展项也在这里添加...

        if (config.hasChanged()) {
            config.save();
        }
    }
}
