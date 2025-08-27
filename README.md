[English](READMEen.md) | [中文](README.md)

# 🌊 无限水方块 Mod (Forge 1.7.10)

一个为 Minecraft Forge 1.7.10 开发的示例 Mod，添加了一个**无限水方块**。
该方块会持续向周围的流体容器注入水（无延迟、无限速率），可用石镐及以上的工具挖掘并掉落自身。

-----

## ✨ 功能特性

- **无限供水**：自动为相邻的流体接口（管道、储罐、机器等）提供水源。
- **简单易懂**：通过单一的方块类和方块实体实现，方便理解和扩展。
- **工具采集**：仅限石镐及以上工具采集，避免过早获取。
- **资源占位**：已附带语言文件和贴图目录，可直接替换。

-----

## 📸 游戏内效果

> 下图为示意图，展示了方块在世界中的外观（已使用蓝色水纹占位贴图）：
![无限水方块](src/main/resources/assets/infinitewater/textures/blocks/infinite_water.png)
-----

## 📂 工程结构

```
src/main/java/com/example/infinitewater/
    InfiniteWaterMod.java        // 主类
    BlockInfiniteWater.java      // 方块定义
    TileInfiniteWater.java       // 方块实体（流体逻辑）

src/main/resources/
    mcmod.info
    assets/infinitewater/lang/zh_CN.lang
    assets/infinitewater/textures/blocks/infinite_water.png
```

-----

## 🔧 开发环境要求

- **Minecraft Forge** 1.7.10 MDK（建议版本 1614）
- JDK 8
- Gradle（或使用 MDK 自带的 Gradle Wrapper）
- 开发工具推荐 IntelliJ IDEA / Eclipse

-----

## 🚀 运行与测试

1.  克隆仓库到本地：

    ```bash
    git clone https://github.com/klzw2233/infinitewater-1.7.10.git
    ```

2.  导入到 Forge 1.7.10 MDK 工程中，或将 `src/` 内容放入现有 MDK 的 `src/` 目录。

3.  在命令行执行：

    ```bash
    gradlew setupDecompWorkspace idea
    gradlew build
    ```

4.  在 IDE 中运行 `runClient` 配置，即可在游戏中测试。

-----

## 🛠 二次开发指南

如果你想基于此项目进行扩展，可以考虑以下方向：

1.  **改变输出流体类型**
    修改 `TileInfiniteWater` 中的 `INFINITE_WATER` 为 `FluidRegistry` 中的其他流体（如岩浆、牛奶等）。

2.  **添加合成配方**
    在 `InfiniteWaterMod#init` 方法中添加：

    ```java
    GameRegistry.addRecipe(new ItemStack(infiniteWaterBlock), "WWW", "III", "III",
        'W', Items.water_bucket,
        'I', Items.iron_ingot);
    ```

3.  **调整方块外观**
    将 `assets/infinitewater/textures/blocks/infinite_water.png` 替换为自己的贴图。
    修改 `setBlockTextureName` 参数以引用新的纹理名称。

4.  **交互逻辑扩展**
    在 `updateEntity` 中添加额外判断，实现：

    * 仅在红石信号激活时工作
    * 限制输出速率
    * 记录运行统计

5.  **多版本迁移**
    对照新版 Forge API 更新事件注解、注册方法等，使其适配 1.12+ 或更高版本。

-----

## 🖼 占位贴图说明

`assets/infinitewater/textures/blocks/infinite_water.png` 当前为占位贴图（纯色或简易水纹理），建议替换为你自己的设计。 推荐尺寸：16×16 或 32×32 像素，PNG 格式，透明背景可选。

-----

## 📜 授权协议

本示例项目使用 **MIT License**，你可以自由使用、修改和分发，但请保留原始作者署名。

-----

## ❤️ 鸣谢

* Minecraft Forge 团队
* Minecraft 社区开发者
* GregtechNewHorizons 开发团队
