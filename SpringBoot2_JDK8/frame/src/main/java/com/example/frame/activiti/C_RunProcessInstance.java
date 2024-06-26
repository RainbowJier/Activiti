package com.example.frame.activiti;

import com.example.frame.domain.Constants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
/**
 * @Description 👀✔🐱‍🐉❌
 * @Author RainbowJier
 * @Date 2024/6/26
 */
public class C_RunProcessInstance {
    private static final String DEPLOYMENT_ID = Constants.DEPLOYMENT_ID;
    private static final ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    public static void main(String[] args) {
        ProcessInstance processInstance = runProcessInstance();

        // 输出流程实例的相关信息
        System.out.println("流程定义的ID: " + processInstance.getProcessDefinitionId());
        System.out.println("实例ID: " + processInstance.getId());

    }

    public static ProcessInstance runProcessInstance() {
        // 获取流程定义
        ProcessDefinition processDefinition = getProcessDefinition();

        // 根据流程定义id，启动流程实例
        RuntimeService runtimeService = engine.getRuntimeService();

        assert processDefinition != null;
        return runtimeService
                .startProcessInstanceById(processDefinition.getId());
    }


    /**
     * 根据部署id获取流程定义实例
     */
    public static ProcessDefinition getProcessDefinition() {
        RepositoryService repositoryService = engine.getRepositoryService();

        // 根据部署ID获取流程定义
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()      // 创建流程定义查询对象
                .deploymentId(DEPLOYMENT_ID)         // 指定部署ID
                .singleResult();
        // 获取唯一结果
        if (processDefinition == null) {
            System.out.println("未找到指定部署ID的流程定义");
            return null;
        }
        return processDefinition;
    }
}
