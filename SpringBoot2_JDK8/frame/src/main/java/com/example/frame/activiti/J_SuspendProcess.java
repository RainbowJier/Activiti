package com.example.frame.activiti;

import com.example.frame.domain.Constants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @Description 👀✔🐱‍🐉❌流程实例挂起
 * @Author RainbowJier
 * @Date 2024/6/27
 */
public class J_SuspendProcess {
    private static final String DEPLOYMENT_ID = Constants.DEPLOYMENT_ID;
    private static final ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    public static void main(String[] args) {
        RuntimeService runtimeService =  engine.getRuntimeService();

        ProcessDefinition processDefinition = getProcessDefinition();

        //3.获得流程实例对象
        ProcessInstance instance =  runtimeService.createProcessInstanceQuery()
                        .processDefinitionId(processDefinition.getId())
                        .singleResult();

        //4.先判断该流程是否属于正常状态（⾮挂起状态）
        boolean suspended = instance.isSuspended();
        if(!suspended){
            //执⾏流程实例的挂起操作
            runtimeService.suspendProcessInstanceById(instance.getId());
            System.out.println(instance.getId()+"流程被挂起");
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
