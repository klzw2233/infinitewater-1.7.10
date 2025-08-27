package com.klzw2233.infinitewater;

/**
 * 这是一个客户端专用的代理类。
 * 它继承自 CommonProxy，并可以在需要时重写方法以实现客户端特有的行为（如注册渲染器）。
 */
public class ClientProxy extends CommonProxy {

    // 可以在这里重写 CommonProxy 中的方法，以实现客户端特有的功能。
    // 例如：
    // @Override
    // public void preInit(FMLPreInitializationEvent event) {
    //    super.preInit(event);
    //    // 注册客户端渲染器等...
    // }
}
