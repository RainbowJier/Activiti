package com.example.frame.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

/**
 * @Description 👀✔🐱‍🐉❌ 生成Activit数据表
 * @Author RainbowJier
 * @Date 2024/6/25
 */
public class A_CreateTable {
    public static void main(String[] args) {
        //加载配置
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //获取ProcessEngine对象
        ProcessEngine processEngine =
                configuration.buildProcessEngine();
    }
}