package com.derongan.minecraft.infinitewater.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ModConfiguration {
    public static Configuration config;

    // Configuration values
    public static boolean enableInfiniteWater = true;
    public static boolean enableInfiniteFluid = true;
    public static int[] flowRates = {1000, 5000, 10000}; // Different flow rates in mB/tick

    public static void loadConfig(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
    }

    public static void syncConfig() {
        enableInfiniteWater = config.getBoolean("enableInfiniteWater", "general", true, "Enable Infinite Water Block");
        enableInfiniteFluid = config.getBoolean("enableInfiniteFluid", "general", true, "Enable Infinite Fluid Block");

        String[] flowRateStrings = config.getStringList("flowRates", "general", new String[]{"1000", "5000", "10000"}, "Flow rates in mB/tick");
        flowRates = new int[flowRateStrings.length];
        for (int i = 0; i < flowRateStrings.length; i++) {
            try {
                flowRates[i] = Integer.parseInt(flowRateStrings[i]);
            } catch (NumberFormatException e) {
                flowRates[i] = i == 0 ? 1000 : flowRates[i-1] * 2; // Default fallback
            }
        }

        if (config.hasChanged()) {
            config.save();
        }
    }

    /**
     * Gets the flow rate for a specific mode
     *
     * @param mode The mode (0-indexed)
     * @return The flow rate in mB/tick, or 0 if mode is invalid
     */
    public static int getFlowRate(int mode) {
        if (mode >= 0 && mode < flowRates.length) {
            return flowRates[mode];
        }
        return 0; // Return 0 if mode is out of bounds
    }
}