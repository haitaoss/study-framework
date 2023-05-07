package com.atguigu

import org.gradle.api.Plugin
import org.gradle.api.Project

class Text implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task("atguigu") {
            doLast {
                println("自定义atguigu插件")
            }
        }
    }
}