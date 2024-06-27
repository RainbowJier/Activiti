package com.example.frame.activiti;

import com.example.frame.domain.Constants;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import static org.activiti.engine.impl.util.ProcessDefinitionUtil.getProcessDefinition;

/**
 * @Description 👀✔🐱‍🐉❌吹任务
 * @Author RainbowJier
 * @Date 2024/6/26
 */
public class E_HandleTasks {
    private static final String DEPLOYMENT_ID = Constants.DEPLOYMENT_ID;
    private static final ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    public static void main(String[] args) {
        // 输入流程节点的办理人的名称
        handleTask("王工");
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
     * 根据名称处理任务
     */
    public static void handleTask(String assignee) {
        ProcessDefinition processDefinition = getProcessDefinition();;

        // 查询任务
        TaskService taskService = engine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionId(processDefinition.getId())
                .taskAssignee(assignee)
                .singleResult();

        if (task == null) {
            System.out.println(assignee + "未找到任务");
        } else {
            taskService.complete(task.getId());
            System.out.println(assignee + "的任务已完成");
        }
    }
}
