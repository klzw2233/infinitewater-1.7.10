package com.klzw2233.infinitewater.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


//日志类
public class ModLogger {

    // 全局唯一 Logger 实例
    private static final Logger LOGGER = LogManager.getLogger("InfiniteWater");

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    // 如果想记录异常栈，可加一个重载
    public static void error(String message, Throwable t) {
        LOGGER.error(message, t);
    }
}
