package com.example.frame.activiti;

import com.example.frame.domain.Constants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 👀✔🐱‍🐉❌设置节点的Assignee
 * @Author RainbowJier
 * @Date 2024/6/27
 */
public class L_SetAssignee {
    private static final String DEPLOYMENT_ID = Constants.DEPLOYMENT_ID;
    private static final ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();


    public static void main(String[] args) {
        ProcessDefinition processDefinition = getProcessDefinition();
        assert processDefinition != null;

        RuntimeService runtimeService = engine.getRuntimeService();
        //创建存放变量名-变量值的map
        Map<String,Object> map = new HashMap<>();
        map.put("assignee0","张三");
        map.put("assignee1","李四");
        map.put("assignee2","王五");

        //创建流程实例
        ProcessInstance instance = runtimeService
                .startProcessInstanceById(processDefinition.getId(),map);

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
