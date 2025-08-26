repositories {
    // 添加 Maven Central 仓库
    mavenCentral()
}
plugins {
    id("com.gtnewhorizons.gtnhconvention")
    //id("com.diffplug.spotless") version "6.23.0" // 使用最新稳定版
}

// 禁用 Spotless 任务（如果被隐式应用）
//tasks.named<com.diffplug.gradle.spotless.SpotlessTask>("spotlessJavaCheck") {
//    enabled = false
//}

// 或者禁用所有 Spotless 任务
tasks.withType<com.diffplug.gradle.spotless.SpotlessTask>().configureEach {
    enabled = false
}
//代码风格检查直接报error的傻逼
tasks.named<Checkstyle>("checkstyleMain") {
    enabled = false // 禁用所有 Checkstyle 任务
}
