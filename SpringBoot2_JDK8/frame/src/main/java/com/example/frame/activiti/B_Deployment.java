package com.example.frame.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 * @Description 👀✔🐱‍🐉❌部署activiti，将bpmn、png文件存储到数据库。
 * @Author RainbowJier
 * @Date 2024/6/26
 */
public class B_Deployment {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();

        // 部署流程
        org.activiti.engine.repository.Deployment deploy = repositoryService.createDeployment()
                // Add source.
                .addClasspathResource("bpmn/leave-1.bpmn20.xml")
                .addClasspathResource("bpmn/leave.png")
                .name("请假申请流程")
                .deploy();
        System.out.println("流程部署的id:"+deploy.getId());
        System.out.println("流程name:"+deploy.getName());

    }
}
