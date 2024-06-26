package com.example.frame.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;

import java.util.List;
/**
 * @Description 👀✔🐱‍🐉❌
 * @Author RainbowJier
 * @Date 2024/6/26
 */
public class D_QueryTasks {
    private static final String DEPLOYMENT_ID = "ba844cda-3399-11ef-bf20-b2359fcf31ea";
    private static final ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    public static void main(String[] args) {
        queryTask("王工");
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

    /**
     * 根据名称查询任务
     */
    public static void queryTask(String assignee) {
        //获取流程定义
        ProcessDefinition processDefinition = getProcessDefinition();
        assert processDefinition != null;

        // 查询任务
        TaskService taskService = engine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionId(processDefinition.getId())
                .taskAssignee(assignee)
                .list();

        if (tasks.isEmpty()) {
            System.out.println("未找到王工的任务");
        } else {
            for (Task task : tasks) {
                System.out.println("流程定义Id：" + task.getProcessDefinitionId());
                System.out.println("流程实例Id：" + task.getProcessInstanceId());
                System.out.println("任务Id：" + task.getId());
                System.out.println("任务名称：" + task.getName());
            }
        }
    }
}
