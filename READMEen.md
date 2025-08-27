[English](READMEen.md) | [中文](README.md)

# 🌊Infinite Water Block Mod (Forge 1.7.10)

An example Mod developed for Minecraft Forge 1.7.10 that adds an Infinite Water Block.

This block continuously injects water into surrounding fluid containers (with no delay and at an infinite rate). It can be mined with a stone pickaxe or better, and it will drop itself.

-----

## ✨ Features

- **Infinite Water Supply**：Automatically provides a water source to adjacent fluid interfaces (pipes, tanks, machines, etc.).
- **Simple and Easy to Understand**：Implemented with a single block class and a block entity for easy comprehension and extension.
- **Tool-Specific Harvesting**：Can only be harvested with a stone pickaxe or better, preventing early-game acquisition.
- **Resource Placeholder**：Includes language files and a texture directory, ready for direct replacement.

-----

## 📸 In-Game Effect

> The image below is for illustration purposes, showing the block's appearance in the world (a blue water-pattern placeholder texture has been used):
![infiniteWaterBlock](src/main/resources/assets/infinitewater/textures/blocks/infinite_water.png)
-----

## 📂 Project Structure

```
src/main/java/com/example/infinitewater/
    InfiniteWaterMod.java        // Main Class
    BlockInfiniteWater.java      // Block Definition
    TileInfiniteWater.java       // Block Entity (Fluid Logic)

src/main/resources/
    mcmod.info
    assets/infinitewater/lang/zh_CN.lang
    assets/infinitewater/textures/blocks/infinite_water.png
```

-----

## 🔧 Development Environment Requirements

- **Minecraft Forge** 1.7.10 MDK (recommended version 1614)
- JDK 8
- Gradle (or use the Gradle Wrapper that comes with the MDK)
- Recommended development tools: IntelliJ IDEA / Eclipse

-----

## 🚀 Running and Testing

1.  Clone the repository locally:

    ```bash
    git clone https://github.com/klzw2233/infinitewater-1.7.10.git
    ```

2.  Import into a Forge 1.7.10 MDK project, or place the contents of `src/` into the `src/` directory of an existing MDK.

3.  Execute in the command line:

    ```bash
    gradlew setupDecompWorkspace idea
    gradlew build
    ```

4.  Run the`runClient` configuration in your IDE to test in the game.

-----

## 🛠 Further Development Guide

If you want to extend this project, consider the following directions:

1.  **Change the Output Fluid Type**
    Modify `INFINITE_WATER` in `TileInfiniteWater` to another fluid from `FluidRegistry` (such as lava, milk, etc.).

2.  **Add a Crafting Recipe**
    Add the following in the `InfiniteWaterMod#init` method:

    ```java
    GameRegistry.addRecipe(new ItemStack(infiniteWaterBlock), "WWW", "III", "III",
        'W', Items.water_bucket,
        'I', Items.iron_ingot);
    ```

3.  **Adjust the Block's Appearance**
    Replace `assets/infinitewater/textures/blocks/infinite_water.png` with your own texture.
    Modify the `setBlockTextureName` parameter to reference the new texture name.

4.  **Extend Interaction Logic**
    Add extra checks in `updateEntity` to implement:

    * Only work when activated by a redstone signal
    * Limit the output rate
    * Record runtime statistics

5.  **Multi-Version Migration**
    Update event annotations, registration methods, etc., to adapt to 1.12+ or higher versions by referencing the new Forge API.

-----

## 🖼 Placeholder Texture Description

`assets/infinitewater/textures/blocks/infinite_water.png` is currently a placeholder texture (a solid color or a simple water texture). It is recommended to replace it with your own design. Recommended dimensions: 16×16 or 32×32 pixels, PNG format, with an optional transparent background.

-----

## 📜 License

This example project uses the MIT License. You are free to use, modify, and distribute it, but please retain the original author's attribution.

-----

## ❤️ Acknowledgements

* The Minecraft Forge Team
* The Minecraft Community Developers
* The GregtechNewHorizons Development Team
