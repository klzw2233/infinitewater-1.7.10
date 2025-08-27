package com.klzw2233.infinitewater;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

/**
 * 这是一个静态配置类，用于处理模组的配置文件。
 * 它负责加载和保存模组的各种设置。
 */
public class Config {

    /**
     * 定义一个公共静态字符串，作为模组的问候语。
     * 这是配置项的默认值。
     */
    public static String greeting = "Hello World";

    /**
     * 同步配置文件的方法。
     * 该方法会从配置文件中读取配置项，如果配置文件不存在，则使用默认值并创建文件。
     * * @param configFile 要同步的配置文件对象。
     */
    public static void synchronizeConfiguration(File configFile) {
        // 创建一个 Configuration 实例，用于操作配置文件。
        Configuration configuration = new Configuration(configFile);

        // 使用 configuration.getString() 方法来获取配置项。
        // 第一个参数是配置项的键名（greeting）。
        // 第二个参数是配置项所属的类别（这里是通用类别）。
        // 第三个参数是默认值（greeting 变量的当前值）。
        // 第四个参数是注释，用于在配置文件中解释该配置项的用途。
        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "How shall I greet?");

        // 检查配置文件自加载以来是否发生过更改。
        // 如果更改过（例如，首次运行时创建了文件或修改了值），则保存文件。
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
