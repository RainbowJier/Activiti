package com.example.frame.activiti;

import com.example.frame.domain.Constants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @Description 👀✔🐱‍🐉❌激活流程实例
 * @Author RainbowJier
 * @Date 2024/6/27
 */
public class K_ActivateProcess {
    private static final String DEPLOYMENT_ID = Constants.DEPLOYMENT_ID;
    private static final ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    public static void main(String[] args) {
        RuntimeService runtimeService = engine.getRuntimeService();
        ProcessDefinition processDefinition = getProcessDefinition();

        assert processDefinition != null;
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionId(processDefinition.getId())
                .singleResult();

        boolean suspended = processInstance.isSuspended();
        if (suspended) {
            //当前流程实例已被挂起，激活的操作
            runtimeService.activateProcessInstanceById(processInstance.getId());
            System.out.println(processInstance.getId() + "流程被激活");
        }
    }
    /**
     * 根据部署 id 获取流程定义
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
